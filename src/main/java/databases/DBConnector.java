package databases;

import models.FlightOrder;
import models.HotelOrder;
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
            initFlightOrder();
            initHotelOrder();

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

    public void initFlightOrder() throws SQLException{
        Statement statement = connection.createStatement();
        String sql =
                "CREATE TABLE IF NOT EXISTS FlightOrder " +
                        "(ID INT PRIMARY KEY     NOT NULL," +
                        " OrderDate           TEXT    NOT NULL, " +
                        " Destination            CHAR(50)     NOT NULL, " +
                        " DepartureDate        TEXT NOT NULL, " +
                        " DepartureTime        CHAR(10) NOT NULL, " +
                        " ArriveDate        TEXT NOT NULL, " +
                        " ArriveTime        CHAR(10) NOT NULL, " +
                        " FlightNumber        CHAR(10) NOT NULL, " +
                        " Passenger        CHAR(50) NOT NULL, " +
                        " Unit        CHAR(1) NOT NULL, " +
                        " Price        REAL NOT NULL, " +
                        " Status         TEXT NOT NULL)";

        statement.executeUpdate(sql);
        statement.close();
    }

    public void initHotelOrder() throws SQLException {
        Statement statement = connection.createStatement();
        String sql =
                "CREATE TABLE IF NOT EXISTS HotelOrder " +
                        "(ID INT PRIMARY KEY     NOT NULL," +
                        " OrderDate           TEXT    NOT NULL, " +
                        " HotelName            CHAR(50)     NOT NULL, " +
                        " Description        TEXT NOT NULL, " +
                        " StartDate        CHAR(10) NOT NULL, " +
                        " EndDate        CHAR(10) NOT NULL, " +
                        " Passenger        CHAR(10) NOT NULL, " +
                        " RoomType        CHAR(50) NOT NULL, " +
                        " Unit        CHAR(1) NOT NULL, " +
                        " Price        REAL NOT NULL, " +
                        " Status         TEXT NOT NULL)";

        statement.executeUpdate(sql);
        statement.close();
    }

    public boolean addHotelOrder(HotelOrder order) throws SQLException{
        // check if the order exists
        String id = order.getId();
        String sql = "SELECT ID FROM HotelOrder WHERE ID = ?";
        PreparedStatement prepareStatement = connection.prepareStatement(sql);
        prepareStatement.setString(1, id);
        ResultSet resultSet = prepareStatement.executeQuery();
        if(resultSet.next()){
            System.out.println("HotelOrder with ID: " + id + " already exists.");
            return false;
        }
        resultSet.close();
        prepareStatement.close();

        sql = "INSERT INTO HotelOrder VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        prepareStatement = connection.prepareStatement(sql);
        prepareStatement.setString(1, order.getId());
        prepareStatement.setString(2, order.getOrderDate().toString());
        prepareStatement.setString(3, order.getHotelName());
        prepareStatement.setString(4, order.getDescription());
        prepareStatement.setString(5, order.getStartDate().toString());
        prepareStatement.setString(6, order.getEndDate().toString());
        prepareStatement.setString(7, order.getPassenger());
        prepareStatement.setString(8, order.getRoomType());
        prepareStatement.setString(9, order.getUnit());
        prepareStatement.setDouble(10, order.getPrice());
        prepareStatement.setString(11, order.getStatus());

        prepareStatement.executeUpdate();
        prepareStatement.close();

        return true;
    }

    public boolean addFlightOrder(FlightOrder order) throws SQLException{
        // check if the order exists
        String id = order.getId();
        String sql = "SELECT ID FROM FlightOrder WHERE ID = ?";
        PreparedStatement prepareStatement = connection.prepareStatement(sql);
        prepareStatement.setString(1, id);
        ResultSet resultSet = prepareStatement.executeQuery();
        if(resultSet.next()){
            System.out.println("FlightOrder with ID: " + id + " already exists.");
            return false;
        }
        resultSet.close();
        prepareStatement.close();

        sql = "INSERT INTO FlightOrder VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        prepareStatement = connection.prepareStatement(sql);
        prepareStatement.setString(1, order.getId());
        prepareStatement.setString(2, order.getOrderDate().toString());
        prepareStatement.setString(3, order.getOrigin());
        prepareStatement.setString(4, order.getDestination());
        prepareStatement.setString(5, order.getDepartureDate().toString());
        prepareStatement.setString(6, order.getDepartureTime());
        prepareStatement.setString(7, order.getArriveDate().toString());
        prepareStatement.setString(8, order.getArriveTime());
        prepareStatement.setString(9, order.getFlightNumber());
        prepareStatement.setString(10, order.getPassenger());
        prepareStatement.setString(11, order.getUnit());
        prepareStatement.setDouble(12, order.getPrice());
        prepareStatement.setString(13, order.getStatus());

        prepareStatement.executeUpdate();
        prepareStatement.close();

        return true;
    }

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
