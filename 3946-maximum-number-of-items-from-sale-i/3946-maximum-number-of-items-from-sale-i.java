
import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {

        int n = items.length;

        int[][] arr = new int[n][3];

        for (int i = 0; i < n; i++) {

            int free = 0;

            for (int j = 0; j < n; j++) {

                if (i != j &&
                    items[j][0] % items[i][0] == 0) {

                    free++;
                }
            }

            arr[i][0] = items[i][1];
            arr[i][1] = free + 1;    
            arr[i][2] = items[i][0]; 
        }

        Arrays.sort(arr, (a, b) -> b[0] - a[0]);

        int[] dp = new int[budget + 1];

        Arrays.fill(dp, -1000000);
        dp[0] = 0;

        int ans = 0;
        int i = 0;

        while (i < n) {

            int price = arr[i][0];
            int[] has = new int[budget + 1];

            Arrays.fill(has, -1000000);

            int j = i;

            while (j < n && arr[j][0] == price) {

                int value = arr[j][1];

                for (int cost = budget; cost >= price; cost--) {

                    if (dp[cost - price] != -1000000) {

                        has[cost] = Math.max(
                            has[cost],
                            dp[cost - price] + value
                        );
                    }

                    if (has[cost - price] != -1000000) {

                        has[cost] = Math.max(
                            has[cost],
                            has[cost - price] + value
                        );
                    }
                }

                for (int cost = budget; cost >= price; cost--) {

                    if (dp[cost - price] != -1000000) {

                        dp[cost] = Math.max(
                            dp[cost],
                            dp[cost - price] + value
                        );
                    }
                }

                j++;
            }

            for (int cost = 0; cost <= budget; cost++) {

                if (has[cost] != -1000000) {

                    int extra = (budget - cost) / price;

                    ans = Math.max(
                        ans,
                        has[cost] + extra
                    );
                }
            }

            i = j;
        }

        return ans;
    }
}
