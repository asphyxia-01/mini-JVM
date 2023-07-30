package tzy.tinyPros.JVM08;/*
 * @lc app=leetcode.cn id=5 lang=java
 *
 * [5] 最长回文子串
 */

// @lc code=start
public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().longestPalindrome("abcdbcbacaaaaaaanmnnnnmnnnn"));
    }

    public String longestPalindrome(String s) {
        int maxLen = 1;
        int begin = 0; // 截取的子串的初始位置
        int len = s.length(); // 字符串的长度
        boolean dp[][] = new boolean[len][len]; // 判断当前位置是否是回文状态
        for (int k = 0; k < s.length(); k++)
            dp[k][k] = true; // 只有一个数的时候一定是回文数
        // 从右端开始考虑
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                // 当前i和j都是考虑的可能是回文字符串的两头
                if (s.charAt(i) != s.charAt(j))
                    dp[i][j] = false;
                else {
                    if (j - i < 3)
                        dp[i][j] = true;
                    else {
                        dp[i][j] = dp[i + 1][j - 1]; // 内部是回文就true,不是就是false
                    }
                }
                if (dp[i][j] == true && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }
}
// @lc code=end
