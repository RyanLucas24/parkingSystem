package com.example.parkingsystem.application.repository.sqlite;

import com.example.parkingsystem.domain.model.service.Service;
import com.example.parkingsystem.domain.model.service.StandardService;
import com.example.parkingsystem.domain.usecases.service.StandardServiceDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteStandardServiceDAO implements StandardServiceDAO {
    @Override
    public Service create(Service service) {
        String sql = "INSERT INTO StandardService(value, time, toleranceTime, additionalValue, checkIn, checkOut) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try(PreparedStatement stmt = ConnectionFactory.createConnection().prepareStatement(sql)) {
            stmt.setDouble(1, service.getValue());
            stmt.setDouble(2, ((StandardService) service).getTime());
            stmt.setDouble(3, ((StandardService) service).getToleranceTime());
            stmt.setDouble(4, ((StandardService) service).getAdditionalValue());
            stmt.setString(5, ((StandardService) service).getCheckIn().toString());
            stmt.setString(6, ((StandardService) service).getCheckOut().toString());
            stmt.execute();

            ResultSet rs = stmt.getGeneratedKeys();
            int generatedKey = rs.getInt(1);
            service.setId(generatedKey);
            return service;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Service> readAll() {
        String sql = "SELECT * FROM StandardService";
        List<Service> services = new ArrayList<>();

        try(PreparedStatement stmt = ConnectionFactory.createConnection().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Service service = resultSetToEntity(rs);
                services.add(service);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return services;
    }

    @Override
    public Optional<Service> findOne(Integer id) {
        String sql = "SELECT * FROM StandardService WHERE id = ?";
        Service service = null;

        try(PreparedStatement stmt = ConnectionFactory.createConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
                service = resultSetToEntity(rs);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return Optional.ofNullable(service);
    }

    @Override
    public boolean update(Service service) {
        String sql = "UPDATE INTO StandardService SET value = ?, time = ?, toleranceTime = ?," +
                "additionalValue = ?, checkIn = ?, checkOut = ? WHERE id = ?";

        try(PreparedStatement stmt = ConnectionFactory.createConnection().prepareStatement(sql)) {
            stmt.setDouble(1, service.getValue());
            stmt.setDouble(2, ((StandardService) service).getTime());
            stmt.setDouble(3, ((StandardService) service).getToleranceTime());
            stmt.setDouble(4, ((StandardService) service).getAdditionalValue());
            stmt.setString(5, ((StandardService) service).getCheckIn().toString());
            stmt.setString(6, ((StandardService) service).getCheckOut().toString());
            stmt.setInt(7, service.getId());
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
            throw new IllegalArgumentException("Service id must not be null");

        String sql = "DELETE FROM StandardService WHERE id = ?";

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
    public boolean delete(Service service) {
        if(service == null)
            throw new IllegalArgumentException("Service is null");

        return deleteByKey(service.getId());
    }
    private Service resultSetToEntity(ResultSet rs) throws SQLException {
        return new StandardService(
                rs.getInt("id"),
                rs.getDouble("value"),
                rs.getDouble("time"),
                rs.getDouble("toleranceTime"),
                rs.getDouble("additionalValue"),
                LocalDateTime.parse(rs.getString("checkIn")),
                LocalDateTime.parse(rs.getString("checkOut"))
        );
    }
}
