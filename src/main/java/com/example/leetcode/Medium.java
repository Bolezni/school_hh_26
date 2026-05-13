package com.example.leetcode;

import java.io.IOException;
import java.util.*;

public class Medium {
    static class TreeNode {
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

    public static void main(String[] args) throws IOException {

    }

    //logs[i] = [IDi, timei]IDitimei
    public int[] findingUsersActiveMinutes(int[][] logs, int k) {
        int[] res = new int[k];

        Map<Integer, Set<Integer>> userMinutes = new HashMap<>();

        for(int[] i: logs){
            int idx = i[0];
            int value = i[1];

            userMinutes.computeIfAbsent(idx, v -> new HashSet<>()).add(value);
        }

        for(Set<Integer> set: userMinutes.values()){
            res[set.size() - 1]++;
        }

        return res;
    }

    public String findDifferentBinaryString(String[] nums) {
        StringBuilder sb = new StringBuilder();

        for(int i =0;i<nums.length;i++)
        {
            if(nums[i].charAt(i) == '0')
            {
                sb.append('1');
            }
            else
            {
                sb.append('0');
            }
        }
        return sb.toString();
    }

    private static void generationBinaryString(String[] nums, List<Integer> result, int n, int idx){
        if(idx == nums.length){
            result.add(idx);
        }

        for(int i = idx; i < nums.length; i++){

        }


    }




    public static List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        inorder(root1, list1);
        inorder(root2, list2);

        return mergeSorted(list1, list2);
    }

    private static void inorder(TreeNode root, List<Integer> list) {
        if (root == null) return;
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    private static List<Integer> mergeSorted(List<Integer> l1, List<Integer> l2) {
        List<Integer> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < l1.size() && j < l2.size()) {
            if (l1.get(i) <= l2.get(j))
                result.add(l1.get(i++));
            else
                result.add(l2.get(j++));
        }

        while (i < l1.size())
            result.add(l1.get(i++));
        while (j < l2.size())
            result.add(l2.get(j++));

        return result;
    }




}

