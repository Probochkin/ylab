package fourth.io.ylab.intensive.lesson04.persistentmap;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;


public class PersistentMapImpl implements PersistentMap {
  
  private DataSource dataSource;
  private String mapName;

  public PersistentMapImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public void init(String name) {
   this.mapName = name;
  }

  @Override
  public boolean containsKey(String key) throws SQLException {
    try (Connection connection = dataSource.getConnection();
         PreparedStatement preparedStatement  = connection.prepareStatement("select value from persistent_map where key = ? AND map_name = ?")) {
      preparedStatement.setString(1, key);
      preparedStatement.setString(2, this.mapName);
      ResultSet result = preparedStatement.executeQuery();
      if (result.next() && result.getString(1) != null) {
        return true;
      }
    }
    return false;
  }

  @Override
  public List<String> getKeys() throws SQLException {
    List<String> listResult = new ArrayList<>();
    try (Connection connection = dataSource.getConnection();
         PreparedStatement preparedStatement  = connection.prepareStatement("select key from persistent_map where map_name = ? and key NOTNULL ")) {
      preparedStatement.setString(1, this.mapName);
      ResultSet resultset = preparedStatement.executeQuery();
      while (resultset.next()) {
        listResult.add(resultset.getString(1));
      }
      return listResult;
    }
  }

  @Override
  public String get(String key) throws SQLException {
    try (Connection connection = dataSource.getConnection();
         PreparedStatement preparedStatement  = connection.prepareStatement("select value from persistent_map where key = ? AND map_name = ?")) {
      preparedStatement.setString(1, key);
      preparedStatement.setString(2, this.mapName);
      ResultSet result = preparedStatement.executeQuery();
      if (result.next() && result.getString(1) != null) {
        return result.getString(1);
      }
    }
    return null;
  }

  @Override
  public void remove(String key) throws SQLException {
    try (Connection connection = dataSource.getConnection();
         PreparedStatement preparedStatement  = connection.prepareStatement("DELETE from persistent_map where key = ? AND map_name = ?")) {
      preparedStatement.setString(1, key);
      preparedStatement.setString(2, this.mapName);
      preparedStatement.execute();
    }
  }

  @Override
  public void put(String key, String value) throws SQLException {
    try (Connection connection = dataSource.getConnection();
         PreparedStatement preparedStatement  = connection.prepareStatement("SELECT map_name,key from  persistent_map  where map_name = ? and key = ? ")) {
      preparedStatement.setString(1, this.mapName);
      preparedStatement.setString(2, key);
      ResultSet result = preparedStatement.executeQuery();
      if (result.next() && result.getString(1) != null) {
        remove(key);
        PreparedStatement  statement =  connection.prepareStatement("Insert into persistent_map(map_name,KEY,value) values(?,?,?)");
        statement.setString(1, this.mapName);
        statement.setString(2, key);
        statement.setString(3, value);
        statement.executeUpdate();
      } else {
        PreparedStatement  statement =  connection.prepareStatement("Insert into persistent_map(map_name,KEY,value) values(?,?,?)");
        statement.setString(1, this.mapName);
        statement.setString(2, key);
        statement.setString(3, value);
        statement.executeUpdate();
      }
    }
  }

  @Override
  public void clear() throws SQLException {
    try (Connection connection = dataSource.getConnection();
         PreparedStatement preparedStatement  = connection.prepareStatement("DELETE from  persistent_map  where map_name = ?  ")) {
      preparedStatement.setString(1, this.mapName);
      preparedStatement.executeUpdate();
    }
  }
}
