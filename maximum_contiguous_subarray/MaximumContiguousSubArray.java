/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maximumcontiguoussubarray;

import java.util.Random;

/**
 *
 * @author mohamed
 */
public class MaximumContiguousSubArray {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double [] A = new double[50000000];
        Random rand = new Random();
        for(int i = 0; i < A.length; i++) {
            A[i] = rand.nextInt();
        }
        Helper help = new Helper(A);
        long start = System.currentTimeMillis();
        help.preformOperationAndShowResultsComplexityN();
        System.out.println(System.currentTimeMillis() - start + " millis");
        
        start = System.currentTimeMillis();
        help.preformOperationAndShowResultsComplexityNLgN();
        System.out.println(System.currentTimeMillis() - start + " millis");
    }
    
}
