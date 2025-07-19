import java.util.HashMap;
import java.util.Map;

/**
 * @description: leetcode第三题：无重复字符的最长子串
 *    （Longest Substring Without Repeating Characters）
 * @author: zhuganjun
 * @time: 2025/7/18
 * @version:
 */

public class LeetCode_3 {

    public int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1) {
            return s.length();
        }

        Map<Character, Integer> map = new HashMap<>();

        int head = 0;
        map.put(s.charAt(0), 0);
        int res = 1;

        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            // 注意：这里会出现问题，因为本质上没有删除map里的数据，head前的数据可能会重复
            if (map.get(c) == null || map.get(c) < head) {
                map.put(c, i);
            } else {
                head = map.get(c) + 1;
                map.put(c, i);
            }
            res = res < (i - head + 1) ? (i - head + 1) : res;
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode_3 leetcode = new LeetCode_3();
        System.out.println(leetcode.lengthOfLongestSubstring("abba"));
    }
}


/**
 * 总结：这个题目，做了很久，其实思路很清晰，但是卡住了，因为“abba”这个case，
 * 在遇到第二个b的时候，head被设为了2， 接着遇到a，因为没有删除map里的数据，所以head又被设为了1
 */


/**
 * 官方题解：滑动窗口，思路和我的思路类似，但是更清晰一些
 * 由一头一尾两个指针，尾指针向右每移动一次，都做一次判断，是否有重复字符（使用set来保存）
 * 有重复字符的话，将头指针向右移动，每一移动一次，都从set中删除一个元素，直至不重复。
 */