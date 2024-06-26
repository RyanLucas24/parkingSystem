package com.example.parkingsystem.application.repository.sqlite;

import com.example.parkingsystem.domain.model.service.MonthlyService;
import com.example.parkingsystem.domain.model.service.Service;
import com.example.parkingsystem.domain.usecases.service.MonthlyServiceDAO;
import com.example.parkingsystem.domain.usecases.service.StandardServiceDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteMonthlyServiceDAO implements MonthlyServiceDAO{
    @Override
    public Service create(Service service) {
        String sql = "INSERT INTO MonthlyService(value, paymentDate, paymentChecked) VALUES (?, ?, ?)";

        try(PreparedStatement stmt = ConnectionFactory.createConnection().prepareStatement(sql)) {
            stmt.setDouble(1, service.getValue());
            stmt.setString(2, ((MonthlyService) service).getPaymentDate().toString());
            stmt.setBoolean(3, ((MonthlyService) service).isPaymentChecked());
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
        String sql = "SELECT * FROM MonthlyService";
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
        String sql = "SELECT * FROM MonthlyService WHERE id = ?";
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
        String sql = "UPDATE INTO MonthlyService SET value = ?, paymentDate = ?, paymentChecked = ? WHERE id = ?";

        try(PreparedStatement stmt = ConnectionFactory.createConnection().prepareStatement(sql)) {
            stmt.setDouble(1, service.getValue());
            stmt.setString(2, ((MonthlyService)service).getPaymentDate().toString());
            stmt.setBoolean(3, ((MonthlyService)service).isPaymentChecked());
            stmt.setInt(4, service.getId());
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

        String sql = "DELETE FROM MonthlyService WHERE id = ?";

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
        return new MonthlyService(
                rs.getInt("id"),
                rs.getDouble("value"),
                LocalDateTime.parse(rs.getString("paymentDate")),
                rs.getBoolean("paymentChecked")
        );
    }

}
