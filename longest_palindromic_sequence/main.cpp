#include <iostream>
#include <cstring>
#include <algorithm>
#define UP 0
#define LEFT 1
#define DIAG 2

using namespace std;

int dp[10050][10050], memo[10050][10050], state[10050][10050];
string s, ss;

int topDown(int n, int m) {
    if(n == 0 || m == 0)
        return 0;
    if(memo[n][m] != -1)
        return memo[n][m];

    if(s[n - 1] == ss[m - 1]) {
        state[n][m] = DIAG;
        return memo[n][m] = topDown(n - 1, m - 1) + 1;
    }

    int up = topDown(n - 1, m);
    int left = topDown(n, m - 1);

    if(up > left) {
        state[n][m] = UP;
        memo[n][m] = up;
    }
    else {
        state[n][m] = LEFT;
        memo[n][m] = left;
    }

    return memo[n][m];

}

string printSequence(int i, int j) {
    if(i == 0 || j == 0) {
        return "";
    }
    string ret;
    if(state[i][j] == UP) {
        ret = printSequence(i - 1, j);
    }
    else if(state[i][j] == LEFT) {
        ret = printSequence(i, j - 1);
    }
    else {
        ret = s[i - 1] + printSequence(i - 1, j - 1);
    }

    return ret;
}

int main() {
    freopen("in.txt", "r", stdin);
    cin >> s;
    ss = s;
    reverse(ss.begin(), ss.end());
    int n = s.size();
    for(int i = 1; i <= n; i++) {
        for(int j = 1; j <= n; j++) {
            if(s[i - 1] == ss[j - 1]) {
                dp[i][j] = dp[i - 1][j - 1] + 1;
                state[i][j] = DIAG;
            }
            else {
                if(dp[i - 1][j] < dp[i][j - 1]) {
                    dp[i][j] = dp[i][j - 1];
                    state[i][j] = LEFT;
                }
                else {
                    dp[i][j] = dp[i - 1][j];
                    state[i][j] = UP;
                }
            }
        }
    }

    cout << "Maximum palindromic sequence of length " << dp[n][n] << endl;
    cout << printSequence(n, n) << endl;

    memset(memo, -1, sizeof memo);
    cout << "Maximum palindromic sequence of length " << topDown(n, n) << endl;
    cout << printSequence(n, n) << endl;


}
