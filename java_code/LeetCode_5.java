package java_code;

/**
 * @description: LeetCode第五题：最长回文子串
 *              (Longest Palindromic Substring)
 * @author: zhuganjun
 * @time: 2025/7/22
 * @version:
 */

/**
 * 从思路上来说，就是找回文子串的中心
 * 这里要注意，回文子串可能是奇数串，也可能是偶数串
 * 这题的难度就在于处理偶数串上
 *
 * 此外，这题还可以用动态规划去做，动态规划和上面找中心的做法，区别在于：
 * 动态规划是从大到小，找中心是从小到大
 */
public class LeetCode_5 {
    /**
     * 第一种实现方式，找中心的方式
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s.length() == 1) {
            return s;
        }
        int maxLen = 1;
        int head = 0;
        int tail = 0;

        for (int i = 0; i < s.length() - 1; i++) {
            // 奇数长度的回文子串
            int len = substringLength(s, i, i);
            if (maxLen < len) {
                maxLen = len;
                head = (i - (len - 1) / 2);
                tail = (i + (len - 1) / 2);
            }

            // 偶数长度的回文子串
            if (s.charAt(i) == s.charAt(i+1)) {
                len = substringLength(s, i, i+1);
                if (maxLen < len) {
                    maxLen = len;
                    head = (i - (len - 1) / 2);
                    tail = (i + len / 2);
                }
            }
        }
        // 因为substring包左不包右，所以tail要+1
        return s.substring(head, tail + 1);
    }

    public int substringLength(String s, int head, int tail) {

        if (head < 0 || tail >= s.length()) {
            return (tail - head - 1);
        }

        while(head >= 0 && tail < s.length()) {
            if(s.charAt(head) == s.charAt(tail)) {
                head--;
                tail++;
            } else {
                break;
            }
        }
        return (tail - head - 1);
    }
}
