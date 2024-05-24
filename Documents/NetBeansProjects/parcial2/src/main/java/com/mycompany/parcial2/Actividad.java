
package com.mycompany.parcial2;

import java.util.HashSet;
import java.util.Set;

class Actividad {
    private String nombre;
    private Set<String> estudiantes;

    public Actividad(String nombre) {
        this.nombre = nombre;
        this.estudiantes = new HashSet<>();
    }

    public String getNombre() {
        return nombre;
    }

    public Set<String> getEstudiantes() {
        return estudiantes;
    }

    public void agregarEstudiante(String codigoParticipacion) {
        estudiantes.add(codigoParticipacion);
    }

    @Override
    public String toString() {
        return nombre + ": " + estudiantes.size() + " estudiantes";
    }
}
