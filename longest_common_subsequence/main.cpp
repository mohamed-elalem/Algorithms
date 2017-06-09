#include <iostream>
#include <cstring>
#include <cstdlib>
#include <algorithm>

#define DIAG 0
#define UP 1
#define LEFT 2

using namespace std;

int c[10010][10010], b[10010][10010];

string s, ss;

string getLCS(int i, int j) {
    if(i == 0 || j == 0) {
        return "";
    }
    else {
        if(b[i][j] == UP) {
            return getLCS(i - 1, j);
        }
        else if(b[i][j] == LEFT) {
            return getLCS(i, j  - 1);
        }
        else {
            return getLCS(i - 1, j - 1) + s[i - 1];
        }
    }
}

int memoized_lcs(int i, int j) {
    if(i == 0 || j == 0) {
        return 0;
    }
    if(c[i][j] != -1) {
        return c[i][j];
    }
    if(s[i - 1] == ss[j - 1]) {
        b[i][j] = DIAG;
        return 1 + memoized_lcs(i - 1, j - 1);
    }
    else {
        int up = memoized_lcs(i - 1, j);
        int left = memoized_lcs(i, j - 1);
        if(up < left) {
            b[i][j] = LEFT;
            return c[i][j] = left;
        }
        else {
            b[i][j] = UP;
            return c[i][j] = up;
        }
    }
}

int main() {
    freopen("in.txt", "r", stdin);
    cin >> s >> ss;

    cout << "Bottom Up\n=====================================\n\n";

    memset(c, 0, sizeof c);

    for(int i = 1; i <= s.size(); i++) {
        for(int j = 1; j <= ss.size(); j++) {
            if(s[i - 1] == ss[j - 1]) {
                c[i][j] = c[i - 1][j - 1] + 1;
                b[i][j] = DIAG;
            }
            else {
                if(c[i][j - 1] <= c[i - 1][j]) {
                    c[i][j] = c[i - 1][j];
                    b[i][j] = UP;
                }
                else {
                    c[i][j] = c[i][j - 1];
                    b[i][j] = LEFT;
                }
            }
        }
    }

    cout << "Longest increasing subsequence is '" << getLCS(s.size(), ss.size()) << "' and length " << c[s.size()][ss.size()] << endl;


    cout << "=====================================\n\nTop Down\n=====================================\n\n";
    memset(c, -1, sizeof c);
    int length = memoized_lcs(s.size(), ss.size());
    cout << "Longest increasing subsequence is '" << getLCS(s.size(), ss.size())  << "' and length " << length << endl;
}
