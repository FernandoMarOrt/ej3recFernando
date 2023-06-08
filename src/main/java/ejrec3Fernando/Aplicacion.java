/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejrec3Fernando;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author fer
 */
public class Aplicacion {

    public static void main(String[] args) {

        int opcion;
        int[][] matrizInvertida;
        int[][] matriz;

        do {

            Menu();
            opcion = leerEnteroSinErrores();

            switch (opcion) {

                case 1 -> {
                    matriz = generarMatrizFichero("matrizP.txt", ",");
                    imprimirMatriz(matriz);
                }
                case 2 -> {
                    matriz = generarMatrizFichero("matrizP.txt", ",");
                    matrizInvertida = invertirMatriz(matriz);
                    imprimirMatriz(matrizInvertida);
                }
                case 3 -> {
                    matriz = generarMatrizFichero("matrizP.txt", ",");
                    matrizInvertida = invertirMatriz(matriz);
                    teclado.nextLine();
                    System.out.println("Dime un nombre para el fichero");
                    String nomFichero = teclado.nextLine();
                    generarMatrizFichero(nomFichero, matrizInvertida);
                }
                case 4 -> {
                    matriz = generarMatrizFichero("matrizP.txt", ",");
                    ordenarFilasMatriz(matriz);
                    imprimirMatriz(matriz);
                }

            }

        } while (opcion != 5);

    }

    public static void Menu() {

        String menu = """
                      Introduce una de estas opciones por favor:
                      
                        1.Leer archivo el archivo adjunto en la tarea, cargar el contenido en una matriz y mostrar la estructura de datos por consola
                        2.Leer archivo el archivo adjunto en la tarea, cargar el contenido en una matriz y realizar la matriz invertida, mostrando el resultado por consola. 
                        3.Realizar la función anterior pero volcando la matriz invertida en un fichero de texto en la raíz del proyecto. 
                        4.Leer archivo el archivo adjunto en la tarea, cargar el contenido en una matriz y ordenar cada fila de la matriz de mayor a menor, mostrando la matriz resultante por consola.
                        5.Salir
                      
                      """;

        System.out.println(menu);

    }

    public static Scanner teclado = new Scanner(System.in);

    public static int leerEnteroSinErrores() {

        int numero;

        do {
            try {
                numero = teclado.nextInt();
                break; // Si llegamos hasta aquí significa que no hay error
            } catch (InputMismatchException ime) {
                System.out.println("Debe introducir un entero por favor ");
                teclado.nextLine(); // Limpia los posibles datos que hay en el buffer
            }
        } while (true);

        return numero;

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
        int[] longitudesFilas = new int[filas];

        // Determinar las longitudes de las filas
        for (int i = 0; i < filas; i++) {
            longitudesFilas[i] = matriz[i].length;
        }

        int[][] matrizInvertida = new int[filas][];

        for (int i = 0; i < filas; i++) {
            int longitudFilaOriginal = longitudesFilas[i];
            matrizInvertida[i] = new int[longitudFilaOriginal];

            for (int j = 0; j < longitudFilaOriginal; j++) {
                matrizInvertida[i][j] = matriz[filas - i - 1][j];
            }
        }

        return matrizInvertida;
    }

    public static void generarMatrizFichero(String nomFichero, int[][] matriz) {

        try ( BufferedWriter flujo = new BufferedWriter(new FileWriter(nomFichero + ".txt"))) {

            for (int i = 0; i < matriz.length; i++) {

                for (int j = 0; j < matriz[i].length; j++) {
                    flujo.write(matriz[i][j] + " ");
                }

                flujo.newLine();
            }

            flujo.flush();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void ordenarFilasMatriz(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            Arrays.sort(matriz[i]);
            // Revertir el orden de la fila
            for (int j = 0; j < matriz[i].length / 2; j++) {
                int temp = matriz[i][j];
                matriz[i][j] = matriz[i][matriz[i].length - 1 - j];
                matriz[i][matriz[i].length - 1 - j] = temp;
            }
        }
    }

}
