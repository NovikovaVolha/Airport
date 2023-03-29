package com.academy.model.repository.impl;

import com.academy.model.DataSourceManager;
import com.academy.model.entity.City;
import com.academy.model.repository.CityRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityRepositoryImpl implements CityRepository {
    @Override
    public void create(City city) {
        var connection = DataSourceManager.getInstance().getConnection();

        var query = "INSERT INTO city (name, country) VALUES (?, ?)";

        try (var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getCountry());

            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(City city) {
        var connection = DataSourceManager.getInstance().getConnection();

        var query = "UPDATE city SET name = ?, country = ? WHERE id = ?";

        try (var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getCountry());
            preparedStatement.setInt(3, city.getId());

            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(City city) {
        var connection = DataSourceManager.getInstance().getConnection();

        var query = "DELETE FROM city WHERE id = ?";

        try (var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, city.getId());

            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<City> findAll() {
        var cities = new ArrayList<City>();

        var connection = DataSourceManager.getInstance().getConnection();

        var query = "SELECT * FROM city";

        try (var preparedStatement = connection.prepareStatement(query)) {

            var resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                var city = City.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .country(resultSet.getString("country"))
                        .build();

                cities.add(city);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cities;
    }

    @Override
    public City findById(Integer id) {
        var city = City.builder();

        var connection = DataSourceManager.getInstance().getConnection();

        var query = "SELECT * FROM city WHERE id = ?";

        try (var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                city
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .country(resultSet.getString("country"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return city.build();
    }
}
