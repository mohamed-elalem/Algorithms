#include <iostream>
#include <cstdio>
#include <cstring>

using namespace std;

int dp[10000], state[10000];
string s;

int bottomUpUtils(int n) {
    for(int i = 0; i < n; i++) {
        dp[i] = 1;
    }
    memset(state, -1, sizeof state);

    for(int i = 1; i < n; i++) {
        for(int j = 0; j < i; j++) {
            if(s[i] > s[j] && dp[i] < dp[j] + 1) {
                dp[i] = dp[j] + 1;
                state[i] = j;
            }
        }
    }
    return dp[n - 1];
}

int topDownUtils(int n) {
    if(n == 0)
        return 1;
    if(dp[n] != -1)
        return dp[n];
    int lis = 1;
    for(int i = 0; i < n; i++) {
        int plis = topDownUtils(i);
        if(s[i] < s[n] && plis + 1 > lis) {
            lis = plis + 1;
            state[n] = i;
        }
    }
    return dp[n] = lis;
}

int topDown(int n) {
    memset(dp, -1, sizeof dp);
    memset(state, -1, sizeof state);
    return topDownUtils(n - 1);
}

string buildLIS(int n) {
    if(n == -1)
        return "";
    return buildLIS(state[n]) + s[n];
}

int main() {
    freopen("in.txt", "r", stdin);
    freopen("out.txt", "w", stdout);
    cin >> s;
    int top = topDown(s.size());
    string topS = buildLIS(s.size() - 1);
    int bottom = bottomUpUtils(s.size());
    string bottomS = buildLIS(s.size() - 1);
    cout << "TopDown Approach\n\n";
    cout << "LIS: " << top << endl;
    cout << "Sequence: " << topS << endl;
    cout << "\n\n\nBottomUp Approach\n\n";
    cout << "LIS: " << bottom << endl;
    cout << "Sequence: " << bottomS << endl;
}

