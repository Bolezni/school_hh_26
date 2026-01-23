package com.example.yandexContest.practice;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

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

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x, ListNode head) {
            val = x;
            next = null;
        }
    }

    public static void main(String[] args) throws IOException {
        int res = minMovesToSeat(new int[]{3, 1, 5}, new int[]{2, 7, 4});
        System.out.println(res);
    }


    private int resMaxPathSum = 0;

    public int maxPathSum(TreeNode A) {
        if(A == null) return 0;
        resMaxPathSum = Integer.MIN_VALUE;
        findOnlyPlusNumbers(A);
        return resMaxPathSum;
    }

    private int findOnlyPlusNumbers(TreeNode A) {
        if(A == null)
            return 0;

        int left = Math.max(0, findOnlyPlusNumbers(A.left));
        int right = Math.max(0, findOnlyPlusNumbers(A.right));

        int currentMax = A.val + left + right;
        resMaxPathSum = Math.max(resMaxPathSum, currentMax);

        return A.val + Math.max(left, right);
    }


    //todo: посмотреть полное объяснение
 // 1, 2, 3, 4,5
    // 1,2,3,5
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy;
        if(head == null) return null;

       for(int i = 0; i < n; i++) {
           head = head.next;
       }

        while(head != null){
            head = head.next;
            cur = cur.next;
        }
        cur.next = cur.next.next;
        return dummy.next;
    }

    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
       for(int i = 0; i < nums.length - 1; i++) {
           int com = target - nums[i];
           if(map.containsKey(com)) {
               return new int[]{map.get(com), i};
           }
           map.put(nums[i], i);
       }
       return new int[]{};
    }

    public int countTriplets(int[] arr) {
        int n = arr.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int a = 0;
                for (int k = i; k < j; k++) {
                    a ^= arr[k];
                }

                int b = 0;
                for (int k = j; k < n; k++) {
                    b ^= arr[k];
                    if (a == b) count++;

                }
            }
        }
        return count;
    }

    private int sum = 0;
    private int maxLevel = 0;

    public int deepestLeavesSum(TreeNode root) {
        if (root == null) return 0;
        maxSum(root, 0);
        return sum;
    }

    private void maxSum(TreeNode root, int level) {
        if (root == null) return;

        if (level > maxLevel) {
            sum = 0;
            maxLevel = level;
        }
        if (level == maxLevel) {
            sum += root.val;
        }
        maxSum(root.left, level + 1);
        maxSum(root.right, level + 1);
    }


    public TreeNode reverseOddLevels(TreeNode root) {
        traverse(root.left, root.right, true);
        return root;
    }

    private void traverse(TreeNode node1, TreeNode node2, boolean lvl) {
        if (node1 == null && node2 == null) {
            return;
        }

        if (lvl) {
            int temp = node1.val;
            node1.val = node2.val;
            node2.val = temp;
        }

        traverse(node1.left, node2.right, !lvl);
        traverse(node1.right, node2.left, !lvl);
    }

    public static int minMovesToSeat(int[] seats, int[] students) {
        int[] pos = new int[101];
        int n = seats.length;
        for (int i = 0; i < n; i++) {
            pos[seats[i]]++;
            pos[students[i]]--;
        }
        int res = 0;
        int current = 0;
        for (int i : pos) {
            res += Math.abs(current);
            current += i;
        }
        return res;
    }

    public int maxDistinct(String s) {
        Set<Character> sets = new HashSet<>();
        for (char c : s.toCharArray()) {
            sets.add(c);
        }
        return sets.size();
    }

    public String reversePrefix(String s, int k) {
        char[] temp = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = k - 1; i >= 0; i--) {
            sb.append(temp[i]);
        }
        for (int i = k; i < temp.length; i++) {
            sb.append(temp[i]);
        }
        return sb.toString();

    }

    public int[] processQueries(int[] queries, int m) {
        int n = queries.length;
        int[] P = new int[m];
        for (int i = 0, j = 1; i < m; i++, j++) {
            P[i] = j;
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int index = -1;
            for (int j = 0; j < m; j++) {
                if (queries[i] == P[j]) {
                    index = j;
                    break;
                }
            }
            res[i] = index;
            int temp = P[index];
            for (int k = index; k > 0; k--) {
                P[k] = P[k - 1];
            }
            P[0] = temp;
        }
        return res;
    }


    List<TreeNode> sortedArr = new ArrayList<>();

    public TreeNode balanceBST(TreeNode root) {
        inorderTraverse(root);
        return sortedArrayToBST(0, sortedArr.size() - 1);
    }

    private void inorderTraverse(TreeNode root) {
        if (root == null) return;
        inorderTraverse(root.left);
        sortedArr.add(root);
        inorderTraverse(root.right);
    }

    private TreeNode sortedArrayToBST(int start, int end) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        TreeNode root = sortedArr.get(mid);
        root.left = sortedArrayToBST(start, mid - 1);
        root.right = sortedArrayToBST(mid + 1, end);
        return root;
    }

    public static int reverse(int x) {
        boolean negative = x < 0;
        if (negative) {
            x = -x;
        }
        int res = 0;
        while (x > 0) {
            int temp = x % 10;
            x /= 10;
            if (res > (Integer.MAX_VALUE - temp) / 10) {
                return 0;
            }
            res = (res * 10) + temp;
        }
        return negative ? -res : res;
    }

    public int[] twoSum(int[] nums, int target) {
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < nums.length - i; j++) {
                if ((nums[j] + nums[j + i]) == target) {
                    return new int[]{j, j + i};
                }
            }
        }

        return null;
    }

    public int maxDepth(TreeNode root) {
        int max = 0;
        if (root == null) {
            return max;
        }
        max = Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
        return max;
    }

    public int balancedStringSplit(String s) {
        int count = 0;
        int res = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'R') {
                count++;
            } else {
                count--;
            }

            if (count == 0) {
                res++;
            }
        }
        return res;
    }


    public int countConsistentStrings(String allowed, String[] words) {
        Set<Character> allowedSet = new HashSet<>();
        for (char c : allowed.toCharArray()) {
            allowedSet.add(c);
        }

        int count = 0;
        for (String word : words) {
            boolean consistent = true;
            for (char c : word.toCharArray()) {
                if (!allowedSet.contains(c)) {
                    consistent = false;
                    break;
                }
            }
            if (consistent) {
                count++;
            }
        }
        return count;
    }

    public int[] countPoints(int[][] points, int[][] queries) {
        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int cx = queries[i][0];
            int cy = queries[i][1];
            int r = queries[i][2];
            int r2 = r * r;

            int count = 0;

            for (int[] p : points) {
                int dx = p[0] - cx;
                int dy = p[1] - cy;

                if (dx * dx + dy * dy <= r2) {
                    count++;
                }
            }

            ans[i] = count;
        }

        return ans;
    }

    public int countPairs(List<Integer> nums, int target) {
        int n = nums.size();
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums.get(i) + nums.get(j) < target) {
                    count++;
                }
            }
        }
        return count;
    }

    public int findPermutationDifference(String s, String t) {
        int n = s.length();

        int diff = 0;

        for (int i = 0; i < n; i++) {
            char curr = s.charAt(i);

            for (int j = 0; j < n; j++) {
                if (curr == t.charAt(j)) {
                    diff += Math.abs(i - j);
                }
            }
        }
        return diff;
    }

    public static char strangeEcho(String s, int k) {
        String[] split = s.split(" ");
        int pos = 0;

        for (String str : split) {
            for (int i = 0; i < str.length(); i++) {
                int repeat = i + 1;

                if (pos + repeat > k) {
                    return str.charAt(i);
                }
                pos += repeat;
            }
            if (pos <= k) {
                if (pos == k)
                    return ' ';
                pos++;
            }
        }

        throw new IndexOutOfBoundsException();
    }


    public int[][] largestLocal(int[][] grid) {
        int n = grid.length;
        int[][] matrix = new int[n - 2][n - 2];
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                int temp = 0;

                for (int k = i - 1; k <= i + 1; k++) {
                    for (int l = j - 1; l <= j + 1; l++) {
                        temp = Math.max(temp, grid[k][l]);
                    }
                }

                matrix[i - 1][j - 1] = temp;
            }
        }
        return matrix;
    }

    public int maxFreqSum(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int maxVowel = 0;
        int maxConsonant = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {

            if ("aeiou".indexOf(entry.getValue()) >= 0) {
                maxVowel = Math.max(maxVowel, entry.getValue());
            } else {
                maxConsonant = Math.max(maxConsonant, entry.getValue());
            }

        }
        return maxVowel + maxConsonant;
    }

    public int maximumWealth(int[][] accounts) {
        int maxWealth = 0;
        for (int[] customer : accounts) {
            int currentWealth = 0;
            for (int balance : customer) {
                currentWealth += balance;
            }
            maxWealth = Math.max(maxWealth, currentWealth);
        }
        return maxWealth;
    }

    public int numJewelsInStones(String jewels, String stones) {
        int count = 0;
        for (char ch : stones.toCharArray()) {
            for (char jewel : jewels.toCharArray()) {
                if (jewel == ch) {
                    count++;
                }
            }

        }
        return count;
    }

    public int[] getSneakyNumbers(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int[] res = new int[2];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                res[i] = entry.getKey();
                i++;
            }
        }
        return res;
    }

    // n = 3;

    public static int climbStairs(int n) {
        int steps = 0;
        int temp = n;
        while (temp > 0) {
            temp = n / 2;
            steps = steps + temp;
            n -= temp;
        }
        return steps;
    }

    public static List<List<Integer>> findMatrix(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int maxFreq = 0;
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
            maxFreq = Math.max(maxFreq, map.get(i));
        }

        int n = nums.length;

        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < maxFreq; i++) {
            res.add(new ArrayList<>());
        }

        for (List<Integer> re : res) {
            for (int j = 0; j < n; j++) {
                if (!re.contains(nums[j]) && nums[j] != 0) {
                    re.add(nums[j]);
                    nums[j] = 0;
                }
            }
        }
        return res;
    }

    // m - metal
    // p - paper
    // g - steklo

    public int garbageCollection(String[] garbage, int[] travel) {
        int[] last = new int[128];
        int res = 0;

        for (int i = 0; i < garbage.length; i++) {
            res += garbage[i].length();
            for (int j = 0; j < garbage[i].length(); j++) {
                last[garbage[i].charAt(j)] = i;
            }
        }

        for (int j = 1; j < travel.length; ++j) {
            travel[j] += travel[j - 1];
        }
        for (int c : "PMG".toCharArray()) {
            if (last[c] > 0) {
                res += travel[last[c] - 1];
            }
        }
        return res;
    }

    public boolean hasCycle(ListNode head) {
        if (head == null) return false;

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                return true;
        }
        return false;
    }

    public static int climbStairs2(int n) {
        if (n <= 1)
            return 1;
        int prev1 = 1;
        int prev2 = 1;
        int current = 0;

        for (int i = 2; i <= n; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }

        return current;
    }

    public static Map<Long, Integer> multiMap(Map<Integer, List<Long>> map) {
        return map.entrySet().stream()
                .flatMap(entry -> {
                    int key = entry.getKey();
                    List<Long> values = entry.getValue();
                    return values.stream().map(value -> Map.entry(value, key));
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public int calculate(String expression) {
        int sum = 0;
        int currentVal = 0;
        int currentBlockVal = 1;

        for (char c : expression.toCharArray()) {
            if (c == '+' || c == '*') {
                if (c == '+') {
                    currentBlockVal *= currentVal;
                    sum += currentBlockVal;
                    currentBlockVal = 1;
                } else {
                    currentBlockVal *= currentVal;
                }
                currentVal = 0;
            } else {
                int digit = c - '0';
                currentVal = currentVal * 10 + digit;
            }
        }

        currentBlockVal *= currentVal;
        sum += currentBlockVal;

        return sum;
    }

    public static boolean binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (arr[mid] == target) {
                return true;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else if (arr[mid] < target) {
                left = mid + 1;
            }
        }
        return false;
    }

    public int getMaxThreePath(TreeNode root) {
        getMaxThreePathRecursive(root);
        return res;
    }

    public int res = Integer.MIN_VALUE;

    private int getMaxThreePathRecursive(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int maxLeftPath = getMaxThreePathRecursive(root.left);

        int maxRightPath = getMaxThreePathRecursive(root.right);

        res = Math.max(res, maxLeftPath + root.val + maxRightPath);

        return Math.max(Math.max(maxLeftPath, maxRightPath) + root.val, 0);
    }

    public String defangIPaddr(String address) {
        return address.replace(".", "[.]");
    }

    public static int minimumOperations(int[] nums) {
        int countOperation = 0;

        for (int num : nums) {
            countOperation += Math.min(num % 3, 3 - (num % 3));
        }
        return countOperation;
    }

    public int smallestEvenMultiple(int n) {
        if (n % 2 == 0) {
            return n;
        }
        return smallestEvenMultiple(n * 2);
    }

    public int[][] sortTheStudents(int[][] score, int k) {
        Arrays.sort(score, (a, b) -> b[k] - a[k]);
        return score;
    }

    public static List<Integer> firstVertexAccessibleFrom(List<List<Integer>> orientGraph) {
        int n = orientGraph.size();
        boolean[] visited = new boolean[n + 1];

        List<Integer> res = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(1);
        visited[1] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int neighbor : orientGraph.get(current)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (visited[i])
                res.add(i);
        }

        return res;
    }

    public static List<List<Integer>> connectivityComponents(List<List<Integer>> grid, int n) {
        boolean[] visited = new boolean[n + 1];
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                List<Integer> comp = new ArrayList<>();
                Stack<Integer> stack = new Stack<>();
                stack.push(i);

                while (!stack.isEmpty()) {
                    int current = stack.pop();
                    if (visited[current]) continue;

                    visited[current] = true;
                    comp.add(current);

                    for (int neighbor : grid.get(current)) {
                        if (!visited[neighbor]) {
                            stack.push(neighbor);
                        }
                    }
                }
                res.add(comp);
            }
        }
        return res;
    }

    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) return 0;

        int value = (root.val >= low && root.val <= high) ? root.val : 0;

        int left = rangeSumBST(root.left, low, high);
        int right = rangeSumBST(root.right, low, high);

        return value + left + right;
    }

    public static int numberOfEmployeesWhoMetTarget(int[] hours, int target) {
        int count = 0;
        for (int hour : hours) {
            if (hour >= target) {
                count++;
            }
        }
        return count;
    }

    public int numIdenticalPairs(int[] nums) {
        int n = nums.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] == nums[j]) {
                    count++;
                }
            }
        }
        return count;

    }

    public int[] transformArray(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];

        for (int i = 0; i < n; i++) {
            if (nums[i] % 2 == 0) {
                res[i] = 0;
            } else {
                res[i] = 1;
            }
        }
        Arrays.sort(res);
        return res;
    }

    public static int findClosest(int x, int y, int z) {
        int first = Math.abs(z - x);
        int second = Math.abs(z - y);

        if (first < second)
            return 1;
        else if (second < first)
            return 2;

        return 0;
    }

    public static int[] shuffle(int[] nums, int n) {
        int[] res = new int[n * 2];

        int pointer = 0;
        for (int i = 0; i < n; ) {
            res[i++] = nums[pointer++];
            res[i++] = nums[n++];
        }
        return res;

    }

    public double[] convertTemperature(double celsius) {
        double[] ans = new double[2];
        ans[0] = celsius + 273.15;
        ans[1] = (celsius * 1.8) + 32;
        return ans;
    }


    static int countSubtree = 0;

    public static int averageOfSubtree(TreeNode root) {
        countSubtree = 0;
        move(root);
        return countSubtree;
    }

    private static int[] move(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        int[] left = move(root.left);
        int[] right = move(root.right);

        int sum = root.val + left[0] + right[0];
        int numberOfNodes = left[1] + right[1] + 1;
        if (sum / numberOfNodes == root.val) {
            countSubtree++;
        }

        return new int[]{sum, numberOfNodes};
    }


    static String expr;
    static int pos;

    public static int parseXOR() {
        int left = parseAND();

        while (pos < expr.length()) {
            char ch = expr.charAt(pos);
            if (ch == '|') {
                pos++;
                int right = parseAND();
                left = left | right;
            } else if (ch == '^') {
                pos++;
                int right = parseAND();
                left = left ^ right;
            } else {
                break;
            }
        }
        return left;
    }

    public static int parseAND() {
        int left = parseNot();

        while (pos < expr.length() && expr.charAt(pos) == '&') {
            pos++;
            int right = parseNot();
            left = left & right;
        }
        return left;
    }

    public static int parseNot() {
        if (pos < expr.length() && expr.charAt(pos) == '!') {
            pos++;
            int value = parseNot();
            return value == 0 ? 1 : 0;
        }
        return parsePrimary();
    }

    public static int parsePrimary() {
        if (expr.charAt(pos) == '(') {
            pos++;
            int res = parseXOR();
            pos++;
            return res;
        }

        int value = expr.charAt(pos) - '0';
        pos++;
        return value;
    }


    private static boolean checkers(int n, int m, int w, boolean[][] white,
                                    int b, boolean[][] black, String turn) {

        if ("white".equals(turn)) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    if (white[i][j]) {
                        // Вниз-влево
                        if (i + 2 <= n && j - 2 >= 1) {
                            if (black[i + 1][j - 1] &&
                                    !white[i + 2][j - 2] &&
                                    !black[i + 2][j - 2]) {
                                return true;
                            }
                        }
                        // Вниз-вправо
                        if (i + 2 <= n && j + 2 <= m) {
                            if (black[i + 1][j + 1] &&
                                    !white[i + 2][j + 2] &&
                                    !black[i + 2][j + 2]) {
                                return true;
                            }
                        }
                    }
                }
            }
        } else {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    if (black[i][j]) {
                        // Вверх-влево
                        if (i - 2 >= 1 && j - 2 >= 1) {
                            if (white[i - 1][j - 1] &&
                                    !black[i - 2][j - 2] &&
                                    !white[i - 2][j - 2]) {
                                return true;
                            }
                        }
                        // Вверх-вправо
                        if (i - 2 >= 1 && j + 2 <= m) {
                            if (white[i - 1][j + 1] &&
                                    !black[i - 2][j + 2] &&
                                    !white[i - 2][j + 2]) {
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    private static int getMinKeystrokes(String word, Set<String> dictionary) {
        for (int prefixLen = 1; prefixLen <= word.length(); prefixLen++) {
            String prefix = word.substring(0, prefixLen);

            List<String> matches = new ArrayList<>();
            for (String dictWord : dictionary) {
                if (dictWord.startsWith(prefix)) {
                    matches.add(dictWord);
                }
            }

            if (matches.size() == 1 && matches.get(0).equals(word)) {
                return prefixLen;
            }
        }

        return word.length();
    }


    //      j→  0  1  2  3  4  5  6  7  8  9
//   i↓   ┌──────────────────────────────┐
//   0    │ 0  1  0  0  0  0  0  0  0  0 │
//   1    │ 1  0  0  1  1  0  1  0  0  0 │
//   2    │ 0  0  0  0  1  0  0  0  1  0 │
//   3    │ 0  1  0  0  0  0  1  0  0  0 │
//   4    │ 0  1  1  0  0  0  0  0  0  1 │
//   5    │ 0  0  0  0  0  0  1  0  0  1 │
//   6    │ 0  1  0  1  0  1  0  0  0  0 │
//   7    │ 0  0  0  0  0  0  0  0  1  0 │
//   8    │ 0  0  1  0  0  0  0  1  0  0 │
//   9    │ 0  0  0  0  1  1  0  0  0  0 │
//        └──────────────────────────────┘
    // start --> 5
    //finish --> 4
    private static List<Integer> pathInGraph(int[][] graphs, int start, int end) {
        int n = graphs.length;
        boolean[] visited = new boolean[n];
        int[] parents = new int[n];
        Arrays.fill(parents, -1);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (cur == end) {
                List<Integer> path = new ArrayList<>();
                for (int i = end; i != -1; i = parents[i]) {
                    path.add(i);
                }
                Collections.reverse(path);
                return path;
            }

            for (int neighbor = 0; neighbor < n; neighbor++) {
                if (graphs[cur][neighbor] == 1 && !visited[neighbor]) {
                    visited[neighbor] = true;
                    parents[neighbor] = cur;
                    queue.offer(neighbor);
                }
            }
        }

        return null;
    }

    //n - количество тестов
    //n - строк
    /*Ввод
        2
        2 2.9 2.1
        3 5.6 9.0 2.0
    */

    private static int[] convertWithHashMap(double[] values) {
        int n = values.length;

        Double[][] pairs = new Double[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i][0] = values[i];
            pairs[i][1] = (double) i;
        }

        Arrays.sort(pairs, Comparator.comparingDouble(a -> a[0]));

        int[] ranks = new int[n];
        for (int i = 0; i < n; i++) {
            int originalIndex = pairs[i][1].intValue();
            ranks[originalIndex] = i + 1;
        }

        return ranks;
    }


    private static boolean conveyor(int[] A) {
        Stack<Integer> stack = new Stack<>();
        int n = A.length;

        int[] sorted = A.clone();
        Arrays.sort(sorted);

        int beltIndex = 0;
        int expectedIndex = 0;

        while (expectedIndex < n) {
            int expected = sorted[expectedIndex];

            if (!stack.isEmpty() && stack.peek() == expected) {
                stack.pop();
                expectedIndex++;
            } else if (beltIndex < n && A[beltIndex] == expected) {
                beltIndex++;
                expectedIndex++;
            } else if (beltIndex < n) {
                int current = A[beltIndex];

                if (!stack.isEmpty() && current < stack.peek()) {
                    return false;
                }
                stack.push(current);
                beltIndex++;
            } else {
                return false;
            }
        }

        return true;
    }

    private static int programmerOnTheBeach(int[] arr) {
        int n = arr.length;
        int min = Integer.MAX_VALUE;

        Arrays.sort(arr);

        for (int i = 0; i < n - 1; i++) {
            int minXor = arr[i] ^ arr[i + 1];
            min = Math.min(min, minXor);

        }
        return min;
    }


    //найти кратчайший путь в метро
    // n- количество станций
    // m - количество линий метро
    // int[n][m] graph
    // a - номер начальной станции
    // b- номер конечной станции

    private static int findMinTransfers(int[][] underground, int a, int b) {
        int n = underground.length;

        Map<Integer, List<Integer>> stations = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int station : underground[i]) {
                stations.computeIfAbsent(station, k -> new ArrayList<>()).add(i);
            }
        }

        if (!stations.containsKey(b) || !stations.containsKey(a)) {
            return -1;
        }

        Set<Integer> startLines = new HashSet<>(stations.get(a));
        Set<Integer> targetLines = new HashSet<>(stations.get(b));

        for (int line : startLines) {
            if (targetLines.contains(line)) {
                return 0;
            }
        }

        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (List<Integer> lineList : stations.values()) {
            for (int i = 0; i < lineList.size(); i++) {
                for (int j = i + 1; j < lineList.size(); j++) {
                    int line1 = lineList.get(i);
                    int line2 = lineList.get(j);

                    graph.computeIfAbsent(line1, k -> new ArrayList<>()).add(line2);
                    graph.computeIfAbsent(line2, k -> new ArrayList<>()).add(line1);
                }
            }
        }

        Map<Integer, Integer> dist = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();

        for (int line : startLines) {
            dist.put(line, 0);
            queue.offer(line);
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();
            int currentDist = dist.get(current);

            if (!graph.containsKey(current)) {
                continue;
            }

            for (int neighbor : graph.get(current)) {
                if (!dist.containsKey(neighbor)) {
                    dist.put(neighbor, currentDist + 1);

                    if (targetLines.contains(neighbor)) {
                        return currentDist + 1;
                    }

                    queue.offer(neighbor);
                }
            }
        }
        return -1;
    }

    private static double favouriteNumbers(String n, int k) {
        int len = n.length();

        if (len == 1) {
            if (isDivisible(n)) {
                System.out.println("1.000000000000000");
            } else {
                System.out.println("0.000000000000000");
            }
            return 0.0;
        }

        Map<String, Double> map = new HashMap<>();
        map.put(n, 1.0);

        for (int op = 0; op < k; op++) {
            Map<String, Double> newDp = new HashMap<>();

            for (Map.Entry<String, Double> entry : map.entrySet()) {
                String current = entry.getKey();
                double prob = entry.getValue();

                int totalSwap = len * (len - 1) / 2;
                double swapProb = prob / totalSwap;

                char[] arr = current.toCharArray();
                for (int i = 0; i < len; i++) {
                    for (int j = i + 1; j < len; j++) {
                        char temp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = temp;

                        String newNum = new String(arr);
                        newDp.put(newNum, newDp.getOrDefault(newNum, 0.0) + swapProb);

                        arr[j] = arr[i];
                        arr[i] = temp;
                    }
                }
            }
            map = newDp;
        }
        double result = 0.0;
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            if (isDivisible(entry.getKey())) {
                result += entry.getValue();
            }
        }
        return result;

    }

    private static boolean isDivisible(String num) {
        int lastDigit = num.charAt(num.length() - 1) - '0';

        if (lastDigit == 5) return true;

        if (lastDigit == 10) return true;

        boolean divisibleBy2 = (lastDigit % 2) == 0;

        int sum = 0;
        for (char c : num.toCharArray()) {
            sum += c - '0';
        }
        boolean divisibleBy3 = (sum % 3) == 0;

        if (divisibleBy2 && divisibleBy3) return true;

        return false;
    }

    private static int countUniqueElements(List<Integer> a) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (Integer i : a) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        int count = 0;
        for (Integer i : map.keySet()) {
            if (map.get(i) == 1) {
                count++;
            }
        }
        return count;
    }

    private static List<Integer> findSequence(int[] arr1, int[] arr2) {
        int n = arr1.length;
        int m = arr2.length;
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (arr1[i - 1] == arr2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        List<Integer> res = new ArrayList<>();
        int i = n, j = m;

        while (i > 0 && j > 0) {
            if (arr1[i - 1] == arr2[j - 1]) {
                res.add(arr1[i - 1]);
                i--;
                j--;
            } else if (dp[i - 1][j] >= dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        List<Integer> reversed = new ArrayList<>();
        for (int k = res.size() - 1; k >= 0; k--) {
            reversed.add(res.get(k));
        }
        return reversed;

    }

    private static int knightsMove(int n, int m) {
        int[][] dp = new int[n][m];
        dp[0][0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i >= 2 && j >= 1) {
                    dp[i][j] += dp[i - 2][j - 1];
                }
                if (i >= 1 && j >= 2) {
                    dp[i][j] += dp[i - 1][j - 2];
                }
            }
        }

        return dp[n - 1][m - 1];
    }

    private static String characters(int[][] dp, int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        List<Character> path = new ArrayList<>();
        int i = n - 1, j = m - 1;

        while (i > 0 || j > 0) {
            if (i > 0 && dp[i][j] == dp[i - 1][j] + matrix[i][j]) {
                path.add('D');
                i--;
            } else if (j > 0 && dp[i][j] == dp[i][j - 1] + matrix[i][j]) {
                path.add('R');
                j--;
            }
        }

        Collections.reverse(path);
        StringBuilder result = new StringBuilder();
        for (char c : path) {
            result.append(c);
        }
        return result.toString();
    }

    //Вывести маршрут максимальной стоимости
    private static int[][] finMaxPath(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int[][] dp = new int[n][m];
        dp[0][0] = matrix[0][0];

        for (int j = 1; j < m; j++) {
            dp[0][j] = dp[0][j - 1] + matrix[0][j];
        }

        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + matrix[i][0];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + matrix[i][j];
            }
        }


        return dp;
    }

    //Самый дешевый путь
    private static int findFreePath(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[][] dp = new int[n][m];
        dp[0][0] = grid[0][0];

        for (int j = 1; j < m; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        return dp[n - 1][m - 1];
    }
}
