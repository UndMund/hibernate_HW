package org.example.task10;

import org.example.task10.dao.BikeDao;
import org.example.task10.dao.MotorcyclistDao;
import org.example.task10.entity.Bike;
import org.example.task10.entity.Motorcyclist;
import org.junit.jupiter.api.Test;

public class MotorcyclistBikeTest {
    private static MotorcyclistDao motorcyclistDao = MotorcyclistDao.getInstance();
    private static BikeDao bikeDao = BikeDao.getInstance();
    private static Motorcyclist biker = Motorcyclist.builder()
            .name("Amogus")
            .build();
    private static Bike bike1 = Bike.builder()
            .name("Honda XR")
            .motorcyclist(biker)
            .build();
    private static Bike bike2 = Bike.builder()
            .name("KTM")
            .motorcyclist(biker)
            .build();

    @Test
    public void testDao() {
        biker = motorcyclistDao.add(biker);
        printMotorcyclist(biker);

        bike1 = bikeDao.add(bike1);
        bike2 = bikeDao.add(bike2);
        printBike(bike1);
        printBike(bike2);

        bikeDao.delete(bike1);
        printBike(bike1);

        motorcyclistDao.delete(biker);
        printMotorcyclist(biker);
        printBike(bike2);
    }

    private void printMotorcyclist(Motorcyclist motorcyclist) {
       try {
           System.err.println(motorcyclistDao.findById(motorcyclist.getId()));
       } catch (NullPointerException e) {
           System.err.println("Motorcyclist not found");
       }
    }

    private void printBike(Bike bike) {
        try {
            System.err.println(bikeDao.findById(bike.getId()));
        } catch (NullPointerException e) {
            System.err.println("Bike not found");
        }
    }
}