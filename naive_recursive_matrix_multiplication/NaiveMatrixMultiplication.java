package com.company;

/**
 * Created by mohamed on 2/21/17.
 */
public class NaiveMatrixMultiplication {
    private double [][] A, B, C;

    public NaiveMatrixMultiplication(double[][] a, double[][] b) {
        A = a;
        B = b;
        C = new double[A.length][B[0].length];
    }

    public double [][] multiply() {
        multiply(0, A.length - 1, 0, A[0].length - 1, 0, B.length - 1, 0, B[0].length - 1);
        return C;
    }

    private void multiply(int lowRowA, int highRowA, int lowColA, int highColA, int lowRowB, int highRowB, int lowColB, int highColB) {
        if(lowRowA == highRowA ||
           lowColA == highColB ||
           lowRowB == highRowB ||
           lowColB == highColB) {

            normal_multiply(lowRowA, highRowA, lowColA, highColA, lowRowB, highRowB, lowColB, highColB);
        }
        else {
            int midRowA = (lowRowA + highRowA) / 2;
            int midRowB = (lowRowB + highRowB) / 2;
            int midColA = (lowColA + highColA) / 2;
            int midColB = (lowColB + highColB) / 2;

            multiply(lowRowA, midRowA, lowColA, midColA, lowRowB, midRowB, lowColB, midColB);
            multiply(lowRowA, midRowA, midColA + 1, highColA, midRowB + 1, highRowB, lowColB, midColB);
            multiply(lowRowA, midRowA, lowColA, midColA, lowRowB, midRowB, midColB + 1, highColB);
            multiply(lowRowA, midRowA, midColA + 1, highColA, midRowB + 1, highRowB, midColB + 1, highColB);
            multiply(midRowA + 1, highRowA, lowColA, midColA, lowRowB, midRowB, lowColB, midColB);
            multiply(midRowA + 1, highRowA, midColA + 1, highRowA, midRowB + 1, highRowB, lowColB, midColB);
            multiply(midRowA + 1, highRowA, lowColA, midColA, lowRowB, midRowB, midColB + 1, highColB);
            multiply(midRowA + 1, highRowA, midColA + 1, highColA, midRowB + 1, highRowB, midColB + 1, highColB);
        }
    }

    private void normal_multiply(int lowRowA, int highRowA, int lowColA, int highColA, int lowRowB, int highRowB, int lowColB, int highColB) {
        for(int i = lowRowA; i <= highRowA; i++) {
            for(int j = lowColB; j <= highColB; j++) {
                for(int k = lowRowB; k <= highRowB; k++) {
                    try {
                        C[i][j] += A[i][k] * B[k][j];
                    } catch(Exception ex) {
                        System.out.println(i + " " + j + " " + k);
                    }
                }
            }
        }
    }


}
