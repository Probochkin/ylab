package fourth.io.ylab.intensive.lesson04.filesort;

import java.io.File;
import java.sql.SQLException;
import javax.sql.DataSource;


import fourth.io.ylab.intensive.lesson04.DbUtil;

public class FileSorterTest {
  public static void main(String[] args) throws SQLException {
    DataSource dataSource = initDb();
    File data = new Generator().generate("data.txt", 100000);
    FileSorter fileSorter = new FileSortImpl(dataSource);
    long start = System.nanoTime();
    File res = fileSorter.sort(data);
    long finish = System.nanoTime();
    long elapsed = finish - start;
    System.out.println("Сортировка с batch длилась " + elapsed/1000000000 + " секунд");
      dataSource = initDb();
   FileSorter fileSortImpl = new FileSorterWithoutBatch(dataSource);
      start = System.nanoTime();
   fileSortImpl.sort(data);
       finish = System.nanoTime();
       elapsed = finish - start;
      System.out.println("Сортировка с без batch длилась " + elapsed/1000000000 + " секунд");
  }
  
  public static DataSource initDb() throws SQLException {
    String createSortTable = "" 
                                 + "drop table if exists numbers;" 
                                 + "CREATE TABLE if not exists numbers (\n"
                                 + "\tval bigint\n"
                                 + ");";
    DataSource dataSource = DbUtil.buildDataSource();
    DbUtil.applyDdl(createSortTable, dataSource);
    return dataSource;
  }
}
