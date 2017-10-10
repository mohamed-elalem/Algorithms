#include <iostream>

using namespace std;

int s[] = {1, 3, 0, 5, 3, 5, 6 , 8 , 8 , 2 , 12};
int f[] = {4, 5, 6, 7, 9, 9, 10, 11, 12, 14, 16};

int recursiveActivitySelector(int s[], int f[], int k, int n) {
    int m = k + 1;
    while(m < n && s[m] < f[k])
        m++;
    if(m < n) {
        return 1 + recursiveActivitySelector(s, f, m, n);
    }
    return 0;
}


int main()
{
    int n = 11;
    int A = 1;
    int k = 0;
    for(int m = 1; m < n; m++) {
        if(s[m] > f[k]) {
            cout << s[m] << " " << f[m] << endl;
            A++;
            k = m;
        }
    }
    cout << A << " " << 1 + recursiveActivitySelector(s, f, 1, n) << endl;
}
