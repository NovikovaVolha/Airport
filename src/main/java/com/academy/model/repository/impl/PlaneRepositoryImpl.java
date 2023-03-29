package com.academy.model.repository.impl;

import com.academy.model.DataSourceManager;
import com.academy.model.entity.Plane;
import com.academy.model.repository.PlaneRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaneRepositoryImpl implements PlaneRepository {

    @Override
    public void create(Plane plane) {
        var connection = DataSourceManager.getInstance().getConnection();

        var query = "INSERT INTO plane (manufacturer, model, seats) VALUES (?, ?, ?)";

        try (var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, plane.getManufacturer());
            preparedStatement.setString(2, plane.getModel());
            preparedStatement.setString(3, plane.getSeats());

            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Plane plane) {
        var connection = DataSourceManager.getInstance().getConnection();

        var query = "UPDATE plane SET manufacturer = ?, model = ?, seats = ? WHERE id = ?";

        try (var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, plane.getManufacturer());
            preparedStatement.setString(2, plane.getModel());
            preparedStatement.setString(3, plane.getSeats());
            preparedStatement.setInt(4, plane.getId());

            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Plane plane) {
        var connection = DataSourceManager.getInstance().getConnection();

        var query = "DELETE FROM plane WHERE id = ?";

        try (var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, plane.getId());

            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Plane> findAll() {
        var planes = new ArrayList<Plane>();

        var connection = DataSourceManager.getInstance().getConnection();

        var query = "SELECT * FROM plane";

        try (var preparedStatement = connection.prepareStatement(query)) {

            var resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                var plane = Plane.builder()
                        .id(resultSet.getInt("id"))
                        .manufacturer(resultSet.getString("manufacturer"))
                        .model(resultSet.getString("model"))
                        .seats(resultSet.getString("seats"))
                        .build();

                planes.add(plane);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return planes;
    }

    @Override
    public Plane findById(Integer id) {
        var plane = Plane.builder();

        var connection = DataSourceManager.getInstance().getConnection();

        var query = "SELECT * FROM plane WHERE id = ?";

        try (var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                plane
                        .id(resultSet.getInt("id"))
                        .manufacturer(resultSet.getString("manufacturer"))
                        .model(resultSet.getString("model"))
                        .seats(resultSet.getString("seats"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return plane.build();
    }

}
