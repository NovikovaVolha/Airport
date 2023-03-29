package com.academy.model.repository.impl;

import com.academy.model.DataSourceManager;
import com.academy.model.entity.City;
import com.academy.model.entity.Route;
import com.academy.model.repository.RouteRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteRepositoryImpl implements RouteRepository {
    @Override
    public void create(Route route) {
        var connection = DataSourceManager.getInstance().getConnection();

        var query = "INSERT INTO route (departure_city_id, departure_date_time, arrival_city_id, arrival_date_time) VALUES (?, ?, ?, ?)";

        try (var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, route.getDepartureCity().getID());
            preparedStatement.setTimestamp(2, route.getDepartureDateTime());
            preparedStatement.setInt(3, route.getArrivalCity().getID());
            preparedStatement.setTimestamp(4, route.getArrivalDateTime());

            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Route route) {
        var connection = DataSourceManager.getInstance().getConnection();

        var query = "UPDATE route SET departure_city_id = ?, departure_date_time = ?, arrival_city_id = ?, arrival_date_time = ? WHERE ID = ?";

        try (var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, route.getDepartureCity().getID());
            preparedStatement.setTimestamp(2, route.getDepartureDateTime());
            preparedStatement.setInt(3, route.getArrivalCity().getID());
            preparedStatement.setTimestamp(4, route.getArrivalDateTime());
            preparedStatement.setInt(5, route.getID());

            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Route route) {
        var connection = DataSourceManager.getInstance().getConnection();

        var query = "DELETE FROM route WHERE ID = ?";

        try (var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, route.getID());

            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Route> findAll() {
        var routes = new ArrayList<Route>();

        var connection = DataSourceManager.getInstance().getConnection();

        var query = "SELECT r.ID AS route_ID, cD.name AS departure_city, " +
                "cD.country, departure_date_time, " +
                "cA.name AS arrival_city, cA.country, arrival_date_time " +
                "FROM route AS r " +
                "JOIN city AS cD ON r.departure_city_id = cD.ID " +
                "JOIN city AS cA ON r.arrival_city_id = cA.ID";

        try (var preparedStatement = connection.prepareStatement(query)) {

            var resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                var departureCity = City.builder()
                        .name(resultSet.getString("departure_city"))
                        .country(resultSet.getString("country"))
                        .build();

                var arrivalCity = City.builder()
                        .name(resultSet.getString("arrival_city"))
                        .country(resultSet.getString("country"))
                        .build();

                var route = Route.builder()
                        .ID(resultSet.getInt("route_ID"))
                        .departureCity(departureCity)
                        .departureDateTime(resultSet.getTimestamp("departure_date_time"))
                        .arrivalCity(arrivalCity)
                        .arrivalDateTime(resultSet.getTimestamp("arrival_date_time"))
                        .build();

                routes.add(route);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return routes;
    }

    @Override
    public Route findById(Integer ID) {
        var route = Route.builder();

        var connection = DataSourceManager.getInstance().getConnection();

        var query = "SELECT r.ID AS route_ID, cD.name AS departure_city, " +
                "cD.country, departure_date_time, " +
                "cA.name AS arrival_city, cA.country, arrival_date_time " +
                "FROM route AS r " +
                "JOIN city AS cD ON r.departure_city_id = cD.ID " +
                "JOIN city AS cA ON r.arrival_city_id = cA.ID " +
                "WHERE r.ID = ?";

        try (var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, ID);

            var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                var departureCity = City.builder()
                        .name(resultSet.getString("departure_city"))
                        .country(resultSet.getString("country"))
                        .build();

                var arrivalCity = City.builder()
                        .name(resultSet.getString("arrival_city"))
                        .country(resultSet.getString("country"))
                        .build();

                route
                        .ID(resultSet.getInt("route_ID"))
                        .departureCity(departureCity)
                        .departureDateTime(resultSet.getTimestamp("departure_date_time"))
                        .arrivalCity(arrivalCity)
                        .arrivalDateTime(resultSet.getTimestamp("arrival_date_time"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return route.build();
    }
}
