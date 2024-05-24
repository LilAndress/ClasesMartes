
package com.mycompany.parcial2;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import java.util.Set;

import java.util.HashSet;

import java.util.Random;



class Estudiante {
    private String nombre;
    private String apellido;
    private String codigoParticipacion;
    private Set<String> actividades;

    public Estudiante(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.actividades = new HashSet<>();
        this.codigoParticipacion = generarCodigo(nombre, apellido);
    }

    private String generarCodigo(String nombre, String apellido) {
        Random rand = new Random();
        String codigo = (nombre.charAt(0) + "" + apellido.charAt(0) + apellido.charAt(apellido.length() - 1)).toLowerCase();
        codigo += rand.nextInt(900) + 100; // Genera un número aleatorio de 3 dígitos
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCodigoParticipacion() {
        return codigoParticipacion;
    }

    public Set<String> getActividades() {
        return actividades;
    }

    public void agregarActividad(String actividad) {
        actividades.add(actividad);
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " (" + codigoParticipacion + "): " + actividades;
    }
}