package com.example;

import java.util.*;

public class App {


    public static class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }



    public static int isPalindrome(int A) {
        if (A < 0) return 0;
        int a = Math.abs(A);
        int i = 0;
        while (a > 0) {
            i = i * 10 + a % 10;
            System.out.println(i);
            a /= 10;
        }
        return (A == i) ? 1 : 0;
    }

    public static void main(String[] args) {
        int res = wordBreak("a",new String[]{"aaa"});
        System.out.println(res);
    }

    public static int[] squareArrAndSortUnDesc(int[] array){
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i] * array[i];
        }
        Arrays.sort(array);
        return array;
    }

    public static int wordBreak(String A, String[] B) {
        int n = B.length;
        int[] dp = new int[n + 1];

        dp[0] = 1;

        Set<String> dict = new HashSet<>(Arrays.asList(B));

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                String substring = A.substring(j, i);
                if (dict.contains(substring) && dp[j] == 1) {
                    dp[i] = 1;
                    break;
                }
            }
        }
        return dp[n];
    }

    public ListNode sortList(ListNode A) {
        if(A == null || A.next == null) return A;

        ListNode mid = getMid(A);
        ListNode left = sortList(A);
        ListNode right = sortList(mid);

        return merge(left, right);

        /*
        *  PriorityQueue<Integer> p = new PriorityQueue<>();
        ListNode current=A;
        while(current!=null){
            p.add(current.val);
            current=current.next;
        }
        ListNode temp= new ListNode(p.poll());
        A=temp;
        while(p.isEmpty()==false){
            ListNode pre = new ListNode(p.poll());
            temp.next=pre;
            temp=temp.next;
        }
        return A;
        * */
    }

    private ListNode getMid(ListNode l1){
        ListNode prev = null;
        ListNode slow = l1;
        ListNode fast = l1;

        while(fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        if(prev != null) {
            prev.next = null;
        }

        return slow;
    }

    private ListNode merge(ListNode A, ListNode B) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (A != null && B != null) {
            if (A.val < B.val) {
                current.next = A;
                A = A.next;
            }
            else{
                current.next = B;
                B = B.next;
            }
            current = current.next;
        }

        if(A != null) {
            current.next = A;
        }else{
            current.next = B;
        }

        return dummy.next;
    }


    public static List<String> sortByColor(List<String> noski) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String s : noski) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        List<String> nonPar = new ArrayList<>();

        for (String s : map.keySet()) {
            if (map.get(s) % 2 == 1) {
                nonPar.add(s);
            }
        }

        return nonPar;
    }

    public ListNode mergeTwoLists(ListNode A, ListNode B) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (A != null && B != null) {
            if (A.val <= B.val) {
                current.next = A;
                A = A.next;
            } else {
                current.next = B;
                B = B.next;
            }
            current = current.next;
        }

        current.next = (A != null) ? A : B;

        return dummy.next;
    }

    public int evalRPN(String[] A) {
        Stack<Integer> stack = new Stack<>();
        for (String s : A) {
            if (s.equals("+")) {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a + b);
            } else if (s.equals("-")) {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a - b);
            } else if (s.equals("*")) {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a * b);
            } else if (s.equals("/")) {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a / b);
            } else {
                stack.push(Integer.parseInt(s));
            }
        }
        return stack.pop();
    }

    public int maximalRectangle(int[][] A) {
        if (A == null || A.length == 0 || A[0].length == 0) {
            return 0;
        }

        int rows = A.length;
        int cols = A[0].length;
        int[] heights = new int[cols + 1];
        int maxArea = 0;

        for (int[] row : A) {
            for (int i = 0; i < cols; i++) {
                heights[i] = row[i] == 1 ? heights[i] + 1 : 0;
            }

            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < heights.length; i++) {
                while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                    int h = heights[stack.pop()];
                    int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                    maxArea = Math.max(maxArea, h * w);
                }
                stack.push(i);
            }
        }

        return maxArea;
    }

    public ListNode removeNthFromEnd(ListNode A, int B) {
        ListNode dummy = new ListNode(0);
        dummy.next = A;
        ListNode fast = dummy;
        ListNode slow = dummy;

        for (int i = 0; i <= B; i++) {
            if (fast == null) break;
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return dummy.next;
    }

    public ListNode reverseList(ListNode A) {
        ListNode current = A;
        ListNode prev = null;

        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    public int solve(int[][] A) {
        int n = A.length;

        for (int i = n - 2; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                A[i][j] += Math.max(A[i + 1][j], A[i + 1][j + 1]);
            }
        }
        return A[0][0];
    }

    public int solve(String A, String B) {
        int m = A.length();
        int n = B.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }

    public int mice(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);

        int timer = 0;

        for (int i = 0; i < A.length; i++) {
            int currentTime = Math.abs(A[i] - B[i]);
            timer = Math.max(timer, currentTime);
        }
        return timer;
    }

    public int maxProfit(final int[] A) {
        int minDay = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < A.length; i++) {
            minDay = Math.min(minDay, A[i]);
            maxProfit = Math.max(maxProfit, A[i] - minDay);
        }
        return maxProfit;
    }

    public ArrayList<Integer> plusOne(ArrayList<Integer> A) {
        int n = A.size();

        while (n > 1 && A.get(0) == 0) {
            A.remove(0);
            n--;
        }

        int carry = 1;

        for (int i = n - 1; i >= 0; i--) {
            int sum = A.get(i) + carry;
            A.set(i, sum % 10);
            carry = sum / 10;
        }

        if (carry > 0) {
            A.add(0, carry);
        }

        return A;
    }

    public int repeatedNumber(final int[] A) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < A.length; i++) {
            if (!set.add(A[i])) {
                return A[i];
            }
        }
        return -1;
    }

    public int majorityElement(final List<Integer> A) {
        int count = 0;
        int ans = -1;
        for (Integer i : A) {
            if (count == 0)
                ans = i;
            count += (ans == i) ? 1 : -1;
        }
        return ans;
    }

    public static int minDistance(String A, String B) {
        int m = A.length();
        int n = B.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }

        return dp[m][n];
    }


    private static Character findFirstUniqueLetter(String str) {
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        for (char c : str.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (char c : map.keySet()) {
            if (map.get(c) == 1) {
                return c;
            }
        }
        return null;
    }

    public static ListNode deleteDuplicates(ListNode A) {
        if (A == null || A.next == null) {
            return A;
        }

        Set<Integer> seen = new HashSet<>();
        ListNode current = A;
        ListNode prev = null;

        while (current != null) {
            if (seen.contains(current.val)) {
                prev.next = current.next;
            } else {
                seen.add(current.val);
                prev = current;
            }
            current = current.next;
        }

        return A;

    }

    private void task1() {
        Scanner scan = new Scanner(System.in);

        int a = scan.nextInt();
        int b = scan.nextInt();
        int c = scan.nextInt();
        int d = scan.nextInt();

        int result = a;
        int dopSum = 0;

        if (d > b) {
            dopSum = (d - b) * c;
        }
        result += dopSum;
        System.out.println(result);
        scan.close();
    }

    //task2
    public static int minCuts(int n) {
        int cuts = 0;

        while (n > 0) {
            cuts += n & 1;
            n >>= 1;
        }

        return cuts;
    }

    private static void task3() {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        int t = scan.nextInt();

        List<Integer> floors = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            floors.add(scan.nextInt());
        }
        System.out.println(floors);

        int employee = scan.nextInt();

        int neibor = Math.min((floors.get(employee - 1) - floors.get(0)), (floors.get(n - 1) - floors.get(employee - 1)));

        int res = 0;
        if (employee == 1 || employee == n || neibor <= t) {
            res = (floors.get(n - 1) - floors.get(0));
        } else {
            res = Math.min(floors.get(employee - 1) - floors.get(0), floors.get(n - 1) - floors.get(employee - 1) + floors.get(n - 1) - floors.get(0));
        }
        System.out.println(res);
    }


}
