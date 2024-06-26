package com.example.parkingsystem.application.repository.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import org.sqlite.SQLiteDataSource;

public class ConnectionFactory implements AutoCloseable{

    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static Statement statement;

    public static Connection createConnection(){
        try{
            instantiateConnectionIfNull();
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return connection;
    }

    private static void instantiateConnectionIfNull() throws SQLException {
        SQLiteDataSource  ds = new SQLiteDataSource();
        ds.setUrl("jdbc:sqlite:database.db");
        if(connection == null)
            connection = ds.getConnection();
    }

    private static PreparedStatement createPreparedStatement(String sql){
        try{
            preparedStatement = connection.prepareStatement(sql);
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return preparedStatement;
    }

    public static Statement createStatement(){
        try{
            statement = createConnection().createStatement();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return statement;
    }

    @Override
    public void close() throws Exception {
        closeStatementsIfNotNull();
        closeConnectionIfNotNull();
    }
    private void closeStatementsIfNotNull() throws SQLException {
        if(preparedStatement != null)
            preparedStatement.close();
        if(statement != null)
            statement.close();
    }

    private void closeConnectionIfNotNull() throws SQLException {
        if(connection != null)
            connection.close();
    }
}
