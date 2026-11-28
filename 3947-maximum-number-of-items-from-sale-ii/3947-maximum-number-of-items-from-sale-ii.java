
import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {

        int n = items.length;
        int maxFactor = n;

        int minPrice = Integer.MAX_VALUE;
        int[] freq = new int[maxFactor + 1];

        for (int[] item : items) {
            freq[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        int[] total = new int[maxFactor + 1];

        for (int i = 1; i <= maxFactor; i++) {
            for (int j = i; j <= maxFactor; j += i) {
                total[i] += freq[j];
            }
        }

        Arrays.sort(items, (a, b) -> Integer.compare(a[1], b[1]));

        long remaining = budget;
        long ans = 0;

        for (int[] item : items) {

            int factor = item[0];
            int price = item[1];

            if (price >= 2L * minPrice) {
                break;
            }

            int gain = total[factor] - 1;

            if (gain == 0) {
                continue;
            }

            long take = Math.min(gain, remaining / price);

            ans += take * 2;
            remaining -= take * price;
        }

        ans += remaining / minPrice;

        return (int) ans;
    }
}
