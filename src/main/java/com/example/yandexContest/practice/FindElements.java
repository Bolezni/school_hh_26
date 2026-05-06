package com.example.yandexContest.practice;

import java.util.HashSet;
import java.util.Set;

public class FindElements {
    public static class TreeNode {
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

    private Set<Integer> recoveredValues;

    public FindElements(TreeNode root) {
        recoveredValues = new HashSet<>();
        if (root != null)
            recover(root, 0);
    }

    public boolean find(int target) {
        return recoveredValues.contains(target);
    }

    private void recover(TreeNode root, int value) {
        if (root == null) return;

        root.val = value;

        recoveredValues.add(value);

        recover(root.left, 2 * value + 1);
        recover(root.right, 2 * value + 2);


    }
}
