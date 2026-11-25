
import java.util.*;

class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        int N = n + 1;

        // Coordinate compression
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int[] a = new int[N];

        for (int i = 0; i < n; i++) {
            a[i] = Arrays.binarySearch(sorted, nums[i]) + 1;
        }
        a[n] = 0; // Unique smallest sentinel

        // Build Suffix Array
        int[] sa = new int[N];
        int[] c = new int[N];
        int[] cnt = new int[N];

        for (int x : a) cnt[x]++;

        for (int i = 1; i < N; i++) {
            cnt[i] += cnt[i - 1];
        }

        for (int i = N - 1; i >= 0; i--) {
            sa[--cnt[a[i]]] = i;
        }

        int classes = 1;
        c[sa[0]] = 0;

        for (int i = 1; i < N; i++) {
            if (a[sa[i]] != a[sa[i - 1]]) {
                classes++;
            }
            c[sa[i]] = classes - 1;
        }

        int[] pn = new int[N];
        int[] cn = new int[N];

        for (int len = 1; len < N; len <<= 1) {

            for (int i = 0; i < N; i++) {
                pn[i] = sa[i] - len;
                if (pn[i] < 0) pn[i] += N;
            }

            Arrays.fill(cnt, 0);

            for (int i = 0; i < N; i++) {
                cnt[c[pn[i]]]++;
            }

            for (int i = 1; i < N; i++) {
                cnt[i] += cnt[i - 1];
            }

            for (int i = N - 1; i >= 0; i--) {
                int x = pn[i];
                sa[--cnt[c[x]]] = x;
            }

            cn[sa[0]] = 0;
            int newClasses = 1;

            for (int i = 1; i < N; i++) {
                int cur = sa[i];
                int prev = sa[i - 1];

                int mid1 = (cur + len) % N;
                int mid2 = (prev + len) % N;

                if (c[cur] != c[prev] ||
                    c[mid1] != c[mid2]) {
                    newClasses++;
                }

                cn[cur] = newClasses - 1;
            }

            int[] temp = c;
            c = cn;
            cn = temp;

            classes = newClasses;

            if (classes == N) break;
        }

        // Build inverse suffix array
        int[] pos = new int[N];

        for (int i = 0; i < N; i++) {
            pos[sa[i]] = i;
        }

        // Kasai algorithm: Build LCP array
        int[] lcp = new int[N];
        int h = 0;

        for (int i = 0; i < n; i++) {
            int r = pos[i];

            if (r == 0) continue;

            int j = sa[r - 1];

            while (i + h < n &&
                   j + h < n &&
                   nums[i + h] == nums[j + h]) {
                h++;
            }

            lcp[r] = h;

            if (h > 0) h--;
        }

        // Find minimum unique subarray length
        int ans = n;

        for (int i = 0; i < n; i++) {
            int r = pos[i];

            int maxLcp = lcp[r];

            if (r + 1 < N) {
                maxLcp = Math.max(maxLcp, lcp[r + 1]);
            }

            int uniqueLength = maxLcp + 1;

            if (uniqueLength <= n - i) {
                ans = Math.min(ans, uniqueLength);
            }
        }

        return ans;
    }
}
