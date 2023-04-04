package com.aprendizajeSpring.SpringBootAprendizaje;

import com.aprendizajeSpring.SpringBootAprendizaje.controllers.UsuarioController;
import com.aprendizajeSpring.SpringBootAprendizaje.models.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class SpringBootAprendizajeApplicationTests {


	@Test
	void contextLoads() {
	}

	private UsuarioController uController;
	@BeforeEach
	public void setUp(){
		uController = new UsuarioController();
		//Esto asegurara que siempre se cree una nueva instacia de usuarioController sin necesidad de declararla
	}


	@DisplayName("Comprobar return del metodo prueba en clase Controller")
	@Test
	public void test1(){


		List<String> esperado = new ArrayList<>(Arrays.asList("hola"));
		final Usuario resultado = uController.getUsuario(3L);

		Assertions.assertEquals(resultado,resultado, "prueba unitaria exitosa: ");
		Assertions.assertTrue(1==2);

	}


}
