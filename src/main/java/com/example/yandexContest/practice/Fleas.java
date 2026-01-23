package com.example.yandexContest.practice;

import java.util.*;

public class Fleas {

    private static class Pair {
        Integer first;
        Integer second;

        public Pair(Integer first, Integer second) {
            this.first = first;
            this.second = second;
        }
    }

    public class ListNode {
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
    }

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
        int[] a = {1,3,2,4};
        int[] b = {3,1,2,4};

        int[] res = findThePrefixCommonArray(a,b);

        System.out.println(Arrays.toString(res));

    }

    public static int[] findThePrefixCommonArray(int[] A, int[] B) {

        int n = A.length;
        int[] counter = new int[n+1];
        int[] res = new int[n];
        int common = 0;

        for (int i = 0; i < n; i++) {
            if(++counter[A[i]] == 2) common++;
            if(++counter[B[i]] == 2) common++;
            res[i] = common;
        }
        return res;
    }

    public static String equationWithRoot(int a, int b, int c){
        if (a == 0) {
            if (b < 0) {
                return "NO SOLUTION";
            }
            if (Math.sqrt(b) == c) {
                return "MANY SOLUTIONS";
            } else {
                return "NO SOLUTION";
            }
        }

        if (c < 0) {
            return "NO SOLUTION";
        }

        int rightSide = c * c - b;

        if (rightSide % a != 0) {
            return "NO SOLUTION";
        }

        int x = rightSide / a;

        if (a * x + b < 0) {
            return "NO SOLUTION";
        }

        if (Math.sqrt(a * x + b) != c) {
            return "NO SOLUTION";
        }

        return String.valueOf(x);
    }


    public static List<String> validStrings(int n) {
        List<String> result = new ArrayList<>();
        generateValidStrings(n, new StringBuilder(), 1, result);
        return result;
    }

    private static void generateValidStrings(int n, StringBuilder str, int prev, List<String> result) {
        if(str.length() == n){
            result.add(str.toString());
            return;
        }

        str.append("1");
        generateValidStrings(n, str, 1, result);
        str.deleteCharAt(str.length()-1);

        if(prev == 1){
            str.append("0");
            generateValidStrings(n, str, 0, result);
            str.deleteCharAt(str.length()-1);
        }
    }

    public int[] recoverOrder(int[] order, int[] friends) {
        int[] res = new int[friends.length];
        int index = 0;
        for (int currentOrder : order) {
            for (int friend : friends) {
                if (currentOrder == friend) {
                    res[index] = friend;
                    index++;
                }
            }
        }
        return res;
    }

    public int finalValueAfterOperations(String[] operations) {
        int x = 0;
        for (String operation : operations) {
            switch (operation.toLowerCase()) {
                case "x++":
                    x++;
                    break;
                case "++x":
                    ++x;
                    break;
                case "x--":
                    x--;
                    break;
                case "--x":
                    --x;
                    break;
            }
        }
        return x;
    }

    public static int[] getConcatenation(int[] nums) {
        int n = nums.length;
        int[] res = new int[n * 2];
        int resLength = res.length;

        int j = 0;
        for (int i = 0; i < resLength; i++) {
            if (j == n) {
                j = 0;
            }
            res[i] = nums[j++];

        }
        return res;
    }

    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < groupSizes.length; i++) {
            int size = groupSizes[i];
            map.putIfAbsent(size, new ArrayList<>());
            map.get(size).add(i);
            if (map.get(size).size() == size) {
                res.add(new ArrayList<>(map.get(size)));
                map.get(size).clear();
            }
        }
        return res;
    }

    int sum = 0;

    public TreeNode bstToGst(TreeNode root) {
        if (root != null) {
            bstToGst(root.right);
            sum += root.val;
            root.val = sum;
            bstToGst(root.left);
        }
        return root;
    }

    public int minPartitions(String n) {
        int max = 0;
        for (int i = 0; i < n.length(); i++) {
            if (n.charAt(i) - '0' == '9') return 9;
            max = Math.max(max, (n.charAt(i) - '0'));
        }
        return max;
    }


    public static int disassembleIntoCubes(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;

            for (int k = 1; k * k * k <= i; k++) {
                int cube = k * k * k;
                dp[i] = Math.min(dp[i], dp[i - cube] + 1);
            }
        }
        return dp[n];
    }

    public int countMaxOrSubsets(int[] nums) {
        int n = nums.length;
        int maxOR = 0;
        for (int num : nums) {
            maxOR |= num;
        }
        return dfs(nums, 0, 0, maxOR);
    }

    private int dfs(int[] nums, int index, int currentOR, int targetOR) {
        if (index == nums.length) {
            return currentOR == targetOR ? 1 : 0;
        }

        int take = dfs(nums, index + 1, currentOR | nums[index], targetOR);

        int skip = dfs(nums, index + 1, currentOR, targetOR);
        return take + skip;
    }

    public ListNode mergeNodes(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        ListNode temp = head.next;
        int sum = 0;


        while (temp != null) {
            if (temp.val == 0) {
                cur.next = new ListNode(sum);
                cur = cur.next;
                sum = 0;
            } else {
                sum += temp.val;
            }
            temp = temp.next;
        }
        return dummyHead.next;
    }

    public boolean isStrictlyPalindromic(int n) {
        if (n >= 4)
            return false;
        return true;
    }

    /*
     * Каждый элемент, меньший, чем pivot, появляется перед каждым элементом, большим, чем pivot.
     * Каждый элемент, равный pivot, появляется между элементами, меньшими и большими, чем pivot.
     */
    /*
     * Input: nums = [9,12,5,10,14,3,10], pivot = 10
     * Output: [9,5,3,10,10,12,14]
     */
    public int[] pivotArray(int[] nums, int pivot) {
        int left = 0;
        int right = nums.length - 1;

        int i = 0;
        while (i <= right) {
            if (nums[i] < pivot) {
                swap(nums, i, left);
                left++;
                i++;
            } else if (nums[i] > pivot) {
                swap(nums, i, right);
                right--;
            } else {
                i++;
            }
        }


        return nums;
    }

    public int[] pivotArray2(int[] nums, int pivot) {
        int[] res = new int[nums.length];
        int left = 0;
        int right = nums.length - 1;

        for (int i = 0, j = nums.length - 1; i < nums.length; i++, j--) {
            if (nums[i] < pivot) {
                res[left] = nums[i];
                left++;
            }
            if (nums[j] > pivot) {
                res[right] = nums[j];
                right--;
            }
        }

        while (left <= right) {
            res[left] = pivot;
            left++;
        }
        return res;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public int[] minOperations(String boxes) {
        int n = boxes.length();
        int[] answer = new int[n];
        int count = 0;
        int ops = 0;

        for (int i = 0; i < n; i++) {
            answer[i] += ops;
            if (boxes.charAt(i) == '1') {
                count++;
            }
            ops += count;
        }

        count = 0;
        ops = 0;

        for (int i = n - 1; i >= 0; i--) {
            answer[i] += ops;
            if (boxes.charAt(i) == '1') {
                count++;
            }
            ops += count;
        }

        return answer;
    }

    public static boolean isValid(int i, int j, int n, int m) {
        return i >= 0 && i < n && j >= 0 && j < m;
    }

    private static final int[][] STEPS = {
            {1, 2}, {1, -2}, {-1, 2}, {-1, -2},
            {2, 1}, {2, -1}, {-2, 1}, {-2, -1}
    };

    public static List<Integer> minimumOnTheSegment(int[] arr, int k) {
        List<Integer> result = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < arr.length; i++) {
            while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }

            while (!deque.isEmpty() && arr[deque.peekLast()] >= arr[i]) {
                deque.pollLast();
            }

            deque.offerLast(i);

            if (i >= k - 1) {
                result.add(arr[deque.peekFirst()]);
            }
        }

        return result;

    }

    public static int[] largestProductOfTwoNumbers(List<Integer> numbers) {

        int n = numbers.size();
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int current = numbers.get(i);

            if (current > max1) {
                max2 = max1;
                max1 = current;
            } else if (current > max2) {
                max2 = current;
            }

            if (current < min1) {
                min2 = min1;
                min1 = current;
            } else if (current < min2) {
                min2 = current;
            }
        }

        long composition = (long) max1 * max2;
        long composition2 = (long) min1 * min2;

        if (composition >= composition2) {
            return new int[]{max2, max1};
        } else {
            return new int[]{min1, min2};
        }
    }


    public static String mostFrequentWord(String[] words) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        int max = 0;
        String mostFrequentWord = "";

        for (String word : map.keySet()) {
            if (map.get(word) > max || (map.get(word) == max && word.compareTo(mostFrequentWord) < 0)) {
                max = map.get(word);
                mostFrequentWord = word;
            }
        }
        return mostFrequentWord;
    }


    public static long findPair(int n, int m, int start, int end, List<Pair> fleas) {
        int[][] dists = new int[n][m];
        int INF = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            Arrays.fill(dists[i], INF);
        }

        Queue<Pair> q = new LinkedList<>();
        dists[start][end] = 0;
        q.offer(new Pair(start, end));

        while (!q.isEmpty()) {
            Pair current = q.poll();
            int currentDist = dists[current.first][current.second];
            for (int[] step : STEPS) {
                int ni = current.first + step[0];
                int nj = current.second + step[1];
                if (isValid(ni, nj, n, m) && dists[ni][nj] == INF) {
                    dists[ni][nj] = currentDist + 1;
                    q.offer(new Pair(ni, nj));
                }
            }
        }

        long sum = 0;
        for (Pair flea : fleas) {
            if (dists[flea.first][flea.second] == INF) {
                return -1;
            }
            sum += dists[flea.first][flea.second];
        }
        return sum;
    }
}
