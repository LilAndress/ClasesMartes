
package com.mycompany.parcial2;


public class Parcial2 {
    public static void main(String[] args) {
        Registro registro = new Registro();
        try {
            registro.registrarEstudiante("Juan", "Perez", "Futbol");
            registro.registrarEstudiante("Ana", "Gomez", "Baloncesto");
            registro.registrarEstudiante("Juan", "Perez", "Natacion");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        registro.listarEstudiantesPorActividad("Futbol");
        registro.listarTotalInscritos();
    }
}