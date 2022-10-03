package databases;

import models.TrainOrder;

import java.sql.*;

public class DBConnector {
    Connection connection = null;

    public DBConnector()
    {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:cs.db");
            initTrainOrder();

        } catch ( Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Initialized database successfully");
    }

    public void initTrainOrder() throws SQLException {
            Statement statement = connection.createStatement();
            String sql =
                    "CREATE TABLE IF NOT EXISTS TrainOrder " +
                            "(ID INT PRIMARY KEY     NOT NULL," +
                            " OrderDate           TEXT    NOT NULL, " +
                            " Destination            CHAR(50)     NOT NULL, " +
                            " DepartureDate        TEXT NOT NULL, " +
                            " DepartureTime        CHAR(10) NOT NULL, " +
                            " ArriveTime        CHAR(10) NOT NULL, " +
                            " TrainNumber        CHAR(10) NOT NULL, " +
                            " Passenger        CHAR(50) NOT NULL, " +
                            " Unit        CHAR(1) NOT NULL, " +
                            " Price        REAL NOT NULL, " +
                            " Status         TEXT NOT NULL)";

            statement.executeUpdate(sql);
            statement.close();
    }

//    public TrainOrder getFirstTrainOrder() throws SQLException{
//        Statement statement = connection.createStatement();
//        String query =
//                "SELECT * FROM TrainOrder LIMIT 1";
//
//        ResultSet resultSet  = statement.executeQuery(query);
//
//        while(resultSet.next()){
//            // Retrieve by column label
//            return resultSet.getRow();
//
//        }
//        resultSet.close();
//    }

    public boolean addTrainOrder(TrainOrder order) throws SQLException {
        // check if the order exists
        String id = order.getId();
        String sql = "SELECT ID FROM TrainOrder WHERE ID = ?";
        PreparedStatement prepareStatement = connection.prepareStatement(sql);
        prepareStatement.setString(1, id);
        ResultSet resultSet = prepareStatement.executeQuery();
        if(resultSet.next()){
            System.out.println("TrainOrder with ID: " + id + " already exists.");
            return false;
        }
        resultSet.close();
        prepareStatement.close();

        sql = "INSERT INTO TrainOrder VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        prepareStatement = connection.prepareStatement(sql);
        prepareStatement.setString(1, order.getId());
        prepareStatement.setString(2, order.getOrderDate().toString());
        prepareStatement.setString(3, order.getDestination());
        prepareStatement.setString(4, order.getDepartureDate().toString());
        prepareStatement.setString(5, order.getDepartureTime());
        prepareStatement.setString(6, order.getArriveTime());
        prepareStatement.setString(7, order.getTrainNumber());
        prepareStatement.setString(8, order.getPassenger());
        prepareStatement.setString(9, order.getUnit());
        prepareStatement.setDouble(10, order.getPrice());
        prepareStatement.setString(11, order.getStatus());

        prepareStatement.executeUpdate();
        prepareStatement.close();

        return true;
    }

    public void debug() throws SQLException {
        Statement statement = connection.createStatement();
        String query =
                "SELECT * FROM TrainOrder";

        ResultSet resultSet  = statement.executeQuery(query);

        while(resultSet.next()){
            // Retrieve by column label
            String id = resultSet.getString(1);
            System.out.println(id);

        }
        resultSet.close();
    }
}
