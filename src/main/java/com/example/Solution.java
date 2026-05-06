package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    private static final Map<String, Integer> numberWords = new HashMap<>();

    static {
        numberWords.put("zero", 0);
        numberWords.put("one", 1);
        numberWords.put("two", 2);
        numberWords.put("three", 3);
        numberWords.put("four", 4);
        numberWords.put("five", 5);
        numberWords.put("six", 6);
        numberWords.put("seven", 7);
        numberWords.put("eight", 8);
        numberWords.put("nine", 9);
        numberWords.put("ten", 10);
        numberWords.put("eleven", 11);
        numberWords.put("twelve", 12);
        numberWords.put("thirteen", 13);
        numberWords.put("fourteen", 14);
        numberWords.put("fifteen", 15);
        numberWords.put("sixteen", 16);
        numberWords.put("seventeen", 17);
        numberWords.put("eighteen", 18);
        numberWords.put("nineteen", 19);
        numberWords.put("twenty", 20);
        numberWords.put("thirty", 30);
        numberWords.put("forty", 40);
        numberWords.put("fifty", 50);
        numberWords.put("sixty", 60);
        numberWords.put("seventy", 70);
        numberWords.put("eighty", 80);
        numberWords.put("ninety", 90);
        numberWords.put("hundred", 100);
        numberWords.put("thousand", 1000);
        numberWords.put("million", 1000000);
        numberWords.put("billion", 1000000000);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        int[][] matrix = new int[N][];
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        int[] data = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int start = data[0] - 1;
        int end = data[1] - 1;
        reader.close();

        int[] result = findLengthMinPath(matrix, start, N);

        if (result[end] != Integer.MAX_VALUE) {
            System.out.println(result[end]);
        } else {
            System.out.println(-1);
        }

    }


    private static int[] findLengthMinPath(int[][] grid, int start, int end){
        Queue<Integer> queue = new LinkedList<>();
        int n = grid.length;
        boolean[] visited = new boolean[n];
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        queue.offer(start);
        visited[start] = true;
        dist[start] = 0;

        while(!queue.isEmpty()){
            int v = queue.poll();

            if(v == end){
                break;
            }

            for(int i = 0; i < n; i++){
                if(grid[v][i] == 1 && !visited[i]){
                    visited[i] = true;
                    dist[i] = dist[v] + 1;
                    queue.offer(i);
                }
            }
        }

        return dist;
    }

    private static char[] characters = {'a','b','c'};

    public static String getHappyString(int n, int k) {
        List<String> res = new ArrayList<>();
        generateHappyString(n,k,res, characters, new StringBuilder());
        return res.toString();
    }

    private static void generateHappyString(int n, int k, List<String> list, char[] words, StringBuilder str) {
        if(k % words.length == 0 && str.length() == 3) {
            list.add(str.toString());
            return;
        }

        for(int i = n; i < k; i++){
            str.append(words[i]);
            generateHappyString(n + i, k, list, words, str);
            str.deleteCharAt(str.length() - 1);
        }
    }

    public ListNode rotateRight(ListNode head, int k) {
        if(head == null) return head;
        ListNode current = head;
        int len = 1;

        while(current.next != null) {
            current = current.next;
            len++;
        }

        int pos = k % len;
        if(pos == 0){
            return head;
        }

        ListNode tail = head;
        int stepsToRevert = len - pos - 1;
        for(int i = 0; i < stepsToRevert; i++){
            tail = tail.next;
        }
        ListNode newHead = tail.next;
        tail.next = null;
        current.next = head;
        return newHead;
    }

    //Дана последовательность строк strs, сгруппируйте их.анаграммыВместе. Вы можете вернуть ответ в любом порядке.
    /**
     *
     * Входные данные: strs = ["eat","tea","tan","ate","nat","bat"]
     *
     * Вывод: [["bat"],["nat","tan"],["ate","eat","tea"]]
     */
    //Можно брать по очереди слова добавлять их в список и добавлении нового проверять есть ли анаграмма уже в списке,
    // если нету добавлять как новую
    public static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();

        for(String s : strs) {
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            String key = new String(arr);
            if(!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());
    }

    private static boolean isAnagram(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        char[] c1 = s1.toLowerCase().toCharArray();
        char[] c2 = s2.toLowerCase().toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        return Arrays.equals(c1, c2);
    }

    public static int[] rearrangeArray(int[] nums) {
        int n = nums.length;

        int[] result = new int[n];

        int positiveIndex = 0;
        int negativeIndex = 0;

        //каждая пара целых чисел имеет противоположный знак
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                while (positiveIndex < n && nums[positiveIndex] < 0)
                    positiveIndex++;
                result[i] = nums[positiveIndex++];
            } else {
                while (negativeIndex < n && nums[negativeIndex] > 0)
                    negativeIndex++;
                result[i] = nums[negativeIndex++];
            }
        }

        return result;
    }


    public static String longestConsec(String[] strarr, int k) {
        int n = strarr.length;

        if (n == 0 || k > n || k <= 0) {
            return "";
        }

        String longest = "";
        int maxLength = 0;

        for (int i = 0; i <= n - k; i++) {
            StringBuilder current = new StringBuilder();
            for (int j = i; j < i + k; j++) {
                current.append(strarr[j]);
            }

            String currentString = current.toString();
            int currentLength = currentString.length();

            if (currentLength > maxLength) {
                maxLength = currentLength;
                longest = currentString;
            }
        }

        return longest;
    }

    //Костыли
    public long maxKelements2(int[] nums, int k) {
        long count = 0;

        Iterator<Integer> iterator = Arrays.stream(nums).iterator();
        List<Integer> list = new ArrayList<>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }

        list.sort(Integer::compare);
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        pq.addAll(list);

        for (int i = 0; i < k; i++) {
            int now = pq.poll();

            count += now;

            int newElement = (now + 2) / 3;

            pq.offer(newElement);
        }

        return count;
    }

    public long maxKelements(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i : nums) {
            pq.offer(i);
        }

        long count = 0L;

        for (int i = 0; i < k; i++) {
            int current = pq.poll();

            count += current;

            int newValue = (int) Math.ceil(current / 3.0);
            pq.offer(newValue);
        }
        return count;
    }


    private static final int INF = Integer.MAX_VALUE;

    public static int[] dijkstra(int[][] graph, int source) {
        int n = graph.length;
        int[] distance = new int[n];
        boolean[] visited = new boolean[n];

        Arrays.fill(distance, INF);
        distance[source] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{source, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int u = cur[1];

            if (visited[u]) continue;
            ;
            visited[u] = true;

            for (int i = 0; i < n; i++) {
                int newDistance = distance[u] + graph[u][i];
                if (newDistance < distance[i]) {
                    distance[i] = newDistance;
                    pq.offer(new int[]{u, i});
                }
            }
        }
        return distance;
    }

    public static int lcsRecursive(String x, String y, int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }

        if (x.charAt(m - 1) == y.charAt(n - 1)) {
            return 1 + lcsRecursive(x, y, m - 1, n - 1);
        } else {
            return Math.max(
                    (lcsRecursive(x, y, m, n - 1)),
                    (lcsRecursive(x, y, m - 1, n))
            );
        }
    }

    public static int fibonaci(int n) {
        if (n == 0 || n == 1)
            return n;

        return fibonaci(n - 1) + fibonaci(n - 2);
    }

    public static int factorial(int n) {
        if (n == 1)
            return 1;

        return n * factorial(n - 1);
    }

    public static int knapsack(int capacity, int[] weights, int[] values, int n) {
        if (n == 0 || capacity == 0) {
            return 0;
        }

        if (weights[n - 1] > capacity) {
            return knapsack(capacity, weights, values, n - 1);
        }

        int take = values[n - 1] + knapsack(capacity - weights[n - 1], weights, values, n - 1);
        int skip = knapsack(capacity, weights, values, n - 1);

        return Math.max(take, skip);
    }

    public static int[] findMaximumSubarray(int[] a) {
        if (a == null || a.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        return findMaximumSubarray(a, 0, a.length - 1);
    }

    private static int[] findMaximumSubarray(int[] a, int low, int high) {
        if (low == high) {
            return new int[]{low, high, a[low]};
        }

        int mid = low + (high - low) / 2;

        int[] left = findMaximumSubarray(a, low, mid);
        int[] right = findMaximumSubarray(a, mid + 1, high);

        int[] cross = findMaxCrossingArray(a, low, mid, high);
        if (left[2] >= right[2] && left[2] >= cross[2]) {
            return left;
        } else if (right[2] >= left[2] && right[2] >= cross[2]) {
            return right;
        } else {
            return cross;
        }
    }

    public static int[] findMaxCrossingArray(int[] a, int low, int mid, int high) {
        int leftSum = Integer.MIN_VALUE;
        int maxLeft = 0;
        int sum = 0;

        for (int i = mid; i >= low; i--) {
            sum += a[i];
            if (sum > leftSum) {
                leftSum = sum;
                maxLeft = i;
            }
        }

        int maxRight = mid + 1;
        int rightSum = Integer.MIN_VALUE;
        sum = 0;

        for (int i = mid + 1; i <= high; i++) {
            sum += a[i];
            if (sum > rightSum) {
                rightSum = sum;
                maxRight = i;
            }
        }
        return new int[]{maxLeft, maxRight, leftSum + rightSum};
    }

    public static ListNode rotateToRight(ListNode A, int B) {
        ListNode current = A;

        int length = 1;

        while (current.next != null) {
            length++;
            current = current.next;
        }

        B = B % length;
        if (B == 0) {
            return A;
        }

        ListNode tail = current;
        tail.next = A;

        ListNode newTail = A;
        int stepsToRevert = length - B;

        for (int i = 1; i < stepsToRevert; i++) {
            System.out.println(newTail.val);
            newTail = newTail.next;
            System.out.println(newTail.val);
        }

        ListNode newHead = newTail.next;
        newTail.next = null;
        return newHead;
    }

    public static int minimumTotal(ArrayList<ArrayList<Integer>> a) {
        int n = a.size();
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = a.get(n - 1).get(i);
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + a.get(i).get(j);
            }
        }
        return dp[0];
    }


    public ListNode swapPairs(ListNode A) {
        ListNode start = new ListNode(0);
        start.next = A;
        ListNode current = start;
        while (current.next != null && current.next.next != null) {
            current.next = swap(current.next, current.next.next);
            current = current.next.next;
        }

        return start.next;
    }

    private ListNode swap(ListNode p1, ListNode p2) {
        p1.next = p2.next;
        p2.next = p1;
        return p2;
    }

    public static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        Collections.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));
        Interval current = intervals.get(0);
        int start = current.start;
        int maxEnd = current.end;

        ArrayList<Interval> mergedIntervals = new ArrayList<>();

        for (int i = 1; i < intervals.size(); i++) {
            current = intervals.get(i);

            if (current.start <= maxEnd) {
                maxEnd = Math.max(maxEnd, current.end);
            } else {
                mergedIntervals.add(new Interval(start, maxEnd));
                maxEnd = current.end;
                start = current.start;
            }

        }
        mergedIntervals.add(new Interval(start, maxEnd));
        return mergedIntervals;
    }

    public int jump(int[] A) {
        int n = A.length;
        if (n <= 1) return 0;

        int jumps = 0;
        int currentEnd = 0;
        int farthest = 0;

        for (int i = 0; i < n - 1; i++) {
            farthest = Math.max(farthest, i + A[i]);

            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;

                if (currentEnd >= n - 1) {
                    return jumps;
                }
            }
        }

        return -1;
    }

    public static void setZeroes(ArrayList<ArrayList<Integer>> a) {
        int n = a.size();
        int m = a.get(0).size();

        HashSet<Integer> zeroRows = new HashSet<>();
        HashSet<Integer> zeroCols = new HashSet<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a.get(i).get(j) == 0) {
                    zeroRows.add(i);
                    zeroCols.add(j);
                }
            }
        }

        for (int i : zeroRows) {
            for (int j = 0; j < m; j++) {
                a.get(i).set(j, 0);
            }
        }

        for (int i : zeroCols) {
            for (int j = 0; j < n; j++) {
                a.get(j).set(i, 0);
            }
        }
    }

    public static int[] getRow(int A) {
        int[] rows = new int[A + 1];
        rows[0] = 1;
        for (int i = 1; i <= A; i++) {
            for (int j = i; j > 0; j--) {
                rows[j] = rows[j - 1] + rows[j];
            }

        }
        return rows;
    }

    public static int uniquePathsWithObstacles(int[][] A) {
        int n = A.length;
        int m = A[0].length;
        int[][] dp = new int[n][m];

        if (A[0][0] == 0) {
            dp[0][0] = 1;
        } else {
            return 0;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (A[i][j] == 0)
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[n - 1][m - 1];
    }

    public static int maxProfit(final int[] A) {
        int profit = 0;

        for (int i = 1; i < A.length; i++) {
            if (A[i] > A[i - 1])
                profit += A[i] - A[i - 1];

        }
        return profit;
    }

    public static int parseInt(String numStr) {
        String[] words = numStr.split("\\s+|-");
        int total = 0;
        int current = 0;

        for (String word : words) {
            if (word.equals("and"))
                continue;

            if (numberWords.containsKey(word)) {
                if (word.equals("hundred")) {
                    current *= numberWords.get(word);
                } else if (word.equals("thousand") || word.equals("million") || word.equals("billion")) {
                    total += current * numberWords.get(word);
                    current = 0;
                } else {
                    current += numberWords.get(word);
                }
            } else {
                throw new IllegalArgumentException("Unknown word: " + word);
            }
        }
        total += current;
        return total;
    }

    public static double solution(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length)
            return 0;
        double sum = 0;

        for (int i = 0; i < arr1.length; i++) {
            int diff = Math.abs(arr1[i] - arr2[i]);
            diff *= diff;
            sum += diff;

        }
        return sum / arr1.length;
    }

    public String convert(String s, int numRows) {
        if (numRows <= 1 || numRows >= s.length()) {
            return s;
        }
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++) {
            rows.add(new StringBuilder());
        }

        int currentRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows.get(currentRow).append(c);

            if (currentRow == 0) {
                goingDown = true;
            } else if (currentRow == numRows - 1) {
                goingDown = false;
            }

            currentRow += goingDown ? 1 : -1;
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }

        return result.toString();
    }

    public String longestPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }

        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {

            int len1 = isPalindrome(s, i, i);
            int len2 = isPalindrome(s, i, i + 1);

            int len = Math.max(len1, len2);

            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private static int isPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] sum2Array = new int[nums1.length + nums2.length];
        System.arraycopy(nums1, 0, sum2Array, 0, nums1.length);
        System.arraycopy(nums2, 0, sum2Array, nums1.length, nums2.length);
        Arrays.sort(sum2Array);

        for (int i = 0; i < sum2Array.length; i++) {
            if (sum2Array.length % 2 != 0) {
                int index = sum2Array.length / 2;
                return sum2Array[index];
            } else {
                int midIndex1 = sum2Array.length / 2 - 1;
                int midIndex2 = sum2Array.length / 2;

                return (sum2Array[midIndex1] + sum2Array[midIndex2]) / 2.0;
            }
        }
        return 0;
    }

    public static int maxCount51ms(int[] banned, int n, int maxSum) {
        Set<Integer> bannedSet = new HashSet<>();
        for (int num : banned) {
            bannedSet.add(num);
        }

        int currentSum = 0;
        int count = 0;

        for (int num = 1; num <= n; num++) {
            if (!bannedSet.contains(num) && currentSum + num <= maxSum) {
                currentSum += num;
                count++;
            }
        }

        return count;
    }


    public int maxCount4ms(int[] banned, int n, int maxSum) {
        int sum = 0;
        int result = 0;
        boolean[] isBanned = new boolean[10001];
        for (int ban : banned) {
            isBanned[ban] = true;
        }
        for (int i = 1; i <= n; i++) {
            if (sum + i > maxSum) {
                break;
            }
            if (!isBanned[i]) {
                sum += i;
                result++;
            }
        }
        return result;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        int carry = 0;

        while (l1 != null || l2 != null) {
            int sum = carry;

            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            carry = sum / 10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
        }
        if (carry > 0) {
            cur.next = new ListNode(carry);
        }

        return dummyHead.next;
    }

    public int[] twoSum(int[] nums, int target) {
        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (nums[j] + nums[j - 1] == target) {
                    return new int[]{j - 1, j};
                }
            }
        }
        return null;
    }

    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();

        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            char current = s.charAt(right);

            while (set.contains(current)) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(current);
            maxLength = Math.max(maxLength, right - left);
        }
        return maxLength;
    }

    public static class ListNode {
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
}
