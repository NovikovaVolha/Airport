package com.academy.model.repository.impl;

import com.academy.model.DataSourceManager;
import com.academy.model.entity.Passenger;
import com.academy.model.repository.PassengerRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PassengerRepositoryImpl implements PassengerRepository {
    @Override
    public void create(Passenger passenger) {
        var connection = DataSourceManager.getInstance().getConnection();

        var query = "INSERT INTO passenger (first_name, last_name, date_of_birth, gender_id, email, address) VALUES (?, ?, ?, ?, ?, ?)";

        try (var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, passenger.getFirstName());
            preparedStatement.setString(2, passenger.getLastName());
            preparedStatement.setDate(3, passenger.getDateOfBirth());
            preparedStatement.setInt(4, passenger.getGenderId());
            preparedStatement.setString(5, passenger.getEmail());
            preparedStatement.setString(6, passenger.getAddress());

            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Passenger passenger) {
        var connection = DataSourceManager.getInstance().getConnection();

        var query = "UPDATE passenger SET first_name = ?, last_name = ?, date_of_birth = ?, gender_id = ? WHERE id = ?";

        try (var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, passenger.getFirstName());
            preparedStatement.setString(2, passenger.getLastName());
            preparedStatement.setDate(3, passenger.getDateOfBirth());
            preparedStatement.setInt(4, passenger.getGenderId());
            preparedStatement.setInt(5, passenger.getId());

            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Passenger passenger) {
        var connection = DataSourceManager.getInstance().getConnection();

        var query = "DELETE FROM passenger WHERE id = ?";

        try (var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, passenger.getId());

            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Passenger> findAll() {
        var passengers = new ArrayList<Passenger>();

        var connection = DataSourceManager.getInstance().getConnection();

        var query = "SELECT * FROM passenger";

        try (var preparedStatement = connection.prepareStatement(query)) {

            var resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                var passenger = Passenger.builder()
                        .id(resultSet.getInt("id"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .dateOfBirth(resultSet.getDate("date_of_birth"))
                        .genderId(resultSet.getInt("gender_id"))
                        .email(resultSet.getString("email"))
                        .address(resultSet.getString("address"))
                        .build();

                passengers.add(passenger);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return passengers;
    }

    @Override
    public Passenger findById(Integer id) {
        var passenger = Passenger.builder();

        var connection = DataSourceManager.getInstance().getConnection();

        var query = "SELECT * FROM passenger WHERE id = ?";

        try (var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                passenger
                        .id(resultSet.getInt("id"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .dateOfBirth(resultSet.getDate("date_of_birth"))
                        .genderId(resultSet.getInt("gender_id"))
                        .email(resultSet.getString("email"))
                        .address(resultSet.getString("address"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return passenger.build();
    }
}
