package com.example;

import java.util.*;

public class Tasks {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(plusOne(new int[]{1})));
    }



    public static int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if(digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        int[] newDigits = new int[digits.length + 1];
        newDigits[0] = 1;
        return newDigits;
    }

    public int lengthOfLastWord(String s) {
        String[] words = s.split(" ");
        return words[words.length - 1].length();
    }

    public static int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                left = mid + 1;
            } else
                right = mid - 1;

        }

        return left;
    }

    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1)
            return Integer.MAX_VALUE;
        return dividend / divisor;
    }

    public static int strStr(String haystack, String needle) {
        for (int i = 0, j = needle.length(); j <= haystack.length(); i++, j++) {
            String sub = haystack.substring(i, j);
            if (sub.equals(needle))
                return i;
        }
        return -1;
    }

    public static boolean isArraySpecial(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] % 2 == nums[i + 1] % 2)
                return false;
        }

        return true;
    }

    public static int removeElement(int[] nums, int val) {
        if (nums.length == 0)
            return 0;

        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[index] = nums[i];
                index++;
            }
        }
        return index;
    }

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generate(result, "", 0, 0, n);
        return result;
    }

    private void generate(List<String> result, String current, int open, int close, int max) {
        if (current.length() == 2 * max) {
            result.add(current);
            return;
        }

        if (open < max) {
            generate(result, current + "(", open + 1, close, max);
        }

        if (close < open) {
            generate(result, current + ")", open, close + 1, max);
        }
    }

    public static int removeDuplicates2(int[] nums) {
        if (nums.length == 0) return 0;

        int currentIndex = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[currentIndex] = nums[i];
                currentIndex++;
            }
        }
        return currentIndex;
    }

    public static int removeDuplicates(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!set.contains(nums[i])) {
                count++;
                set.add(nums[i]);
            }
        }
        return count;
    }

    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (head != null && head.next != null) {
            ListNode first = head;
            ListNode second = head.next;

            first.next = second.next;
            second.next = first;
            prev.next = second;

            prev = first;
            head = first.next;
        }
        return dummy;
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummyHead = new ListNode();
        ListNode cur = dummyHead;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                cur.next = list1;
                cur = cur.next;
                list1 = list1.next;
            } else {
                cur.next = list2;
                cur = cur.next;
                list2 = list2.next;
            }
        }
        if (list1 != null) {
            cur.next = list1;
        }
        if (list2 != null) {
            cur.next = list2;
        }
        return dummyHead.next;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}