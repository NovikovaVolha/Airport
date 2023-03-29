package com.academy.model.repository.impl;

import com.academy.model.DataSourceManager;
import com.academy.model.entity.OrderStatus;
import com.academy.model.entity.Plane;
import com.academy.model.repository.OrderStatusRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderStatusRepositoryImpl implements OrderStatusRepository {

    @Override
    public void create(OrderStatus orderStatus) {
        var connection = DataSourceManager.getInstance().getConnection();

        var query = "INSERT INTO order_status (status) VALUES (?)";

        try (var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, orderStatus.getStatus());

            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(OrderStatus orderStatus) {
        var connection = DataSourceManager.getInstance().getConnection();

        var query = "UPDATE order_status SET status = ? WHERE ID = ?";

        try (var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, orderStatus.getStatus());

            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(OrderStatus orderStatus) {
        var connection = DataSourceManager.getInstance().getConnection();

        var query = "DELETE FROM order_status WHERE ID = ?";

        try (var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, orderStatus.getID());

            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<OrderStatus> findAll() {
        var orderStatuses = new ArrayList<OrderStatus>();

        var connection = DataSourceManager.getInstance().getConnection();

        var query = "SELECT * FROM order_status";

        try (var preparedStatement = connection.prepareStatement(query)) {

            var resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                var orderStatus = OrderStatus.builder()
                        .ID(resultSet.getInt("ID"))
                        .status(resultSet.getString("status"))
                        .build();

                orderStatuses.add(orderStatus);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return orderStatuses;
    }

    @Override
    public OrderStatus findById(Integer ID) {
        var orderStatus = OrderStatus.builder();

        var connection = DataSourceManager.getInstance().getConnection();

        var query = "SELECT * FROM order_status WHERE ID = ?";

        try (var preparedStatement = connection.prepareStatement(query)) {

            var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                orderStatus
                        .ID(resultSet.getInt("ID"))
                        .status(resultSet.getString("status"))
                        .build();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return orderStatus.build();
    }

}
