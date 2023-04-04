package com.aprendizajeSpring.SpringBootAprendizaje.controllers;


import com.aprendizajeSpring.SpringBootAprendizaje.dao.UsuarioDao;
import com.aprendizajeSpring.SpringBootAprendizaje.models.Usuario;
import com.aprendizajeSpring.SpringBootAprendizaje.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private JWTUtil jwtUtil;

    //Se le esta inyectando dependenedias a esta interfaz, buscaria una clase que implemente esta interfaz y se la asigna
    // al objeto
    @Autowired
    private UsuarioDao usuarioDao;
    @RequestMapping(value = "api/usuario/{id}")
    public Usuario getUsuario( @PathVariable long id){

        Usuario user = new Usuario();
        user.setId(id);
        user.setNombre("Carlos");
        user.setApellido("Saavedra");
        user.setEmail("caesamo");
        user.setPassword("123");
        return user;
    }

    @RequestMapping(value = "api/usuarios")
    public List<Usuario> getUsuarios(@RequestHeader(value = "Authorization") String token){


        if (!validarToken(token)){
            return null;
        }

        return usuarioDao.getUsuarios();
    }

    @RequestMapping(value = "api/perfil/{email}")
    public List<Usuario> cargarPerfil(@RequestHeader(value = "Authorization") String token, @PathVariable String email){


        if (!validarToken(token)){
            return null;
        }

        return usuarioDao.cargarPerfil(email);
    }


    private boolean validarToken(String token){

        String usuarioId = jwtUtil.getKey(token);
        return usuarioId!=null;
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario){

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash  = argon2.hash(1,1024,1,usuario.getPassword());
        // iteraciones (Entre mas iteraciones mas segura la contraseña, pero mas lento el proceso) , espacio de memoria, paralelismo que es cuantos procesos a la vez se
        // van a ejecutar esto ayudaria acelerar el proceso en caso de que se ponga muchas iteraciones se incrementa este numero, contraseña a encriptar
        usuario.setPassword(hash);
         usuarioDao.registrar(usuario);
    }

    @RequestMapping(value = "api/actualizar", method = RequestMethod.POST)
    public void actualizarUsuario(@RequestBody Usuario usuario){

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash  = argon2.hash(1,1024,1,usuario.getPassword());
        // iteraciones (Entre mas iteraciones mas segura la contraseña, pero mas lento el proceso) , espacio de memoria, paralelismo que es cuantos procesos a la vez se
        // van a ejecutar esto ayudaria acelerar el proceso en caso de que se ponga muchas iteraciones se incrementa este numero, contraseña a encriptar
        usuario.setPassword(hash);
        usuarioDao.registrar(usuario);
    }

    @RequestMapping(value = "prueba43")
    public Usuario editarUsuario(){

        Usuario user = new Usuario();
        user.setNombre("Carlos");
        user.setApellido("Saavedra");
        user.setEmail("caesamo");
        user.setPassword("123");
        return user;
    }

    @RequestMapping(value = "api/usuarios/{id}",method = RequestMethod.DELETE)
    public void eliminarUsuario(@RequestHeader(value = "Authorization") String token,@PathVariable Long id){


        if (!validarToken(token)){
            return;
        }
        usuarioDao.eliminar(id);
    }

    @RequestMapping(value = "api/usuario/{email}",method = RequestMethod.DELETE)
    public void eliminarCuenta(@RequestHeader(value = "Authorization") String token,@PathVariable String email){


        if (!validarToken(token)){
            return;
        }
        usuarioDao.eliminarCuenta(email);
    }




}
