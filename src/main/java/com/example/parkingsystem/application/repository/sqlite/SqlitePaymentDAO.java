package com.example.parkingsystem.application.repository.sqlite;

import com.example.parkingsystem.domain.model.payment.Payment;
import com.example.parkingsystem.domain.model.payment.PaymentMethodEnum;
import com.example.parkingsystem.domain.usecases.payment.PaymentDAO;
import com.example.parkingsystem.domain.usecases.reports.EarningsReportUseCase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqlitePaymentDAO implements PaymentDAO {
    @Override
    public Payment create(Payment payment) {
        String sql = "INSERT INTO Payment(paymentMethod,value,date) VALUES (?, ?, ?)";

        try(PreparedStatement stmt = ConnectionFactory.createConnection().prepareStatement(sql)) {
            stmt.setString(1, payment.getPaymentMethod().toString());
            stmt.setDouble(2, payment.getValue());
            stmt.setString(3, payment.getDate().toString());
            stmt.execute();

            ResultSet rs = stmt.getGeneratedKeys();
            int generatedKey = rs.getInt(1);
            payment.setId(generatedKey);
            return payment;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Payment> readAll() {
        String sql = "SELECT * FROM Payment";
        List<Payment> payments = new ArrayList<>();

        try(PreparedStatement stmt = ConnectionFactory.createConnection().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Payment payment = resultSetToEntity(rs);
                payments.add(payment);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return payments;
    }

    @Override
    public Optional<Payment> findOne(Integer id) {
        String sql = "SELECT * FROM Parking WHERE id = ?";
        Payment payment = null;

        try(PreparedStatement stmt = ConnectionFactory.createConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
                payment = resultSetToEntity(rs);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return Optional.ofNullable(payment);
    }

    @Override
    public boolean update(Payment payment) {
        String sql = "UPDATE INTO Payment SET paymentMethod = ?, value = ?, date = ? WHERE id = ?";

        try(PreparedStatement stmt = ConnectionFactory.createConnection().prepareStatement(sql)) {
            stmt.setString(1, payment.getPaymentMethod().toString());
            stmt.setDouble(2, payment.getValue());
            stmt.setString(3, payment.getDate().toString());
            stmt.setInt(4, payment.getId());
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
            throw new IllegalArgumentException("Payment id must not be null");

        String sql = "DELETE FROM Payment WHERE id = ?";

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
    public boolean delete(Payment payment) {
        if(payment == null)
            throw new IllegalArgumentException("Client is null");

        return deleteByKey(payment.getId());
    }

    private Payment resultSetToEntity(ResultSet rs) throws SQLException {
        return new Payment(
                rs.getInt("id"),
                rs.getDouble("value"),
                PaymentMethodEnum.toEnum(rs.getString("paymentMethod")),
                LocalDateTime.parse(rs.getString("date"))
        );
    }

    public List<EarningsReportUseCase> fetchPaymentsAndVisits(LocalDate startDate, LocalDate endDate) {
        String sql = "SELECT date(date) AS Dia, " +
                "COUNT(*) AS Numero_de_Visitas, " +
                "SUM(value) AS Valor_Total " +
                "FROM Payment " +
                "WHERE date >= ? AND date <= ? " +
                "GROUP BY date(date)";

        List<EarningsReportUseCase> reports = new ArrayList<>();

        try (PreparedStatement stmt = ConnectionFactory.createConnection().prepareStatement(sql)) {
            stmt.setString(1, startDate.toString());
            stmt.setString(2, endDate.toString());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String dia = rs.getString("Dia");
                int numeroDeVisitas = rs.getInt("Numero_de_Visitas");
                double valorTotal = rs.getDouble("Valor_Total");

                EarningsReportUseCase report = new EarningsReportUseCase(dia, numeroDeVisitas, valorTotal);
                reports.add(report);
            }
        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getMessage());
            return null;
        }

        return reports;
    }
}
