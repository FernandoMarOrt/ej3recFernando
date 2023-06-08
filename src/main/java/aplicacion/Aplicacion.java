/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author fer
 */
public class Aplicacion {

    public static void main(String[] args) {

        int[][] matriz = generarMatrizFichero("matrizP.txt", ",");

        imprimirMatriz(matriz);
        
        int[][] matrizInvetida = invertirMatriz(matriz);
        
        imprimirMatriz(matrizInvetida);

    }

    public static int[][] generarMatrizFichero(String nomFichero, String separador) {
        String idFichero = nomFichero;
        String[] tokens;
        String linea = "";
        int contador = 0;
        int[][] matriz = null;

        try ( Scanner datosFichero = new Scanner(new File(idFichero), "UTF-8")) {

            while (datosFichero.hasNextLine()) {

                datosFichero.nextLine();

                contador++;
            }

            matriz = new int[contador][];
            datosFichero.close();

            Scanner datosFichero2 = new Scanner(new File(idFichero), "UTF-8");

            int fila = 0;
            while (datosFichero2.hasNextLine()) {

                linea = datosFichero2.nextLine();

                tokens = linea.split(separador);

                matriz[fila] = new int[tokens.length]; //Voy creando las columnas de la matriz con la longitud de los tokens

                for (int columna = 0; columna < tokens.length; columna++) { //Relleno la matriz con los tokens

                    matriz[fila][columna] = Integer.parseInt(tokens[columna]);
                }

                fila++; //Cuento filas
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return matriz;
    }

    public static void imprimirMatriz(int[][] matriz) {
        
        
        for (int i = 0; i < matriz.length; i++) {

            for (int j = 0; j < matriz[i].length; j++) {

                System.out.print(matriz[i][j] + " ");

            }

            System.out.println("");
        }
        
    }
    
    public static int[][] invertirMatriz(int[][] matriz) {
        int filas = matriz.length;
        int columnas = matriz[0].length;

        int[][] matrizInvertida = new int[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matrizInvertida[i][j] = matriz[filas - i - 1][columnas - j - 1];
            }
        }

        return matrizInvertida;
    }

}
