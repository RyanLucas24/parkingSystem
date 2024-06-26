package com.example.parkingsystem.application.repository.sqlite;

import com.example.parkingsystem.domain.model.client.Client;
import com.example.parkingsystem.domain.usecases.client.ClientDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteClientDAO implements ClientDAO {
    @Override
    public Client create(Client client) {
        String sql = "INSERT INTO Client(cpf, name, phone, email, address, vehiclePlate, service) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try(PreparedStatement stmt = ConnectionFactory.createConnection().prepareStatement(sql)) {
            stmt.setString(1, client.getCpf());
            stmt.setString(2, client.getName());
            stmt.setString(3, client.getPhone());
            stmt.setString(4, client.getEmail());
            stmt.setString(5, client.getAddress());
            stmt.setString(6, client.getVehiclePlate());
            stmt.setInt(7, client.getService().getId());
            stmt.execute();

            ResultSet rs = stmt.getGeneratedKeys();
            int generatedKey = rs.getInt(1);
            client.setId(generatedKey);
            return client;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Optional<Client> findOne(String cpf) {
        String sql = "SELECT * FROM Client WHERE cpf = ?";
        Client client = null;

        try(PreparedStatement stmt = ConnectionFactory.createConnection().prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
                client = resultSetToEntity(rs);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return Optional.ofNullable(client);
    }


    @Override
    public List<Client> readAll() {
        String sql = "SELECT * FROM Client";
        List<Client> clients = new ArrayList<>();

        try(PreparedStatement stmt = ConnectionFactory.createConnection().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Client client = resultSetToEntity(rs);
                clients.add(client);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return clients;
    }

    @Override
    public Optional<Client> findOne(Integer key) {
        String sql = "SELECT * FROM Client WHERE id = ?";
        Client client = null;

        try(PreparedStatement stmt = ConnectionFactory.createConnection().prepareStatement(sql)) {
            stmt.setInt(1, key);
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
                client = resultSetToEntity(rs);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return Optional.ofNullable(client);
    }

    @Override
    public boolean update(Client client) {
        String sql = "UPDATE INTO Client SET cpf = ? name = ?, phone = ?, email = ?, address = ?, vehiclePlate = ?," +
                " service = ? WHERE id = ?";

        try(PreparedStatement stmt = ConnectionFactory.createConnection().prepareStatement(sql)) {
            stmt.setString(1, client.getCpf());
            stmt.setString(2, client.getName());
            stmt.setString(3, client.getPhone());
            stmt.setString(4, client.getEmail());
            stmt.setString(5, client.getAddress());
            stmt.setString(6, client.getVehiclePlate());
            stmt.setInt(7, client.getService().getId());
            stmt.setInt(8, client.getId());
            stmt.execute();

            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        if (key == null)
            throw new IllegalArgumentException("Client id must not be null");

        String sql = "DELETE FROM Client WHERE id = ?";

        try(PreparedStatement stmt = ConnectionFactory.createConnection().prepareStatement(sql)) {
            stmt.setInt(1, key);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Client client) {
        if(client == null)
            throw new IllegalArgumentException("Client is null");

        return deleteByKey(client.getId());
    }

    private Client resultSetToEntity(ResultSet rs) throws SQLException {
        return new Client(
                rs.getInt("id"),
                rs.getString("cpf"),
                rs.getString("phone"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("address"),
                rs.getString("vehiclePlate")
        );
    }
}
