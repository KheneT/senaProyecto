package com.aprendizajeSpring.SpringBootAprendizaje.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="nutricionistas")
@ToString
public class Nutricionista {

    @Getter
    @Setter
    @Column(name="id")
    //con esto de @ID se indica que es la clave primaria
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Getter @Setter @Column(name="nombre")
    private String nombre;
    @Getter @Setter @Column(name="apellido")
    private String apellido;
    @Getter @Setter @Column(name="email")
    private String email;

    @Getter @Setter @Column(name="telefono")
    private String Telefono;
}
