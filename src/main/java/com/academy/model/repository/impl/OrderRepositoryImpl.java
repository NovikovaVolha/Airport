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
            preparedStatement.setInt(1, order.getOrderStatus().getId());
            preparedStatement.setInt(2, order.getPassenger().getId());
            preparedStatement.setInt(3, order.getPlane().getId());
            preparedStatement.setInt(4, order.getRoute().getId());

            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Order order) {
        var connection = DataSourceManager.getInstance().getConnection();

        var query = "UPDATE orders SET order_status_id = ?, passenger_id = ?, plane_id = ?, route_id = ? WHERE id = ?";

        try (var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, order.getOrderStatus().getId());
            preparedStatement.setInt(2, order.getPassenger().getId());
            preparedStatement.setInt(3, order.getPlane().getId());
            preparedStatement.setInt(4, order.getRoute().getId());
            preparedStatement.setInt(5, order.getId());

            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Order order) {
        var connection = DataSourceManager.getInstance().getConnection();

        var query = "DELETE FROM orders WHERE id = ?";

        try (var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, order.getId());

            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Order> findAll() {
        var orders = new ArrayList<Order>();

        var connection = DataSourceManager.getInstance().getConnection();

        String query = "SELECT o.id AS order_id, os.id AS order_status_id, status AS order_status, p.id AS passenger_ID, first_name, last_name, date_of_birth, email, " +
                "pl.id AS plane_id, model, seats, r.id AS route_id, cD.name AS departure_city, departure_date_time, cA.name AS arrival_city, arrival_date_time " +
                "FROM orders AS o " +
                "JOIN order_status AS os ON o.order_status_id = os.id " +
                "JOIN passenger AS p ON o.passenger_id = p.id " +
                "JOIN plane AS pl ON o.plane_id = pl.id " +
                "JOIN route AS r ON o.route_id = r.id " +
                "JOIN city AS cD ON r.departure_city_id = cD.id " +
                "JOIN city AS cA ON r.arrival_city_id = cA.id";

        try (var preparedStatement = connection.prepareStatement(query)) {

            var resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                var passenger = Passenger.builder()
                        .id(resultSet.getInt("passenger_id"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .dateOfBirth(resultSet.getDate("date_of_birth"))
                        .email(resultSet.getString("email"))
                        .build();

                var plane = Plane.builder()
                        .id(resultSet.getInt("plane_id"))
                        .model(resultSet.getString("model"))
                        .seats(resultSet.getString("seats"))
                        .build();

                var orderStatus = OrderStatus.builder()
                        .id(resultSet.getInt("order_status_id"))
                        .status(resultSet.getString("order_status"))
                        .build();

                var departureCity = City.builder()
                        .name(resultSet.getString("departure_city"))
                        .build();

                var arrivalCity = City.builder()
                        .name(resultSet.getString("arrival_city"))
                        .build();

                var route = Route.builder()
                        .id(resultSet.getInt("route_id"))
                        .departureCity(departureCity)
                        .departureDateTime(resultSet.getTimestamp("departure_date_time"))
                        .arrivalCity(arrivalCity)
                        .arrivalDateTime(resultSet.getTimestamp("arrival_date_time"))
                        .build();

                var order = Order.builder()
                        .id(resultSet.getInt("order_id"))
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
    public Order findById(Integer id) {
        var order = Order.builder();

        var connection = DataSourceManager.getInstance().getConnection();

        String query = "SELECT o.id AS order_id, os.id AS order_status_id, status AS order_status, p.id AS passenger_id, first_name, last_name, date_of_birth, email, " +
                "pl.id AS plane_id, model, seats, r.id AS route_id, cD.name AS departure_city, departure_date_time, cA.name AS arrival_city, arrival_date_time  " +
                "FROM orders AS o " +
                "JOIN order_status AS os ON o.order_status_id = os.id " +
                "JOIN passenger AS p ON o.passenger_id = p.id " +
                "JOIN plane AS pl ON o.plane_id = pl.id " +
                "JOIN route AS r ON o.route_id = r.id " +
                "JOIN city AS cD ON r.departure_city_id = cD.id " +
                "JOIN city AS cA ON r.arrival_city_id = cA.id " +
                "WHERE o.ID = ?";

        try (var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                var passenger = Passenger.builder()
                        .id(resultSet.getInt("passenger_id"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .dateOfBirth(resultSet.getDate("date_of_birth"))
                        .email(resultSet.getString("email"))
                        .build();

                var plane = Plane.builder()
                        .id(resultSet.getInt("plane_id"))
                        .model(resultSet.getString("model"))
                        .seats(resultSet.getString("seats"))
                        .build();

                var orderStatus = OrderStatus.builder()
                        .id(resultSet.getInt("order_status_id"))
                        .status(resultSet.getString("order_status"))
                        .build();

                var departureCity = City.builder()
                        .name(resultSet.getString("departure_city"))
                        .build();

                var arrivalCity = City.builder()
                        .name(resultSet.getString("arrival_city"))
                        .build();

                var route = Route.builder()
                        .id(resultSet.getInt("route_id"))
                        .departureCity(departureCity)
                        .departureDateTime(resultSet.getTimestamp("departure_date_time"))
                        .arrivalCity(arrivalCity)
                        .arrivalDateTime(resultSet.getTimestamp("arrival_date_time"))
                        .build();

                order
                        .id(resultSet.getInt("order_id"))
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
