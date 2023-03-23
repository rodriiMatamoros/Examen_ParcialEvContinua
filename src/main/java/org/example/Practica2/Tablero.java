package org.example.Practica2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Clase que representa un tablero de juego de la vida.
public class Tablero {
    private static final int DIMENSION = 30;
    private int[][] estadoActual; // matriz que representa el estado actual
    private int[][] estadoSiguiente = new int[DIMENSION][DIMENSION]; // matriz que representa el estado siguiente

    // Constructor
    public Tablero(String archivoTablero) throws IOException {
        this.estadoActual = leerEstadoInicial(archivoTablero);
    }

    // Getters y setters
    private int[][] leerEstadoInicial(String archivoTablero) throws IOException {
        int[][] estadoInicial = new int[DIMENSION][DIMENSION];
        BufferedReader br = new BufferedReader(new FileReader(archivoTablero));
        String linea;
        int fila = 0;
        while ((linea = br.readLine()) != null && fila < DIMENSION) {
            for (int columna = 0; columna < DIMENSION && columna < linea.length(); columna++) {
                estadoInicial[fila][columna] = Character.getNumericValue(linea.charAt(columna));
            }
            fila++;
        }
        br.close();
        return estadoInicial;
    }


    public void calcularSiguienteEstado() {
        for (int fila = 0; fila < DIMENSION; fila++) {
            for (int columna = 0; columna < DIMENSION; columna++)
                estadoSiguiente[fila][columna] = calcularEstadoSiguiente(fila, columna);
        }
    }

    // Calcula el estado siguiente de una célula.
    private int calcularEstadoSiguiente(int fila, int columna) {
        int vecinosVivos = contarVecinosVivos(fila, columna);
        if (estadoActual[fila][columna] == 1) {
            if (vecinosVivos < 2 || vecinosVivos > 3)
                return 0;
            else
                return 1;
        } else {
            if (vecinosVivos == 3)
                return 1;
            else
                return 0;
        }
    }

    // Cuenta el número de vecinos vivos de una célula.
    private int contarVecinosVivos(int fila, int columna) {
        int vecinosVivos = 0;
        for (int i = fila - 1; i <= fila + 1; i++) {
            for (int j = columna - 1; j <= columna + 1; j++) {
                if (i >= 0 && i < DIMENSION && j >= 0 && j < DIMENSION && !(i == fila && j == columna)) {
                    if (estadoActual[i][j] == 1)
                        vecinosVivos++;
                }
            }
        }
        return vecinosVivos;
    }

    // Actualiza el estado actual del tablero.
    public void actualizarEstado() {
        for (int fila = 0; fila < DIMENSION; fila++) {
            for (int columna = 0; columna < DIMENSION; columna++)
                estadoActual[fila][columna] = estadoSiguiente[fila][columna];
        }
    }

   // Muestra el estado actual del tablero.
    public void mostrarEstado() {
        for (int fila = 0; fila < DIMENSION; fila++) {
            for (int columna = 0; columna < DIMENSION; columna++)
                System.out.print(estadoActual[fila][columna]);
            System.out.println();
        }
        System.out.println();
    }
}