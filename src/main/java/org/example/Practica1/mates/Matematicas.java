package org.example.Practica1.mates;

import org.w3c.dom.ls.LSOutput;

public class Matematicas {

    public static double pi(int n) {
        int dentro = 0;
        for (int i = 0; i < n; i++) {
            double x = Math.random();
            double y = Math.random();
            if (x * x + y * y <= 1) {
                dentro++;
            }
        }
        return 4.0 * dentro / n;
    }
}