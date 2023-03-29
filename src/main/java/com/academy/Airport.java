package com.academy;

import com.academy.model.entity.*;
import com.academy.model.repository.impl.*;

public class Airport {

    public static void main(String[] args) {
        var orderRepository = new OrderRepositoryImpl();
        var passengerRepository = new PassengerRepositoryImpl();
        var passenger = passengerRepository.findById(4);

        var orderForUpdate = orderRepository.findById(3);
        orderForUpdate.setPassenger(passenger);

        orderRepository.update(orderForUpdate);

        var orders = orderRepository.findAll();

        for (Order order : orders) {
            System.out.println("Order " + order.getId() + ":\norder status: " + order.getOrderStatus().getStatus() + ", passenger: " +
                    order.getPassenger().getFirstName() + " " + order.getPassenger().getLastName() + ", plane: " +
                    order.getPlane().getModel() + ", departure: " +
                    order.getRoute().getDepartureCity().getName() + ", " + order.getRoute().getDepartureDateTime() + ", arrival: " +
                    order.getRoute().getArrivalCity().getName() + ", " + order.getRoute().getArrivalDateTime());
        }
    }

}
