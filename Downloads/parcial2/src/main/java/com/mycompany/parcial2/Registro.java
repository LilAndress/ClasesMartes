
package com.mycompany.parcial2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Registro {
    private Map<String, Estudiante> estudiantes;
    private Map<String, Actividad> actividades;
    private final String archivoEstudiantes = "estudiantes.txt";
    private final String archivoActividades = "actividades.txt";

    public Registro() {
        estudiantes = new HashMap<>();
        actividades = new HashMap<>();
        cargarDatos();
    }

    public void registrarEstudiante(String nombre, String apellido, String actividad) throws Exception {
        String key = nombre + " " + apellido;
        if (estudiantes.containsKey(key)) {
            Estudiante estudiante = estudiantes.get(key);
            estudiante.agregarActividad(actividad);
            actividades.get(actividad).agregarEstudiante(estudiante.getCodigoParticipacion());
            throw new Exception("El estudiante ya está registrado. Se agregó la nueva actividad.");
        } else {
            Estudiante estudiante = new Estudiante(nombre, apellido);
            while (codigoDuplicado(estudiante.getCodigoParticipacion())) {
                estudiante = new Estudiante(nombre, apellido); // Regenerar código
            }
            estudiante.agregarActividad(actividad);
            estudiantes.put(key, estudiante);

            if (!actividades.containsKey(actividad)) {
                actividades.put(actividad, new Actividad(actividad));
            }
            actividades.get(actividad).agregarEstudiante(estudiante.getCodigoParticipacion());
        }
        guardarDatos();
    }

    private boolean codigoDuplicado(String codigo) {
        return estudiantes.values().stream().anyMatch(e -> e.getCodigoParticipacion().equals(codigo));
    }

    public void listarEstudiantesPorActividad(String actividad) {
        if (actividades.containsKey(actividad)) {
            Actividad act = actividades.get(actividad);
            System.out.println("Actividad: " + actividad);
            for (String codigo : act.getEstudiantes()) {
                Estudiante est = estudiantes.values().stream().filter(e -> e.getCodigoParticipacion().equals(codigo)).findFirst().orElse(null);
                if (est != null) {
                    System.out.println(est);
                }
            }
        } else {
            System.out.println("No hay estudiantes inscritos en esta actividad.");
        }
    }

    public void listarTotalInscritos() {
        System.out.println("Total de estudiantes inscritos: " + estudiantes.size());
    }

    private void cargarDatos() {
        // Cargar estudiantes
        try (BufferedReader br = new BufferedReader(new FileReader(archivoEstudiantes))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                String nombre = partes[0];
                String apellido = partes[1];
                String codigo = partes[2];
                Estudiante estudiante = new Estudiante(nombre, apellido);
                estudiante.agregarActividad(partes[3]); // Agregar actividad
                estudiantes.put(nombre + " " + apellido, estudiante);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Cargar actividades
        try (BufferedReader br = new BufferedReader(new FileReader(archivoActividades))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                String nombre = partes[0];
                Actividad actividad = new Actividad(nombre);
                actividad.getEstudiantes().addAll(Arrays.asList(partes).subList(1, partes.length));
                actividades.put(nombre, actividad);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarDatos() {
        // Guardar estudiantes
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoEstudiantes))) {
            for (Estudiante est : estudiantes.values()) {
                for (String actividad : est.getActividades()) {
                    bw.write(est.getNombre() + "," + est.getApellido() + "," + est.getCodigoParticipacion() + "," + actividad);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Guardar actividades
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoActividades))) {
            for (Actividad act : actividades.values()) {
                bw.write(act.getNombre());
                for (String estudiante : act.getEstudiantes()) {
                    bw.write("," + estudiante);
                }
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
