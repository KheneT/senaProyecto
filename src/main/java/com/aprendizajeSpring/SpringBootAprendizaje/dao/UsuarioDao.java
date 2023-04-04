package com.aprendizajeSpring.SpringBootAprendizaje.dao;

import com.aprendizajeSpring.SpringBootAprendizaje.models.Usuario;

import java.util.List;

public interface UsuarioDao {
    List<Usuario> getUsuarios();


    void eliminar(Long id);

    void registrar(Usuario usuario);

    Usuario obtenerUsuarioPorCredenciales(Usuario usuario);

    void eliminarCuenta(String email);

    List<Usuario> cargarPerfil(String email);
}
