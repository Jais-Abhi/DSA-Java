
class Solution {
    int[] ans;
    int[] last = new int[26];
    List<Integer>[] graph;
    List<Integer>[] newGraph;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        graph = new ArrayList[n];
        newGraph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            newGraph[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            graph[parent[i]].add(i);
        }

        ans = new int[n];

        Arrays.fill(last, -1);

        dfs1(0, parent, s);

        dfs2(0);

        return ans;
    }

    void dfs1(int node, int[] parent, String s) {
        int ch = s.charAt(node) - 'a';

        int old = last[ch];

        int newParent = (old == -1) ? parent[node] : old;

        if (node != 0) {
            newGraph[newParent].add(node);
        }

        last[ch] = node;

        for (int child : graph[node]) {
            dfs1(child, parent, s);
        }

        last[ch] = old;
    }

    int dfs2(int node) {
        int size = 1;

        for (int child : newGraph[node]) {
            size += dfs2(child);
        }

        ans[node] = size;

        return size;
    }
}
