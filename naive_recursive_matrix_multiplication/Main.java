package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
	    double [][] A = { {1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12} }, B = { {1, 2, 3}, {4, 5, 6}, {7, 8, 9} };
        double [][] C = new NaiveMatrixMultiplication(A, B).multiply();
        for(int i = 0; i < C.length; i++) {
            System.out.println(Arrays.toString(C[i]));
        }
    }
}
