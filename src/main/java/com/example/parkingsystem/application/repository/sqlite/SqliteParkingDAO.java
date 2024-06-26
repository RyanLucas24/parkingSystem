package com.example.parkingsystem.application.repository.sqlite;

import com.example.parkingsystem.domain.model.parking.Parking;
import com.example.parkingsystem.domain.usecases.parking.ParkingDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteParkingDAO implements ParkingDAO {
    @Override
    public Parking create(Parking parking) {
        String sql = "INSERT INTO Parking(availableStandardParkingSpace, parkingSpace, availableMonthlyParkingSpace" +
                ", standardParkingSpace, monthlyParkingSpace) " +
                "VALUES (?, ?, ?, ?, ?)";

        try(PreparedStatement stmt = ConnectionFactory.createConnection().prepareStatement(sql)) {
            stmt.setInt(1, parking.getAvailableStandardParkingSpace());
            stmt.setInt(2, parking.getParkingSpace());
            stmt.setInt(3, parking.getAvailableMonthlyParkingSpace());
            stmt.setInt(4, parking.getStandardParkingSpace());
            stmt.setInt(5, parking.getMonthlyParkingSpace());
            stmt.execute();

            ResultSet rs = stmt.getGeneratedKeys();
            int generatedKey = rs.getInt(1);
            parking.setId(generatedKey);
            return parking;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Parking> readAll() {
        String sql = "SELECT * FROM Parking";
        List<Parking> parkings = new ArrayList<>();

        try(PreparedStatement stmt = ConnectionFactory.createConnection().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Parking parking = resultSetToEntity(rs);
                parkings.add(parking);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return parkings;
    }

    @Override
    public Optional<Parking> findOne(Integer id) {
        String sql = "SELECT * FROM Parking WHERE id = ?";
        Parking parking = null;

        try(PreparedStatement stmt = ConnectionFactory.createConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
                parking = resultSetToEntity(rs);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return Optional.ofNullable(parking);
    }

    @Override
    public boolean update(Parking parking) {
        String sql = "UPDATE INTO Parking SET availableStandardParkingSpace = ?, parkingSpace = ?" +
                " availableMonthlyParkingSpace = ? , standardParkingSpace = ?, monthlyParkingSpace = ?" +
                "WHERE id = ?";

        try(PreparedStatement stmt = ConnectionFactory.createConnection().prepareStatement(sql)) {
            stmt.setInt(1, parking.getAvailableStandardParkingSpace());
            stmt.setInt(2, parking.getParkingSpace());
            stmt.setInt(3, parking.getAvailableMonthlyParkingSpace());
            stmt.setInt(4, parking.getStandardParkingSpace());
            stmt.setInt(5, parking.getMonthlyParkingSpace());
            stmt.setInt(6, parking.getId());
            stmt.execute();

            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer id) {
        if (id == null)
            throw new IllegalArgumentException("Parking id must not be null");

        String sql = "DELETE FROM Parking WHERE id = ?";

        try(PreparedStatement stmt = ConnectionFactory.createConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Parking parking) {
        if(parking == null)
            throw new IllegalArgumentException("Client is null");

        return deleteByKey(parking.getId());
    }

    private Parking resultSetToEntity(ResultSet rs) throws SQLException {
        return new Parking(
                rs.getInt("availableParkingStandardParkingSpace"),
                rs.getInt("parkingSpace"),
                rs.getInt("availableMonthlyParkingSpace"),
                rs.getInt("standardParkingSpace"),
                rs.getInt("monthlyParkingSpace"),
                rs.getInt("id")
        );
    }
}
