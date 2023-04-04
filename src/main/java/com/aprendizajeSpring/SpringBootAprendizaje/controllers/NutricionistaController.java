package com.aprendizajeSpring.SpringBootAprendizaje.controllers;

import com.aprendizajeSpring.SpringBootAprendizaje.dao.NutricionistaDao;
import com.aprendizajeSpring.SpringBootAprendizaje.dao.UsuarioDao;
import com.aprendizajeSpring.SpringBootAprendizaje.models.Nutricionista;
import com.aprendizajeSpring.SpringBootAprendizaje.models.Usuario;
import com.aprendizajeSpring.SpringBootAprendizaje.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NutricionistaController {

    @Autowired
    private JWTUtil jwtUtil;


    @Autowired
    private NutricionistaDao nutricionistaDao;


    @RequestMapping(value = "api/nutricionistas")
    public List<Nutricionista> getNutricionistas(@RequestHeader(value = "Authorization") String token){


        if (!validarToken(token)){
            return null;
        }

        return nutricionistaDao.getNutricionista();
    }


    private boolean validarToken(String token){

        String usuarioId = jwtUtil.getKey(token);
        return usuarioId!=null;
    }
}
