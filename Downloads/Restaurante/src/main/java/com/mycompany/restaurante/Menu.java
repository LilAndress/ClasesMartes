/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurante;

import javax.swing.JOptionPane;


public class Menu {
    
    String platos[];
    int datos [][];
    String dias[] = new String[]{"lunes", "Martes", "Miercoles","Jueves","Viernes","Sabado"};
    
    
    public void crearMenu(int cantidad){
        
        platos = new String [cantidad];
        int i = 0;
        
        while(i<cantidad){
          platos[i] = JOptionPane.showInputDialog("Ingrese el nombre del plato" + (i+1));       
          i++;
        } 
    }
    
    public void consultarMenu(){
      for(int i = 0; i < platos.length; i++){  
        JOptionPane.showMessageDialog(null, "platos : " + platos[i]);
        
      }
        
        
    }
    public void ingresarDatos(){
        datos = new int[6][platos.length];
        for(int f=0;f<6; f++){
            for(int c=0;c<platos.length;c++){
                datos[f][c]=Integer.parseInt(JOptionPane.showInputDialog("Ventas para el dia" + f + "plato:" + platos[c]));
            }
        }
    }
    
    
    public void cantidadPlatosVendidosSemana() {
        int totalVentasPorPlato[] = new int[platos.length];
        
        for (int c = 0; c < platos.length; c++) {
            int totalVentas = 0;
            for (int f = 0; f < 6; f++) {
                totalVentas += datos[f][c];
            }
            totalVentasPorPlato[c] = totalVentas;
        }
        
        for (int i = 0; i < platos.length; i++) {
            JOptionPane.showMessageDialog(null, "Total de ventas en la semana del plato " + platos[i] + ": " + totalVentasPorPlato[i]);
        }
    }
    
     public void diaMaxMinVentasPorPlato() {
        
         for (int c = 0; c < platos.length; c++) {
            int maxVentas = datos[0][c];
            int minVentas = datos[0][c];
            int diaMax = 0;
            int diaMin = 0;

            for (int f = 1; f < 6; f++) {
                if (datos[f][c] > maxVentas) {
                    maxVentas = datos[f][c];
                    diaMax = f;
                }
                if (datos[f][c] < minVentas) {
                    minVentas = datos[f][c];
                    diaMin = f;
                }
            }

            JOptionPane.showMessageDialog(null, "Para el plato " + platos[c] + 
                ", el día con más ventas fue " + dias[diaMax] + " con " + maxVentas + " ventas." +
                "\nEl día con menos ventas fue " + dias[diaMin] + " con " + minVentas + " ventas.");
        }
    }
     public void diaMaxMinVentasTotal() {
    int totalVentasPorDia[] = new int[6];
    
    
    for (int f = 0; f < 6; f++) {
        for (int c = 0; c < platos.length; c++) {
            totalVentasPorDia[f] += datos[f][c];
        }
    }

    
    int diaMax = 0, diaMin = 0;
    for (int f = 1; f < 6; f++) {
        if (totalVentasPorDia[f] > totalVentasPorDia[diaMax]) diaMax = f;
        if (totalVentasPorDia[f] < totalVentasPorDia[diaMin]) diaMin = f;
    }

   
    JOptionPane.showMessageDialog(null, 
        "El día con más ventas fue " + dias[diaMax] + " con " + totalVentasPorDia[diaMax] + " ventas.\n" +
        "El día con menos ventas fue " + dias[diaMin] + " con " + totalVentasPorDia[diaMin] + " ventas."
    );
}
     
     public void promedioVentas(){
         for(int c = 0; c <platos.length; c++){
             int totalventas =0;
             for (int f = 0; f < 6; f++){
                 totalventas += datos[f][c];
                 
             }
           
      
             double promedio = totalventas /6.0;
             for (int i = 0; i < platos.length; i++) {
             JOptionPane.showMessageDialog(null, "El promedio de ventas para el plato" +platos[i] +" es de" + promedio);
         }
         }
         
     }
     
     public void ventasTotales(){
        int totalventas =0;
         for( int f = 0; f < 6; f++){
          for(int c=0; c< platos.length; c++){
              totalventas += datos[f][c];
          }
     }
         JOptionPane.showMessageDialog(null, "El total de ventas de todos los platos en la semana es de :" + totalventas);
         
         
         
     }
}
