package io.frlib.algorithm.classic;

import java.util.*;

/**
 * 回溯算法
 */
public class Backtrack {

    private List<List<Integer>> result = new ArrayList<>(); // 结果集
    private int[] nums;

    Backtrack(int[] nums) {
        this.nums = nums;
    }

    /**
     * 全排列(元素不重复、不复选)
     * 如：{1,2,3} =>[123,132,213,231,312,321]
     */
    public void permutation() {
        if (nums == null || nums.length == 0) return;
        LinkedList<Integer> track = new LinkedList<>();
        boolean[] used = new boolean[nums.length]; // 备忘录
        backtrack(used, track);
        System.out.println(result);
    }

    private void backtrack(boolean[] used, LinkedList<Integer> track) {
        // base case
        if (track.size() == nums.length) {
            result.add(new ArrayList<>(track));
            return;
        }
        for(int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            // 做选择
            track.add(nums[i]);
            used[i] = true;
            // 递归
            backtrack(used, track);
            // 撤销选择
            track.removeLast();
            used[i] = false;
        }
    }

    /**
     * 子集(元素不重复、不复选)
     */
    public void subset() {
        if (nums == null || nums.length == 0) return;
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(0, track);
        System.out.println(result);
    }

    private void backtrack(int start, LinkedList<Integer> track) {
        // base case
        result.add(new LinkedList<>(track));
        for (int i = start; i < nums.length; i++) {
            // 做选择
            track.add(nums[i]);
            backtrack(i + 1, track);
            // 撤销选择
            track.removeLast();
        }
    }

    /**
     * 组合
     * @param length
     */
    public void combination(int length) {
        if (nums == null || nums.length == 0) return;
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(0, track, length);
        System.out.println(result);
    }

    private void backtrack(int start, LinkedList<Integer> track, int length) {
        // base case
        if (track.size() == length) {
            result.add(new LinkedList<>(track));
            return;
        }

        for (int i = start; i < nums.length; i++) {
            // 做选择
            track.add(nums[i]);
            backtrack(i + 1, track, length);
            // 撤销选择
            track.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1,2,3};
        Backtrack backtrack = new Backtrack(nums);
        //backtrack.permutation(); // 全排列
        //backtrack.subset(); // 子集
        backtrack.combination(2); // 组合
    }
}
