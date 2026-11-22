class Solution {
    public String simplifyPath(String path) {
        Stack<String> st = new Stack<>();

        for (String dir : path.split("/")) {

            if (!st.isEmpty() && dir.equals("..")) {
                st.pop();
            }
            else if (dir.equals(".") || dir.equals("")) {
                continue;
            }
            else if (!dir.equals("..")) {
                st.push(dir);
            }
        }
        String ans = "";
        for (String dir : st) {
            ans += "/";
            ans += dir;
        }
        return ans.isEmpty() ? "/" : ans;
    }
}