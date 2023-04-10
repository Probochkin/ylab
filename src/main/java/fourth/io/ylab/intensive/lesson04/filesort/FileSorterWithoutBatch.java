package fourth.io.ylab.intensive.lesson04.filesort;

import javax.sql.DataSource;
import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class FileSorterWithoutBatch implements FileSorter {
    private DataSource dataSource;
    public FileSorterWithoutBatch(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public File sort(File data) {
        try (Connection connection = this.dataSource.getConnection()) {
            fileInBaseWithoutBatch(connection, data);
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery("SELECT * from numbers ORDER BY val DESC");
                return  printToFile(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }
    private void fileInBaseWithoutBatch(Connection connection, File file) {
        try (BufferedReader in = new BufferedReader(new FileReader(file));
             PreparedStatement preparedStatement = connection.prepareStatement("Insert into numbers (val) values (?)")) {
            Scanner scanner = new Scanner(in);

            while (scanner.hasNext()) {
                preparedStatement.setLong(1, scanner.nextLong());
                preparedStatement.executeUpdate();
            }
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
