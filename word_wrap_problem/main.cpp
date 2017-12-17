#include <iostream>
#include <cstdlib>
#include <cstring>
#include <stack>
#include <cstdio>

using namespace std;

int dp[100000], p[100000];
string words[100000];
int window = 80;
int wordCount = 0;

int pow2(int n) {
    return n * n;
}

void solve() {
    dp[0] = 0;
    for(int i = 1; i <= wordCount; i++) {
        int curLength = words[i - 1].size();
//        cout << curLength << " ";
        dp[i] = dp[i - 1] + pow2(window - curLength);
        p[i] = i - 1;
        for(int j = i - 1; j > 0; j--) {
            curLength += words[j - 1].size() + 1;
            if(curLength > window) {
                break;
            }
//            cout << curLength << " ";
            int state = dp[j - 1] + pow2(window - curLength);
            if(state < dp[i]) {
                dp[i] = state;
                p[i] = j - 1;
            }
        }
//        cout << " | " << dp[i] << endl;
    }

}

int main()
{
//    freopen("in.txt", "r", stdin);
//    freopen("out.txt", "w", stdout);

    while(!cin.eof()) {
        cin >> words[wordCount++];
    }

    memset(p, -1, sizeof p);
    for(int i = 0; i < 10000; i++) {
        dp[i] = 0x9FFFFFF;
    }
    solve();

    stack<int> lineBreaks;

    for(int i = wordCount - 1; i != -1; i = p[i]) {
//        cout << i << endl;
        lineBreaks.push(i);
    }
    for(int i = 0; i < wordCount; i++) {
        if(lineBreaks.top() == i) {
            lineBreaks.pop();
            cout << endl;
        }
        else if(i > 0){
            cout << " ";
        }
        cout << words[i];

    }

}
