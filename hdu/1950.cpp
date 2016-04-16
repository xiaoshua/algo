#include<cstdio>
#include<algorithm>

using namespace std;
const int maxn = 40009;
int a[maxn];
int dp[maxn];
int dpLen;

int main() {
    int i, n, re, ri;
    scanf("%d", &re);

    for (ri = 1; ri <= re; ++ri) {
        scanf("%d", &n);

        for (i = 0; i < n; ++i) {
            scanf("%d", a + i);
        }

        dpLen = 0;

        for (i = 0; i < n; ++i) {
            int idx = lower_bound(dp, dp + dpLen, a[i]) - dp;
            dp[idx] = a[i];

            if (idx + 1 > dpLen) {
                dpLen = idx + 1;
            }
        }

        printf("%d\n", dpLen);
    }

    return 0;
}
