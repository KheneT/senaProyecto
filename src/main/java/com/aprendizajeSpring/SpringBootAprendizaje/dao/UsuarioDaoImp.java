package com.aprendizajeSpring.SpringBootAprendizaje.dao;

import com.aprendizajeSpring.SpringBootAprendizaje.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional

public class UsuarioDaoImp implements UsuarioDao {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Usuario> getUsuarios() {
        String query ="FROM Usuario";
        return entityManager.createQuery(query).getResultList();

    }

    @Override
    public List<Usuario> cargarPerfil(String email) {
        String query = "FROM Usuario where email = :email";

        return entityManager.createQuery(query)
                .setParameter("email",email).getResultList();
    }


    @Override
    public void eliminar(Long id) {
        //TODO ESTO ES HIBERNATE
        Usuario usuario = entityManager.find(Usuario.class,id);
        entityManager.remove(usuario);
    }

    @Override
    public void registrar(Usuario usuario) {
        entityManager.merge(usuario);

    }

    @Override
    public Usuario obtenerUsuarioPorCredenciales(Usuario usuario) {
        String query ="FROM Usuario Where email = :email";
        List<Usuario> lista =entityManager.createQuery(query)
                .setParameter("email",usuario.getEmail())
                .getResultList();

        //Esto es para evitar el null pointer exeption en caso de que el email no exista y se solicite el get password a esa lista
        if (lista.isEmpty()) {return null;}

        String passwordHashed = lista.get(0).getPassword();
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);


        if (argon2.verify(passwordHashed,usuario.getPassword())){

            return lista.get(0);

        }
        return null;

    }

    @Override
    public void eliminarCuenta(String email) {

        String query = "From Usuario where email = :email";
        List<Usuario> lista = entityManager.createQuery(query)
                .setParameter("email",email).getResultList();
        if (lista.isEmpty()){return;}
        Usuario usuario = entityManager.find(Usuario.class,lista.get(0).getId());
        entityManager.remove(usuario);
    }



}
