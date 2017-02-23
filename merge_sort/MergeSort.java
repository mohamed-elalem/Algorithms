import java.util.Arrays;
import java.util.Random;

public class MergeSort {

	
	public static void MergeSort(int [] A, int lo, int hi) {
		if(hi - lo >= 1) {
			int mid = (lo + hi) >> 1;
			//System.out.println(lo + " " + mid + " " + hi);		
			MergeSort(A, lo, mid);
			MergeSort(A, mid + 1, hi);
			sort(A, lo, mid, hi);
		}
	}
	
	public static void sort(int [] A, int lo, int mid, int hi) {
		int n1 = mid - lo + 1;
		int n2 = hi - mid;
		//System.out.println(n1 + " " + n2 + " | " + lo + " " + mid + " " + hi);
		//System.out.println(n1 + " " + n2);
		int [] l = new int[n1];
		int [] r = new int[n2];
		for(int i = 0; i < n1; i++) {
			l[i] = A[lo + i];
		}
		for(int i = 0; i < n2; i++) {
			r[i] = A[mid + i + 1];
		}
		int i = 0, j = 0, k = lo;
		while(i < n1 && j < n2) {
			if(l[i] < r[j]) {
				A[k] = l[i];
				i += 1;
			}
			else {
				A[k] = r[j];
				j += 1;
			}
			k += 1;
		}
		while(i < n1) {
			A[k] = l[i];
			k += 1;
			i += 1;
		}
		while(j < n2) {
			A[k] = r[j];
			k += 1;
			j += 1;
		}
	}
	
	
	public static void main(String[] args) {
		Random rand = new Random();
		int n = 200;
		int [] x = new int[n];
		for(int i = 0; i < n; i++) {
			x[i] = rand.nextInt(10000000);
		}
		int [] clone = x.clone();
		Arrays.sort(clone);
		System.out.println("Original " + Arrays.toString(x));
		MergeSort(x, 0, n - 1);
		System.out.println("Sorted " + Arrays.toString(x));
		System.out.println("System Sorted " + Arrays.toString(clone));
		int s = 0;
		for(int i = 0; i < n; i++) {
			s += Math.abs(x[i] - clone[i]);
		}
		System.out.println("Sum of difference is " + s);

	}

}
