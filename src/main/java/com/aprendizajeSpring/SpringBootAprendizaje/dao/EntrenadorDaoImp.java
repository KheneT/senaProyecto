package com.aprendizajeSpring.SpringBootAprendizaje.dao;

import com.aprendizajeSpring.SpringBootAprendizaje.models.Entrenador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public class EntrenadorDaoImp implements EntrenadorDao{


    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Entrenador> getEntrenador() {
        String query ="FROM Entrenador";
        return entityManager.createQuery(query).getResultList();
    }
}
