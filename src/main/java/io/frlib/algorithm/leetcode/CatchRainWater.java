package io.frlib.algorithm.leetcode;

/**
 * 接雨水
 */
public class CatchRainWater {

    /**
     * 题源：leetcode 42
     * 要求：给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水)
     * 提示：
     * 1. n == height.length
     * 2. 1 <= n <= 2 * 104
     * 3. 0 <= height[i] <= 105
     * @param height
     * @return
     */
    public int trap(int[] height) {
        // 计算每个元素左右的最高（leftMax[i], rightMax[i])，然后计算最小值与height[i]之间的差值保存在至结果
        int n = height.length;
        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n-1];
        for(int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i-1], height[i]);
        }
        for(int i = n-2; i >=0; i--) {
            rightMax[i] = Math.max(rightMax[i+1], height[i]);
        }
        int result = 0;
        for (int i = 0; i < n; i ++) {
            int top = Math.min(leftMax[i], rightMax[i]);
            if (top > height[i]) {
                result += top - height[i];
            }
        }
        return result;
    }

    /**
     * leetcode 42: 优化空间复杂度
     * @param height
     * @return
     */
    public int newTrap(int[] height) {
        // 计算每个元素左右的最高（leftMax[i], rightMax[i])，然后计算最小值与height[i]之间的差值保存在至结果
        int n = height.length;
        int leftMax = 0, rightMax = 0;
        int result = 0;
        int left = 0, right = n-1;
        while(left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (leftMax < rightMax) {
                result += leftMax - height[left];
                left++;
            } else {
                result += rightMax - height[right];
                right--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};// result = 6
        //int[] height = {4,2,0,3,2,5};// result = 9
        CatchRainWater instance = new CatchRainWater();
        int result = instance.newTrap(height);
        System.out.println(result);
    }
}
