package com.aprendizajeSpring.SpringBootAprendizaje.dao;

import com.aprendizajeSpring.SpringBootAprendizaje.models.Nutricionista;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class NutricionistaDaoImp implements NutricionistaDao{

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Nutricionista> getNutricionista() {
        String query ="FROM Nutricionista";
        return entityManager.createQuery(query).getResultList();
    }
}
