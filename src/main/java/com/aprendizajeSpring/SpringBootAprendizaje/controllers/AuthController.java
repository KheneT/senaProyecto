package com.aprendizajeSpring.SpringBootAprendizaje.controllers;

import com.aprendizajeSpring.SpringBootAprendizaje.dao.UsuarioDao;
import com.aprendizajeSpring.SpringBootAprendizaje.models.Usuario;
import com.aprendizajeSpring.SpringBootAprendizaje.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;


    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login (@RequestBody Usuario usuario){

        Usuario usuarioLogueado = usuarioDao.obtenerUsuarioPorCredenciales(usuario);

        if (usuarioLogueado != null){

            //formato KEY VALUE, SIENDO EL ID EL KEY Y EL EMAIL EL VALUE
            String token = jwtUtil.create(String.valueOf(usuarioLogueado.getId()),usuarioLogueado.getEmail());

            return token;
        }
         return "Fail";
    }
}
