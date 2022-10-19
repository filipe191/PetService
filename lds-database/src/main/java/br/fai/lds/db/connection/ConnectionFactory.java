package br.fai.lds.db.connection;

import java.sql.*;

public class ConnectionFactory {

    private static Connection connection = null;

    private static final String URL = "jdbc:postgresql://localhost:5432/pet_service";

    private static final String USER = "postgres";

    private static final String PASSWORD = "123";

    private ConnectionFactory() {

    }
//
//    se conecta uma vez e fica conectada para sempre singinton

    public static Connection getConnection() {
        try {
//            if(connection != null) {
//                return connection;
//            }
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void close(PreparedStatement preparedStatement, Connection connection, ResultSet resultSet) {
        closePrepareStatement(preparedStatement);
        closeResultSet(resultSet);
        closeConnection(connection);
    }

    public static void close(PreparedStatement preparedStatement, Connection connection) {
        closePrepareStatement(preparedStatement);
        closeConnection(connection);
    }

    private static void closeConnection(Connection connection) {
        if (connection == null) {
            return;
        }
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private static void closeResultSet(ResultSet resultSet) {
        if (resultSet == null) {
            return;
        }
        try {
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void closePrepareStatement(PreparedStatement preparedStatement) {
        if (preparedStatement == null) {
            return;
        }
        try {
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
