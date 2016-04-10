#include<cstdio>
#include<algorithm>
using namespace std;
const int maxn = 1 << 10;
int a[maxn], dp[maxn];
int main() {
	int i, j, n, ans;

	while(scanf("%d", &n) != EOF) {
		for(i = 0; i < n; ++i) {
			scanf("%d", a + i);
		}

		fill(dp, dp + n, 1);

		for(i = 0; i < n; ++i) {
			for(j = 0; j < i; ++j) {
				if(a[j] < a[i]) {
					dp[i] = max(dp[i], dp[j] + 1);
				}
			}
		}

		ans = *max_element(dp, dp + n);
		printf("%d\n", ans);
	}

	return 0;
}

