package fourth.io.ylab.intensive.lesson04.movie;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

import fourth.io.ylab.intensive.lesson04.DbUtil;

public class MovieTest {
  public static void main(String[] args) throws SQLException, IOException {
    DataSource dataSource = initDb();
    MovieLoader movieLoader = new MovieLoaderImpl(dataSource);

    File dataFile = new File("src\\main\\resources\\csv\\film.csv");
    movieLoader.loadData(dataFile);
    try (Connection connection = dataSource.getConnection()) {
        try (Statement statement = connection.createStatement()) {
         ResultSet result = statement.executeQuery(String.format("select subject, count(*) from movie  Group by subject "));
          int columns = result.getMetaData().getColumnCount();
          while(result.next()){
            for (int i = 1; i <= columns; i++){
              System.out.print(result.getString(i) + "\t");
            }
            System.out.println();
          }
       }
     }
  }


  private static DataSource initDb() throws SQLException {
    String createMovieTable = "drop table if exists movie;"
                                  + "CREATE TABLE IF NOT EXISTS movie (\n"
                                  + "\tid bigserial NOT NULL,\n"
                                  + "\t\"year\" int4,\n"
                                  + "\tlength int4,\n"
                                  + "\ttitle varchar,\n"
                                  + "\tsubject varchar,\n"
                                  + "\tactors varchar,\n"
                                  + "\tactress varchar,\n"
                                  + "\tdirector varchar,\n"
                                  + "\tpopularity int4,\n"
                                  + "\tawards bool,\n"
                                  + "\tCONSTRAINT movie_pkey PRIMARY KEY (id)\n"
                                  + ");";
    DataSource dataSource = DbUtil.buildDataSource();
    DbUtil.applyDdl(createMovieTable, dataSource);
    return dataSource;
  }
}
