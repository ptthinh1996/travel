package com.travel.travel.Persistence;

import com.travel.travel.Model.Tour;

import java.util.List;

public interface TourDao {

    List<Tour> getAllTours();

    List<Tour> getByName(String name);

    Tour getTour(int id);

    void addTour(Tour tour);

    void updateTour(Tour tour);

    void deleteTour(int id);

    boolean tourExists(String name, String url);

}
