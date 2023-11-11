package io.frlib.algorithm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/array")
public class ArrayDomainController {

    @RequestMapping("/palindrome")
    public String longestPalindrome(@RequestParam("s") String s) {
        int from = 0;
        int maxLength = 1;
        for (int i = 0; i < s.length() - 1; i++) {
            // 奇偶性
            int oddLength = computerLength(s, i, i);
            int evenLength = computerLength(s, i, i+1);
            int currentLength = Math.max(oddLength, evenLength);
            if (currentLength > maxLength) {
                maxLength = currentLength;
                from = i - (maxLength - 1) / 2;
            }
        }
        return s.substring(from, from + maxLength);
    }

    int computerLength(String s, int from, int end) {
        while (from >= 0 && end < s.length()) {
            if (s.charAt(from) == s.charAt(end)) {
                from--;
                end++;
            } else {
                break;
            }
        }
        return end-from-1;
    }
}
