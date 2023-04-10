package fourth.io.ylab.intensive.lesson04.filesort;

import java.io.*;
import java.sql.*;
import java.util.Scanner;
import javax.sql.DataSource;

public class FileSortImpl implements FileSorter {
  private DataSource dataSource;
  private final Integer BATCH_SIZE = 500;

  public FileSortImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public File sort(File data) {
    try (Connection connection = this.dataSource.getConnection()) {
      fileInBase(connection, data);
      try (Statement statement = connection.createStatement()) {
       ResultSet resultSet = statement.executeQuery("SELECT * from numbers ORDER BY val DESC");
        return  printToFile(resultSet);
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

    return null;
  }

  private void fileInBase(Connection connection, File file) {
    try (BufferedReader in = new BufferedReader(new FileReader(file));
         PreparedStatement preparedStatement = connection.prepareStatement("Insert into numbers (val) values (?)")) {
      connection.setAutoCommit(false);
      Scanner scanner = new Scanner(in);
      int i = 0;
      while (scanner.hasNext()) {
        preparedStatement.setLong(1, scanner.nextLong());
        preparedStatement.addBatch();
        i++;
        if (i >= BATCH_SIZE) {
          connection.commit();
          i = 0;
        }
      }
      preparedStatement.executeBatch();
      connection.commit();
    } catch (SQLException | IOException throwables) {
      throwables.printStackTrace();
    }
  }
  private File printToFile (ResultSet resultSet) throws SQLException {
    File file = new File("dataSort.txt");
    try (PrintWriter pw = new PrintWriter(file)) {
      while (resultSet.next()) {
        pw.println(resultSet.getLong(1));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return file;
  }

}
