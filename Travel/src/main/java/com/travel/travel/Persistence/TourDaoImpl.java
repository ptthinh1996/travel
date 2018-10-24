package com.travel.travel.Persistence;


import com.travel.travel.Model.Tour;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class TourDaoImpl implements TourDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Tour> getAllTours(){
        String hql = "FROM Tour as t ORDER BY t.id";
        return (List<Tour>) entityManager.createQuery(hql).getResultList();
    }

    @Override
    public List<Tour> getByName(String name) {
        String hql = "FROM Tour as t WHERE t.name LIKE ? ORDER BY t.id";
        return (List<Tour>) entityManager.createQuery(hql).getResultList();
    }

    @Override
    public Tour getTour(int id) {
        return entityManager.find(Tour.class,id);
    }

    @Override
    public void addTour(Tour tour) {
        entityManager.persist(tour);
    }

    @Override
    public void updateTour(Tour tour){
        Tour tour1 = getTour(tour.getId());
        tour1.setName(tour.getName());
        tour1.setPrice(tour.getPrice());
        tour1.setUrl(tour.getUrl());
        entityManager.flush();
    }

    @Override
    public void deleteTour(int id) {
        entityManager.remove(getTour(id));
    }

    @Override
    public boolean tourExists(String name, String url) {
        String hql = "FROM Tour as t WHERE t.name = ? and t.url = ?";
        int count = entityManager.createQuery(hql).setParameter(0, name)
                .setParameter(1, url).getResultList().size();
        return count > 0 ? true : false;
    }
}
