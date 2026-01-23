package com.example;

import java.math.BigInteger;
import java.util.*;

public class Kata {
    private static Map<Character, Integer> map = new HashMap<>();

    private static Map<Character, Integer> alfavit = new HashMap<>();

    static {
        alfavit.put('a', 0);
        alfavit.put('b', 1);
        alfavit.put('c', 2);
        alfavit.put('d', 3);
        alfavit.put('e', 4);
        alfavit.put('f', 5);
        alfavit.put('g', 6);
        alfavit.put('h', 7);
        alfavit.put('i', 8);
        alfavit.put('j', 9);
        alfavit.put('k', 10);
        alfavit.put('l', 11);
        alfavit.put('m', 12);
        alfavit.put('n', 13);
        alfavit.put('o', 14);
        alfavit.put('p', 15);
        alfavit.put('q', 16);
        alfavit.put('r', 17);
        alfavit.put('s', 18);
        alfavit.put('t', 19);
        alfavit.put('u', 20);
        alfavit.put('v', 21);
        alfavit.put('w', 22);
        alfavit.put('x', 23);
        alfavit.put('y', 24);
        alfavit.put('z', 25);
    }


    static {
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
    }


    public static void main(String[] args) {
        int res = jumpingOnClouds(List.of(0, 1, 0, 0, 0, 1, 0));
        System.out.println(res);
    }



    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums==null || nums.length<4){
            return res;
        }

        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n-3; i++) {
            if(i > 0 &&nums[i] == nums[i-1])
                continue;
            for(int j = i+1; j < n-2; j++) {
                if(j > i+1 && nums[j] == nums[j-1])
                    continue;

                int left = j + 1;
                int right = n - 1;
                while(left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if(sum == target){
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while(left< right && nums[left] == nums[left+1]) left++;
                        while(left < right && nums[right] == nums[right-1]) right--;

                        left++;
                        right--;
                    }else if(sum < target){
                        left++;
                    }else
                        right--;
                }
            }
        }
        return res;

    }

    public static int jumpingOnClouds(List<Integer> c) {
        int n = c.size();
        int count = 0;
        for (int i = 0; i < n - 1; i++) {
            if (c.get(i) == 0) i++;
            count++;
        }
        return count;
    }

    public static int findDigits(int n) {
        int count = 0;
        int temp = n;
        while (temp > 0) {
            int current = temp % 10;
            temp = temp / 10;
            if (current != 0 && n % current == 0) {
                count++;
            }
        }
        return count;
    }

    public static int jumpingOnClouds(int[] c, int k) {
        int e = 100;
        int cloud = 0;
        int n = c.length;
        for (int i = 0; i < n; i++) {
            cloud = (cloud + k) % n;
            if (c[cloud] == 1) {
                e -= 3;
            } else {
                e -= 1;
            }
            if (cloud == 0)
                break;
        }
        return e;
    }

    public static List<Integer> permutationEquation(List<Integer> p) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i <= p.size(); i++) {
            int px = p.indexOf(i) + 1;
            int py = p.indexOf(px) + 1;
            res.add(py);
        }
        return res;
    }

    public static int saveThePrisoner(int n, int m, int s) {
        int lastPrisoner = (s + m - 1) % n;
        return (lastPrisoner == 0) ? n : lastPrisoner;
    }


    public static int viralAdvertising(int n) {
        int startPeople = 5;
        int like = 0;
        int counterLike = 0;
        for (int i = 0; i < n; i++) {
            like = startPeople / 2;
            counterLike += like;
            startPeople = like * 3;
        }
        return counterLike;
    }

    public static int designerPdfViewer(List<Integer> h, String word) {
        int maxHeight = 0;
        for (Character c : word.toCharArray()) {
            int index = alfavit.get(c);
            maxHeight = Math.max(maxHeight, h.get(index));
        }

        return maxHeight * word.length();
    }

    public static int hurdleRace(int k, List<Integer> height) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < height.size(); i++) {
            if (height.get(i) > max) {
                max = height.get(i);
            }
        }
        return max > k ? max - k : k;

    }

    public static int kthSmallest(int[][] matrix, int k) {
        int left = matrix[0][0];
        int right = matrix[matrix.length - 1][matrix[0].length - 1];

        while (left < right) {
            int mid = (left + right) / 2;
            int count = 0;
            int n = matrix.length - 1;
            for (int i = 0; i < matrix.length; i++) {
                while (n >= 0 && matrix[i][n] > mid)
                    n--;
                count += (n + 1);
            }
            if (count < k) {
                left = mid + 1;
            } else
                right = mid;
        }
        return left;
    }


    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};

        int left = binarySearch(nums, target, true);
        int right = binarySearch(nums, target, false);

        result[0] = left;
        result[1] = right;

        return result;
    }

    private static int binarySearch(int[] nums, int target, boolean isFirst) {
        int left = 0, right = nums.length - 1;
        int result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                result = mid;
                if (isFirst)
                    right = mid - 1;
                else
                    left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else
                right = mid - 1;
        }
        return result;
    }

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] >= nums[left]) {
                if (nums[left] <= target && target <= nums[mid]) {
                    right = mid - 1;
                } else
                    left = mid + 1;
            } else {
                if (nums[mid] <= target && target <= nums[right]) {
                    left = mid + 1;
                } else
                    right = mid - 1;
            }
        }
        return -1;

    }

    public static void nextPermutation(int[] nums) {
        int i = nums.length - 2;

        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        reverse(nums, i + 1, nums.length - 1);
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();

        makeCombination(candidates, target, 0, new ArrayList<>(), 0, res);
        return res;
    }

    private void makeCombination(int[] candidates, int target, int idx, List<Integer> comb, int total, List<List<Integer>> res) {
        if (total == target) {
            res.add(new ArrayList<>(comb));
            return;
        }

        if (total > target || idx >= candidates.length) {
            return;
        }

        comb.add(candidates[idx]);
        makeCombination(candidates, target, idx, comb, total + candidates[idx], res);
        comb.remove(comb.size() - 1);
        makeCombination(candidates, target, idx + 1, comb, total, res);
    }

    public String simplifyPath(String path) {
        Deque<String> stack = new LinkedList<>();
        for (String s : path.split("/")) {
            if (s.isEmpty() || s.equals("."))
                continue;
            if (s.equals("..")) {
                if (!stack.isEmpty())
                    stack.pop();
            } else
                stack.push(s);
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, "/" + stack.pop());
        }
        return sb.isEmpty() ? "/" : sb.toString();
    }

    public static int maxSubArray(int[] nums) {
        int sum = nums[0], max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(nums[i], nums[i] + max);
            sum = Math.max(max, sum);
        }
        return sum;
    }

    public static boolean isPalindrome(String s) {
        s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {
            if (chars[i] != chars[chars.length - 1 - i]) {
                System.out.println(chars[i]);
                return false;
            }
        }
        return true;
    }


    public static int numOfSubarrays(int[] arr) {
        final int MOD = 1000000007;
        int oddCount = 0;
        int evenCount = 1;
        int currentSum = 0;
        int result = 0;

        for (int num : arr) {
            currentSum += num;

            if (currentSum % 2 == 0) {
                result = (result + oddCount) % MOD;
                evenCount++;
            } else {
                result = (result + evenCount) % MOD;
                oddCount++;
            }
        }

        return result;
    }


    public static int libraryFine(int d1, int m1, int y1, int d2, int m2, int y2) {
        if (y1 > y2) {
            return 10000;
        } else if (y1 == y2) {
            if (m1 > m2)
                return 500 * (m1 - m2);
            else if (m1 == m2 && d1 > d2)
                return 15 * (d1 - d2);
        }
        return 0;
    }

    public static int squares(int a, int b) {
        int min = (int) Math.ceil(Math.sqrt(a));
        int max = (int) Math.floor(Math.sqrt(b));

        return Math.max(0, max - min + 1);
    }


    public static String appendAndDelete(String s, String t, int k) {
        if (k > s.length() + t.length())
            return "Yes";

        int same = Math.min(s.length(), t.length());
        for (int i = 0; i < same; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                same = i;
                break;
            }
        }
        k -= (s.length() - same);
        k -= (t.length() - same);
        return (k >= 0 && k % 2 == 0) ? "Yes" : "No";
    }

    public static void extraLongFactorials(int n) {
        BigInteger res = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            res = res.multiply(BigInteger.valueOf(i));
        }
        System.out.println(res);
    }

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            }
            System.out.println(stack);
            if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty() || !isMatchingPair(stack.pop(), c)) {
                    System.out.println(stack);
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '{' && close == '}') ||
                (open == '[' && close == ']');
    }

    public static List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }

        Map<Character, String> phoneMap = new HashMap<>();
        phoneMap.put('2', "abc");
        phoneMap.put('3', "def");
        phoneMap.put('4', "ghi");
        phoneMap.put('5', "jkl");
        phoneMap.put('6', "mno");
        phoneMap.put('7', "pqrs");
        phoneMap.put('8', "tuv");
        phoneMap.put('9', "wxyz");

        List<String> combinations = new ArrayList<>();
        combinations.add("");

        for (char digit : digits.toCharArray()) {
            String letters = phoneMap.get(digit);
            List<String> newCombinations = new ArrayList<>();

            for (String combination : combinations) {
                for (char letter : letters.toCharArray()) {
                    newCombinations.add(combination + letter);
                }
            }

            combinations = newCombinations;
        }

        return combinations;
    }

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closestSum = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == target) {
                    return sum;
                }
                if (Math.abs(sum - target) < Math.abs(closestSum - target))
                    closestSum = sum;
                if (sum < target)
                    left++;
                else
                    right--;


            }
        }
        return closestSum;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1, right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else
                    right--;

            }
        }
        return result;


    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String s = strs[0];

        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(s) != 0) {
                s = s.substring(0, s.length() - 1);
            }
            if (s.isEmpty())
                return "";
        }


        return s;
    }

    public static int romanToInt(String s) {

        int result = 0;
        int total = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            char symbol = s.charAt(i);
            int symbolValue = map.get(symbol);

            if (symbolValue < result) {
                total -= symbolValue;
            } else
                total += symbolValue;

            result = symbolValue;
        }
        return total;
    }

    public static String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder roman = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                roman.append(symbols[i]);
                num -= values[i];
            }
        }

        return roman.toString();
    }

    public int maxArea(int[] height) {
        int max = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            max = Math.max(max, Math.min(height[left], height[right]) * (right - left));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }


    public static String listSquared(long m, long n) {
        Map<Long, Long> map = new LinkedHashMap<>();

        for (long i = m; i <= n; i++) {
            long sum = 0;
            for (long j = 1; j * j <= i; j++) {
                if (i % j == 0) {
                    sum += j * j;
                    if (j != i / j) {
                        sum += (i / j) * (i / j);
                    }
                }
            }
            if (isPerfectSquare(sum)) {
                map.put(i, sum);
            }
        }

        StringBuilder result = new StringBuilder("[");
        for (Map.Entry<Long, Long> entry : map.entrySet()) {
            result.append("[").append(entry.getKey()).append(", ").append(entry.getValue()).append("], ");
        }

        if (result.length() > 1) {
            result.setLength(result.length() - 2);
        }
        result.append("]");

        return result.toString();
    }

    private static boolean isPerfectSquare(long num) {
        long sqrt = (long) Math.sqrt(num);
        return (sqrt * sqrt == num);
    }

    public static String longToIP(long ip) {
        int byte1 = (int) (ip >> 24) & 0xFF;
        int byte2 = (int) (ip >> 16) & 0xFF;
        int byte3 = (int) (ip >> 8) & 0xFF;
        int byte4 = (int) ip & 0xFF;
        return byte1 + "." + byte2 + "." + byte3 + "." + byte4;
    }

    public static String rgb(int r, int g, int b) {
        r = Math.max(0, Math.min(r, 255));
        g = Math.max(0, Math.min(g, 255));
        b = Math.max(0, Math.min(b, 255));

        return String.format("#%02X%02X%02X", r, g, b);
    }


    public static int cakes(Map<String, Integer> recipe, Map<String, Integer> available) {
        int maxCakes = Integer.MAX_VALUE;

        for (Map.Entry<String, Integer> entry : recipe.entrySet()) {
            String ingredient = entry.getKey();
            int amountNeeded = entry.getValue();

            int amountAvailable = available.getOrDefault(ingredient, 0);

            int maxCakesWithIngredient = amountAvailable / amountNeeded;

            maxCakes = Math.min(maxCakes, maxCakesWithIngredient);
        }

        return maxCakes == Integer.MAX_VALUE ? 0 : maxCakes;
    }

    public static BigInteger perimeter(BigInteger n) {
        if (n.equals(BigInteger.ZERO))
            return BigInteger.valueOf(4);

        BigInteger a = BigInteger.ONE;
        BigInteger b = BigInteger.ONE;
        BigInteger sum = a.add(b);

        for (BigInteger i = BigInteger.valueOf(2); i.compareTo(n) <= 0; i = i.add(BigInteger.ONE)) {
            BigInteger next = a.add(b);
            sum = sum.add(next);
            a = b;
            b = next;
        }

        return sum.multiply(BigInteger.valueOf(4));
    }

    public static String reverseLetter(final String str) {
        char[] array = str.toCharArray();
        StringBuilder res = new StringBuilder();
        for (int j = str.length() - 1; j >= 0; j--) {
            res.append(array[j]);
        }
        res = new StringBuilder(res.toString().replaceAll("[^a-zA-Z]", ""));
        return res.toString();
    }

    public static boolean isAlt(String word) {
        return word.matches("[aeiou]?([^aeiou][aeiou])*[^aeiou]?");
    }

    public static int[] flattenAndSort(int[][] array) {
        int totalLength = 0;
        for (int[] row : array) {
            totalLength += row.length;
        }
        int[] arrayInts = new int[totalLength];

        int index = 0;
        for (int[] row : array) {
            for (int i : row) {
                arrayInts[index++] = i;
            }
        }
        return Arrays.stream(arrayInts).sorted().toArray();
        //return Arrays.stream(array).flatMapToInt(Arrays::stream).sorted().toArray();
    }

    public static List<Integer> sumConsecutives(List<Integer> s) {
        List<Integer> res = new ArrayList<>();
        int sum = s.get(0);
        for (int i = 1; i < s.size(); i++) {
            if (s.get(i).equals(s.get(i - 1))) {
                sum += s.get(i);
            } else {
                res.add(sum);
                sum = s.get(i);
            }
        }
        res.add(sum);
        return res;
    }

    public static int largestPairSum(int[] numbers) {
        int sum = Integer.MIN_VALUE;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (sum < numbers[i] + numbers[j]) {
                    sum = numbers[i] + numbers[j];
                }
            }
        }
        return sum;

       /* if (numbers.length < 2) {
            throw new IllegalArgumentException("Array must contain at least two elements.");
        }

        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;

        for (int number : numbers) {
            if (number > max1) {
                max2 = max1; // Обновляем второй максимальный элемент
                max1 = number; // Обновляем максимальный элемент
            } else if (number > max2) {
                max2 = number; // Обновляем второй максимальный элемент
            }
        }

        return max1 + max2;*/
    }
}
