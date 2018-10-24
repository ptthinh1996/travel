package com.travel.travel.Service;

import com.travel.travel.Model.Tour;

import java.util.List;

public interface TourService {
    List<Tour> getAllTours();
    List<Tour> getByName(String name);
    Tour getTour(int id);
    boolean addTour(Tour tour);
    void updateTour(Tour article);
    void deleteTour(int id);
    void crawlData();
}
