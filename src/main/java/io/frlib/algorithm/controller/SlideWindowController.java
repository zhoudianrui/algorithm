package io.frlib.algorithm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

@RestController
@RequestMapping("/window")
public class SlideWindowController {

    /**
     * t的每个字符都要出现在s的字串中，并且对应的字符数量s的字串更大
     * 如：s = 'abcdbcd' t = 'ccb', 返回cdbc即可
     *
     * @param s : 原串
     * @param t : 目标串
     * @return
     */
    @RequestMapping("/minSubString")
    public String minSubString(@RequestParam("s") String s, @RequestParam("t") String t) {
        HashMap<Character, Integer> needs = new HashMap<>(); // 目标元素
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            needs.put(c, needs.getOrDefault(c, 0) + 1);
        }
        HashMap<Character, Integer> windows = new HashMap<>(); // 窗口内元素
        int left = 0, right = 0; // 滑动窗口2端
        int valid = 0; // 字串内匹配的元素数
        int start = 0, len = Integer.MAX_VALUE; // 字串的开始位置与长度

        while (right < s.length()) {
            char c = s.charAt(right);
            right++; // 扩大窗口
            // 更新窗口数据
            if (needs.containsKey(c)) {
                windows.put(c, windows.getOrDefault(c, 0) + 1);
                if (windows.get(c).intValue() == needs.get(c).intValue()) {
                    valid++;
                }
            }

            //收缩窗口
            while (valid == needs.size()) {
                // 更新最小子串
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                char removeChar = s.charAt(left);
                left++;
                if (needs.containsKey(removeChar)) {
                    // 移除元素并更新窗口数据
                    if (windows.get(removeChar).intValue() == needs.get(removeChar).intValue()) {
                        valid--;
                    }
                    windows.put(removeChar, windows.get(removeChar) - 1);
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }

    /**
     * @param s : 原串
     * @param t : 目标串
     * @return
     */
    @RequestMapping("/stringSubOrder")
    public boolean stringSubOrder(@RequestParam("s") String s, @RequestParam("t") String t) {
        HashMap<Character, Integer> needs = new HashMap<>(); // 目标元素
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            needs.put(c, needs.getOrDefault(c, 0) + 1);
        }
        HashMap<Character, Integer> windows = new HashMap<>(); // 窗口内元素
        int left = 0, right = 0; // 滑动窗口2端
        int valid = 0; // 字串内匹配的元素数

        while (right < s.length()) {
            char c = s.charAt(right);
            right++; // 扩大窗口
            // 更新窗口数据
            if (needs.containsKey(c)) {
                windows.put(c, windows.getOrDefault(c, 0) + 1);
                if (windows.get(c).intValue() == needs.get(c).intValue()) {
                    valid++;
                }
            }

            //收缩窗口
            if (right - left >= t.length()) { // 确保窗口大小
                // 更新最小子串
                if (valid == needs.size()) {
                    return true;
                }
                char removeChar = s.charAt(left);
                left++;
                if (needs.containsKey(removeChar)) {
                    // 移除元素并更新窗口数据
                    if (windows.get(removeChar).intValue() == needs.get(removeChar).intValue()) {
                        valid--;
                    }
                    windows.put(removeChar, windows.getOrDefault(removeChar, 0) - 1);
                }
            }
        }
        return false;
    }

    @RequestMapping("/maxLengthOfLimitSubArray")
    public int maxLengthOfLimitSubArray(@RequestParam("nums") int[] nums, @RequestParam("limit") int limit) {
        int left = 0, right = 0;
        int length = 0;
        PriorityQueue<Integer> big = new PriorityQueue<>((a, b) -> b - a);
        PriorityQueue<Integer> small = new PriorityQueue<>((a, b) -> a - b);
        while (right < nums.length) {
            big.add(nums[right]);
            small.add(nums[right]);
            right++;
            while (big.peek().intValue() - small.peek().intValue() > limit) {
                big.remove(nums[left]);
                small.remove(nums[left]);
                left++;
            }
            length = Math.max(length, right - left);
        }
        return length;
    }
}
