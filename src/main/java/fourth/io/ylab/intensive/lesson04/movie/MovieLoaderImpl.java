package fourth.io.ylab.intensive.lesson04.movie;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.*;
import java.util.*;
import javax.sql.DataSource;

public class MovieLoaderImpl implements MovieLoader {
  private DataSource dataSource;

  public MovieLoaderImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public void loadData(File file) throws IOException {
    try (FileReader fileInput = new FileReader(file, Charset.forName("WINDOWS-1251"));
         Connection connection = dataSource.getConnection()) {
      Scanner scanner = new Scanner(fileInput);
      scanner.nextLine();
      scanner.nextLine();
      while (scanner.hasNextLine()) {
        String[] values = scanner.nextLine().split(";");
        Movie movie = createMovie(values);
        addMovieInDataBase(movie, connection);
        }

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }
  private static void addMovieInDataBase (Movie movie, Connection connection) {
    try (PreparedStatement statement =
                 connection.prepareStatement(String.format("INSERT INTO movie(year, length, title, subject, actors, actress, director, popularity, awards) " +
                         "VALUES (?, ?, ?,? ,? ,? ,? ,? , ?)"))) {
      if (movie.getYear() != null) {
        statement.setInt(1, movie.getYear());
      } else {
        statement.setNull(1, java.sql.Types.INTEGER);
      }
      if (movie.getLength() != null) {
        statement.setInt(2, movie.getLength());
      } else {
        statement.setNull(2, java.sql.Types.INTEGER);
      }
      if (movie.getTitle() != null) {
        statement.setString(3, movie.getTitle());
      } else {
        statement.setNull(3, java.sql.Types.VARCHAR);
      }
      if (movie.getSubject() != null) {
        statement.setString(4, movie.getSubject());
      } else {
        statement.setNull(4, java.sql.Types.VARCHAR);
      }
      if (movie.getActors() != null) {
        statement.setString(5, movie.getActors());
      } else {
        statement.setNull(5, java.sql.Types.VARCHAR);
      }
      if (movie.getActress() != null) {
        statement.setString(6, movie.getActress());
      } else {
        statement.setNull(6, java.sql.Types.VARCHAR);
      }
      if (movie.getDirector()!= null) {
        statement.setString(7, movie.getDirector());
      } else {
        statement.setNull(7, java.sql.Types.VARCHAR);
      }
      if (movie.getPopularity() != null) {
        statement.setInt(8, movie.getPopularity());
      } else {
        statement.setNull(8, java.sql.Types.INTEGER);
      }
      if (movie.getAwards() != null) {
        statement.setBoolean(9, movie.getAwards());
      } else {
        statement.setNull(9, java.sql.Types.BOOLEAN);
      }
      statement.execute();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  private static Movie createMovie (String[] values) {
    Movie result = new Movie();

    result.setYear(!values[0].isBlank() ? Integer.valueOf(values[0]) : null);
    result.setLength(!values[1].isBlank() ? Integer.valueOf(values[1]) : null);
    result.setTitle(!values[2].isBlank() ? values[2] : null);
    result.setSubject(!values[3].isBlank() ? values[3] : null);
    result.setActors(!values[4].isBlank() ? values[4] : null);
    result.setActress(!values[5].isBlank() ? values[5] : null);
    result.setDirector(!values[6].isBlank()? values[6]: null);
    result.setPopularity(!values[7].isBlank() ? Integer.valueOf(values[7]) : null);
    result.setAwards(values[8].equals("Yes") ? true : false);

    return result;
  }
}
