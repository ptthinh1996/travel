package com.travel.travel.Service;

import com.travel.travel.Model.Tour;
import com.travel.travel.Persistence.TourDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourServiceImpl implements TourService{

    @Autowired
    private TourDao tourDao;

    @Override
    public Tour getTour(int id) {
        Tour tour = tourDao.getTour(id);
        return tour;
    }

    @Override
    public List<Tour> getAllTours(){
        return tourDao.getAllTours();
    }

    @Override
    public List<Tour> getByName(String name) {
        return tourDao.getByName(name);
    }

    @Override
    public synchronized boolean addTour(Tour tour){
        if (tourDao.tourExists(tour.getName(), tour.getUrl())) {
            return false;
        } else {
            tourDao.addTour(tour);
            return true;
        }
    }

    @Override
    public void updateTour(Tour tour) {
        tourDao.updateTour(tour);
    }

    @Override
    public void deleteTour(int id) {
        tourDao.deleteTour(id);
    }

    @Override
    public void crawlData() {

    }
}
