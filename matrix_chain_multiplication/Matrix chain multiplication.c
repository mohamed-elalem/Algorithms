/*
 ============================================================================
 Name        : Matrix.c
 Author      : Mohamed El-alem
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_INT 0x7FFFFFFE

int m[1000][1000], s[1000][1000], p[1000];
int n, i, j, k, l, q;

void printSolution(int i, int j) {
	if(i == j) {
		printf("A[%d]", i);
	}
	else {
		printf("( ");
		printSolution(i, s[i][j]);
		printf(" * ");
		printSolution(s[i][j] + 1, j);
		printf(" )");
	}
}

void bottomUp() {
	for(l = 2; l <= n; l++) {
		for(i = 1; i <= n - l + 1; i++) {
			j = i + l - 1;
			m[i][j] = MAX_INT;
			for(k = i; k < j; k++) {
				q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
				if(q < m[i][j]) {
					m[i][j] = q;
					s[i][j] = k;
				}
			}
		}
	}
}

int topDown(int i, int j) {
	if(i == j)
		return 0;
	if(m[i][j] != -1) {
		return m[i][j];
	}
	int ans = MAX_INT;
	int k;
	for(k = i; k < j; k++) {
		int q = topDown(i, k) + topDown(k + 1, j) + p[i - 1] * p[k] * p[j];
		if(q < ans) {
			ans = q;
			s[i][j] = k;
		}
	}
	return m[i][j] = ans;
}


int main(void) {
	printf("Enter the the number of matrices: ");
	scanf("%d", &n);
	printf("Enter the dimensions (n + 1): ");
	for(i = 0; i <= n; i++) {
		scanf("%d", &p[i]);
//		m[i][i] = 0;
	}

	memset(m, -1, sizeof(m));

	topDown(1, n);

	printf("The smallest order of multiplication for the provided matrices is %d\n", m[1][n]);
	printf("Solution is: ");
	printSolution(1, n);
	printf("\t\n");
	return EXIT_SUCCESS;
}
