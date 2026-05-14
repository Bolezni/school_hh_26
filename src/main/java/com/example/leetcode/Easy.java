package com.example.leetcode;

import java.util.*;

public class Easy {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        int[] nums = {3,5,2,3};
        minPairSum(nums);
    }

    //TODO: https://leetcode.com/problems/minimize-maximum-pair-sum-in-array/solutions/7518813/solution-by-la_castille-f2cy/
    public static int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        int n = nums.length;

        for (int i = 0; i < n >> 1; i++)
            res = Math.max(res, nums[n - 1 - i] + nums[i]);

        return res;
    }

    public String restoreString(String s, int[] indices) {

        char[] chars = new char[s.length()];

        for(int i = 0; i< indices.length; i++){
            chars[indices[i]] = s.charAt(i);
        }

        return new String(chars);
    }

    public static int[] findDegrees(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int[] ans = new int[n];


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 1) {
                    ans[i] += 1;
                }
            }
        }

        return ans;

    }

    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == null) return null;

        if (original == target) return cloned;

        TreeNode left = getTargetCopy(original.left, cloned.left, target);
        if (left != null) return left;
        else return getTargetCopy(original.right, cloned.right, target);
    }

    public int countDigits(int num) {
        int temp = num;
        int count = 0;
        while (temp != 0) {
            int last = temp % 10;
            if (last != 0 && num % last == 0) {
                count++;
            }
            temp /= 10;
        }
        return count;
    }

    public int sumOfMultiples(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 || i % 5 == 0 || i % 7 == 0) {
                count += i;
            }
        }
        return count;
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        Stack<TreeNode> stack = new Stack<>();

        for (int num : nums) {
            TreeNode node = new TreeNode(num);
            while (!stack.isEmpty() && stack.peek().val < num) {
                node.left = stack.pop();
            }
            if (!stack.isEmpty()) {
                stack.peek().right = node;
            }
            stack.push(node);
        }
        while (stack.size() > 1) {
            stack.pop();
        }
        return stack.peek();
    }


    private TreeNode addRecursive(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        if (root.val < val) {
            root.left = addRecursive(root.left, val);
        } else if (root.val > val) {
            root.right = addRecursive(root.right, val);
        }
        return root;
    }

    private static int findMax(int[] nums) {
        return Arrays.stream(nums).max().getAsInt();
    }

    // n - количество команд
    public static int numberOfMatches(int n) {
        return n - 1;
    }

    public int[] createTargetArray(int[] nums, int[] index) {
        if (nums.length != index.length) {
            return new int[0];
        }
        int n = nums.length;
        int size = 0;
        int[] res = new int[n];

        for (int i = 0; i < n; i++) {
            int pos = index[i];

            for (int j = size; j > pos; j--) {
                res[j] = res[j - 1];
            }

            res[pos] = nums[i];
            size++;
        }
        return res;
    }

    public int minOperations(int[] nums, int k) {
        int count = 0;
        for (int i : nums) {
            if (i < k) {
                count++;
            }
        }
        return count;
    }

    public String truncateSentence(String s, int k) {
        String[] words = s.split(" ");
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < k; i++) {
            res.append(words[i]);
            if (i < k - 1) {
                res.append(" ");
            }
        }
        return res.toString();
    }

    public List<Integer> stableMountains(int[] height, int threshold) {
        List<Integer> indexes = new ArrayList<>();

        int n = height.length;
        for (int i = 1; i < n; i++) {
            if (height[i - 1] > threshold) {
                indexes.add(i);
            }
        }
        return indexes;
    }

    public static int[] getFinalState(int[] nums, int k, int multiplier) {

        int n = nums.length;
        while (k-- > 0) {
            int idx = 0;
            int ans = nums[0];
            for (int j = 1; j < n; j++) {
                if (nums[j] < ans) {
                    ans = nums[j];
                    idx = j;
                }
            }
            nums[idx] = nums[idx] * multiplier;
        }

        return nums;
    }
}
