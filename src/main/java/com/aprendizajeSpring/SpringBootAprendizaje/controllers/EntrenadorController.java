package com.aprendizajeSpring.SpringBootAprendizaje.controllers;


import com.aprendizajeSpring.SpringBootAprendizaje.dao.EntrenadorDao;
import com.aprendizajeSpring.SpringBootAprendizaje.dao.UsuarioDao;
import com.aprendizajeSpring.SpringBootAprendizaje.models.Entrenador;
import com.aprendizajeSpring.SpringBootAprendizaje.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class EntrenadorController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private EntrenadorDao entrenadorDao;

    @RequestMapping(value = "api/entrenadores")
    public List<Entrenador> getEntrenador(@RequestHeader(value = "Authorization") String token){


        if (!validarToken(token)){
            return null;
        }

        return entrenadorDao.getEntrenador();
    }

    private boolean validarToken(String token){

        String usuarioId = jwtUtil.getKey(token);
        return usuarioId!=null;
    }
}
