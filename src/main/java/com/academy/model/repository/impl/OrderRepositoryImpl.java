package com.academy.model.repository.impl;

import com.academy.model.DataSourceManager;
import com.academy.model.entity.*;
import com.academy.model.repository.OrderRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {
    @Override
    public void create(Order order) {
        var connection = DataSourceManager.getInstance().getConnection();

        var query = "INSERT INTO orders (order_status_id, passenger_id, plane_id, route_id) VALUES (?, ?, ?, ?)";

        try (var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, order.getOrderStatus().getID());
            preparedStatement.setInt(2, order.getPassenger().getID());
            preparedStatement.setInt(3, order.getPlane().getID());
            preparedStatement.setInt(4, order.getRoute().getID());

            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Order order) {
        var connection = DataSourceManager.getInstance().getConnection();

        var query = "UPDATE orders SET order_status_id = ?, passenger_id = ?, plane_id = ?, route_id = ? WHERE ID = ?";

        try (var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, order.getOrderStatus().getID());
            preparedStatement.setInt(2, order.getPassenger().getID());
            preparedStatement.setInt(3, order.getPlane().getID());
            preparedStatement.setInt(4, order.getRoute().getID());
            preparedStatement.setInt(5, order.getID());

            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Order order) {
        var connection = DataSourceManager.getInstance().getConnection();

        var query = "DELETE FROM orders WHERE ID = ?";

        try (var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, order.getID());

            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Order> findAll() {
        var orders = new ArrayList<Order>();

        var connection = DataSourceManager.getInstance().getConnection();

        String query = "SELECT o.ID AS order_ID, os.ID AS order_status_ID, status AS order_status, p.ID AS passenger_ID, first_name, last_name, date_of_birth, email, " +
                "pl.ID AS plane_ID, model, seats, r.ID AS route_ID, cD.name AS departure_city, departure_date_time, cA.name AS arrival_city, arrival_date_time " +
                "FROM orders AS o " +
                "JOIN order_status AS os ON o.order_status_id = os.ID " +
                "JOIN passenger AS p ON o.passenger_id = p.ID " +
                "JOIN plane AS pl ON o.plane_id = pl.ID " +
                "JOIN route AS r ON o.route_id = r.ID " +
                "JOIN city AS cD ON r.departure_city_id = cD.ID " +
                "JOIN city AS cA ON r.arrival_city_id = cA.ID";

        try (var preparedStatement = connection.prepareStatement(query)) {

            var resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                var passenger = Passenger.builder()
                        .ID(resultSet.getInt("passenger_ID"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .dateOfBirth(resultSet.getDate("date_of_birth"))
                        .email(resultSet.getString("email"))
                        .build();

                var plane = Plane.builder()
                        .ID(resultSet.getInt("plane_ID"))
                        .model(resultSet.getString("model"))
                        .seats(resultSet.getString("seats"))
                        .build();

                var orderStatus = OrderStatus.builder()
                        .ID(resultSet.getInt("order_status_ID"))
                        .status(resultSet.getString("order_status"))
                        .build();

                var departureCity = City.builder()
                        .name(resultSet.getString("departure_city"))
                        .build();

                var arrivalCity = City.builder()
                        .name(resultSet.getString("arrival_city"))
                        .build();

                var route = Route.builder()
                        .ID(resultSet.getInt("route_ID"))
                        .departureCity(departureCity)
                        .departureDateTime(resultSet.getTimestamp("departure_date_time"))
                        .arrivalCity(arrivalCity)
                        .arrivalDateTime(resultSet.getTimestamp("arrival_date_time"))
                        .build();

                var order = Order.builder()
                        .ID(resultSet.getInt("order_ID"))
                        .passenger(passenger)
                        .orderStatus(orderStatus)
                        .plane(plane)
                        .route(route)
                        .build();

                orders.add(order);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return orders;
    }

    @Override
    public Order findById(Integer ID) {
        var order = Order.builder();

        var connection = DataSourceManager.getInstance().getConnection();

        String query = "SELECT o.ID AS order_ID, os.ID AS order_status_ID, status AS order_status, p.ID AS passenger_ID, first_name, last_name, date_of_birth, email, " +
                "pl.ID AS plane_ID, model, seats, r.ID AS route_ID, cD.name AS departure_city, departure_date_time, cA.name AS arrival_city, arrival_date_time  " +
                "FROM orders AS o " +
                "JOIN order_status AS os ON o.order_status_id = os.ID " +
                "JOIN passenger AS p ON o.passenger_id = p.ID " +
                "JOIN plane AS pl ON o.plane_id = pl.ID " +
                "JOIN route AS r ON o.route_id = r.ID " +
                "JOIN city AS cD ON r.departure_city_id = cD.ID " +
                "JOIN city AS cA ON r.arrival_city_id = cA.ID " +
                "WHERE o.ID = ?";

        try (var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, ID);

            var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                var passenger = Passenger.builder()
                        .ID(resultSet.getInt("passenger_ID"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .dateOfBirth(resultSet.getDate("date_of_birth"))
                        .email(resultSet.getString("email"))
                        .build();

                var plane = Plane.builder()
                        .ID(resultSet.getInt("plane_ID"))
                        .model(resultSet.getString("model"))
                        .seats(resultSet.getString("seats"))
                        .build();

                var orderStatus = OrderStatus.builder()
                        .ID(resultSet.getInt("order_status_ID"))
                        .status(resultSet.getString("order_status"))
                        .build();

                var departureCity = City.builder()
                        .name(resultSet.getString("departure_city"))
                        .build();

                var arrivalCity = City.builder()
                        .name(resultSet.getString("arrival_city"))
                        .build();

                var route = Route.builder()
                        .ID(resultSet.getInt("route_ID"))
                        .departureCity(departureCity)
                        .departureDateTime(resultSet.getTimestamp("departure_date_time"))
                        .arrivalCity(arrivalCity)
                        .arrivalDateTime(resultSet.getTimestamp("arrival_date_time"))
                        .build();

                order
                        .ID(resultSet.getInt("order_ID"))
                        .passenger(passenger)
                        .orderStatus(orderStatus)
                        .plane(plane)
                        .route(route);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return order.build();
    }
}
