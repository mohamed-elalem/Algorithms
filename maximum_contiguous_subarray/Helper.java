/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maximumcontiguoussubarray;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mohamed
 */
public class Helper {
    private double [] arr;
    
    private class Sub implements Cloneable {
        private int left, right;
        private double sum;

        public Sub(int left, int right, double sum) {
            this.left = left;
            this.right = right;
            this.sum = sum;
        }
        
        public int getLeft() {
            return left;
        }

        public int getRight() {
            return right;
        }

        public double getSum() {
            return sum;
        }
        
        public Sub max(Sub other) {
            if(this.sum >= other.sum)
                return this;
            return other;
        }
        
        @Override
        public String toString() {
            return "(" + left + ", " + right + ") with sum of " + sum;
        }

        public void setLeft(int left) {
            this.left = left;
        }

        public void setRight(int right) {
            this.right = right;
        }

        public void setSum(double sum) {
            this.sum = sum;
        }
        
        public void addSum(double sum) {
            this.sum += sum;
        }
        
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    public Helper(double[] arr) {
        this.arr = arr;
    }
    
    public void preformOperationAndShowResultsComplexityN() {
        try {
            System.out.println(kadane());
        } catch (Exception ex) {
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void preformOperationAndShowResultsComplexityNLgN() {
        System.out.println(maximumSubArray(0, arr.length - 1));
    }
    
    private Sub kadane() throws CloneNotSupportedException {
        Sub maxSub = new Sub(0, 0, Double.NEGATIVE_INFINITY);
        Sub sum = new Sub(0, 0, 0);
        for(int i = 0; i < arr.length; i++) {
            sum.addSum(arr[i]);
            sum.setRight(i);
            if(sum.getSum() > maxSub.sum) {
                maxSub = (Sub) sum.clone();
            }
            if(sum.getSum() < 0) {
                sum.setSum(0);
                sum.setLeft(i + 1);                
            }
        }
        return maxSub;
    }
    
    private Sub maximumSubArray(int low, int high) {
        if(low == high) {
            return new Sub(low, high, arr[low]);
        }
        int mid = (low + high) / 2;
        Sub maxLeftSubArray = maximumSubArray(low, mid);
        Sub maxRightSubArray = maximumSubArray(mid + 1, high);
        Sub maxCrossingSubArray = maxCrossing(low, mid, high);
        //System.out.println("Comparing " + maxLeftSubArray + ", " + maxRightSubArray + ", " + maxCrossingSubArray + " Got " + maxLeftSubArray.max(maxRightSubArray).max(maxCrossingSubArray));
        return maxLeftSubArray.max(maxRightSubArray).max(maxCrossingSubArray);
    }
    
    private Sub maxCrossing(int low, int mid, int high) {
        double leftSum = 0;
        double sum = 0;
        int left = -1;
        double rightSum = 0;
        int right = -1;
        for(int i = mid; i >= low; i--) {
            sum += arr[i];
            if(sum > leftSum) {
                leftSum = sum;
                left = i;
            }
        }
        
        sum = 0;
        for(int i = mid + 1; i <= high; i++) {
            sum += arr[i];
            if(sum > rightSum) {
                rightSum = sum;
                right = i;
            }
        }
        
        //System.out.println(new Sub(left, right, sum));
        
        return new Sub(left, right, leftSum + rightSum);
    }
    
}
