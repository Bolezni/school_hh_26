package com.example.algorithms;

import java.util.*;


public class Main {
    private static final int MOD = 998244353;

    public static void main(String[] args) {

    }

    public void rotate(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix[i].length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length / 2; j++) {
                int temp = matrix[i][matrix[i].length - j - 1];
                matrix[i][matrix[i].length - j - 1] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }

    }

    public static int maxProfit(int[] prices) {
        int min = prices[0];
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            } else if (prices[i] - min > profit) {
                profit = prices[i] - min;
            }
        }
        return profit;
    }

    public int solve6(String A, int B) {
        int countA = 0;
        int currentCount = 0;
        for (int i = 0; i < A.length(); i++) {
            if (i % B == 0) {
                countA = Math.max(countA, currentCount);
                currentCount = 0;
            }
            if (A.charAt(i) == 'a')
                currentCount++;
        }
        countA = Math.max(countA, currentCount);
        return countA;
    }

    //Фабрика по производству чипсов упаковывает чипсы в пакеты.
    //Пакеты чипсов представлены в виде массива A.
    //Найдите пустые пакеты чипсов (Ai = 0) и переместите их в конец массива.
    //Верните полученный массив.
    public static ArrayList<Integer> solve5(ArrayList<Integer> A) {
        int n = A.size() - 1;
        int j = 0;
        for (int i = 0; i <= n; i++) {
            if (A.get(i) != 0) {
                int temp = A.get(i);
                A.set(i, A.get(j));
                A.set(j, temp);
                j++;
            }
        }
        return A;
    }


    //Даны два целочисленных массива A и B и целое число C.
    //Найдите количество целых чисел в A, которые больше C, и найдите количество целых чисел в B, которые меньше C.
    //Верните максимальное из этих двух значений.
    public int solve4(ArrayList<Integer> A, ArrayList<Integer> B, int C) {
        int countInA = 0;
        int countInB = 0;
        for (int num : A) {
            if (num > C) {
                countInA++;
            }
        }
        for (int num : B) {
            if (num < C) {
                countInB++;
            }
        }
        return Math.max(countInA, countInB);
    }

    public static int solve3(int A) {
        int oddCount = (A + 1) / 2;
        int eventCount = A / 2;
        return Math.max((oddCount + 1) / 2, (eventCount + 1) / 2);
    }

    public int solve(int A, int B, int C) {
        int first = A;
        while (first <= B && first % 10 != C) {
            first++;
        }
        if (first > B) {
            return 0;
        }

        int last = B;
        while (last >= B && last % 10 != C) {
            last--;
        }

        return (last - first) / 10 + 1;
    }

    public int solve2(ArrayList<Integer> A) {
        int max = Integer.MIN_VALUE;
        int n = A.size();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int l = k + 1; l < n; l++) {
                        int xor = A.get(i) ^ A.get(j) ^ A.get(k) ^ A.get(l);
                        max = Math.max(max, xor);
                    }
                }
            }
        }
        return max;
    }

    public int solve(ArrayList<Integer> A) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int totalCount = 0;
        for (int num : A) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            totalCount = Math.max(totalCount, entry.getValue());
        }
        return totalCount;
    }

    class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode solve(ListNode A, int B) {
        ListNode current = A;

        while (current != null) {
            int value = current.val;
            value = value / B;
            current.val = B * value;
            current = current.next;
        }

        return A;
    }

    public int majorityElement(final List<Integer> A) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (Integer i : A) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        Integer max = null;
        double maxCount = Math.floor((double) A.size() / 2);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                max = entry.getKey();
            }
        }

        return max;
    }

    public static int bulbs(List<Integer> A) {
        int count = 0;
        for (int i = 0; i < A.size(); i++) {
            if (A.get(i) == 1) {
                count++;
                continue;
            } else if (A.get(i) == 0) {
                A.set(i, 1);
                if (i + 1 < A.size()) {
                    A.set(i + 1, 0);
                } else {
                    A.set(i, 0);
                }

                count++;
            }
        }
        return count;
    }


    public static int canCompleteCircuit(final List<Integer> A, final List<Integer> B) {
        int totalFuel = 0;
        int totalCost = 0;
        int currentFuel = 0;
        int startIndex = 0;
        for (int i = 0; i < A.size(); i++) {
            totalFuel += A.get(i);
            totalCost += B.get(i);
            currentFuel += A.get(i) - B.get(i);

            if (currentFuel < 0) {
                startIndex = i + 1;
                currentFuel = 0;
            }
        }
        return totalFuel < totalCost ? -1 : startIndex;
    }

    private static long modPow(long base, int exp, int mod) {
        long result = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1;
        }
        return result;
    }

    private static boolean areCollinear(int[] p1, int[] p2, int[] p3) {
        return (p1[0] * (p2[1] - p3[1]) + p2[0] * (p3[1] - p1[1]) + p3[0] * (p1[1] - p2[1])) == 0;
    }

    private static long getMaxBouquetPrice(long budget, long[] flowerPrices) {
        long maxBouquetPrice = -1;

        for (int i = 0; i < flowerPrices.length; i++) {
            for (int j = i + 1; j < flowerPrices.length; j++) {
                for (int k = j + 1; k < flowerPrices.length; k++) {
                    long totalPrice = flowerPrices[i] + flowerPrices[j] + flowerPrices[k];
                    if (totalPrice <= budget) {
                        maxBouquetPrice = Math.max(maxBouquetPrice, totalPrice);
                    }
                }
            }
        }
        return maxBouquetPrice;
    }

    private static int calculateMinimumAdjustments(int n, int m, int[] distances) {
        int minDistance = distances[0]; // Distance on the first day
        int maxDistance = distances[1]; // Distance on the second day

        int goodDays = 0;
        List<Integer> adjustments = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (distances[i] >= minDistance && distances[i] <= maxDistance) {
                goodDays++;
            } else {
                int neededAdjustments = 0;
                if (distances[i] < minDistance) {
                    neededAdjustments = minDistance - distances[i];
                } else if (distances[i] > maxDistance) {
                    neededAdjustments = distances[i] - maxDistance;
                }
                adjustments.add(neededAdjustments);
            }
        }
        return goodDays;
    }


    private static String RSM(String input) {
        int indexM = -1;
        int indexR = -1;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'M') {
                indexM = i;
            }
            if (input.charAt(i) == 'R') {
                indexR = i;
            }
        }
        if (indexM != -1 && indexR != -1) {
            if (indexM > indexR) {
                return "Yes";
            } else {
                return "No";
            }
        }

        return "No";
    }


    public static void quickSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        // Инициализация стека
        Stack<Integer> stack = new Stack<>();
        stack.push(0); // Индекс первого элемента
        stack.push(arr.length - 1); // Индекс последнего элемента

        // Итеративная сортировка
        while (!stack.isEmpty()) {
            // Получаем верхние индексы из стека
            int high = stack.pop();
            int low = stack.pop();

            // Разделяем массив и получаем индекс опорного элемента
            int pivotIndex = partition2(arr, low, high);

            // Если есть элементы слева от опорного элемента, добавляем их в стек
            if (pivotIndex - 1 > low) {
                stack.push(low);
                stack.push(pivotIndex - 1);
            }

            // Если есть элементы справа от опорного элемента, добавляем их в стек
            if (pivotIndex + 1 < high) {
                stack.push(pivotIndex + 1);
                stack.push(high);
            }
        }
    }

    private static int partition2(int[] arr, int low, int high) {
        int pivot = arr[high]; // Опорный элемент
        int i = low; // Индекс для меньших элементов

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                // Меняем местами arr[i] и arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
            }
        }

        // Меняем местами arr[i] и arr[high] (опорный элемент)
        int temp = arr[i];
        arr[i] = arr[high];
        arr[high] = temp;

        return i; // Возвращаем индекс опорного элемента
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static long countGoodNumbers(long l, long r) {
        long count = 0;

        for (long i = l; i <= r; i++) {
            long lastNumber = i / 10;
            long firstNumber = i % 10;

            if (lastNumber == 0 || lastNumber == firstNumber) {
                count++;
            }
        }
        return count;
    }

    private static List<Long> calculateGains(long number) {
        List<Long> gains = new ArrayList<>();
        int weight = 1; // Начальный весовой коэффициент
        while (number > 0) {
            int digit = (int) (number % 10); // Получаем последнюю цифру
            long gain = (long) (9 - digit) * weight; // Вычисляем возможное увеличение
            if (gain > 0) {
                gains.add(gain); // Добавляем увеличение в список
            }
            weight *= 10; // Увеличиваем весовой коэффициент
            number /= 10; // Убираем последнюю цифру
        }
        return gains;
    }

    private static int[] rearrangementOfNumbers(int[] arr) {
        int[] newArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {

            newArr[arr[i] - 1] = i + 1;
        }
        return newArr;
    }


    private static int determinesHaveTwoAdjacent(int[] arr) {
        int count = 0;
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
                count++;
            }
        }
        return count;
    }

    private static int[] shiftArray(int[] array, int k) {
        if (array.length == 0 || array.length == 1) return array;

        k = k % array.length;
        int[] temp = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            temp[(i + k) % array.length] = array[i];
        }
        System.arraycopy(temp, 0, array, 0, array.length);
        return array;
    }

    private static List<Integer> reverseRangeMinAndMax(List<Integer> list) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        int positionMin = 0;
        int positionMax = 0;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) > max) {
                max = list.get(i);
                positionMax = i;
            }
            if (list.get(i) < min) {
                min = list.get(i);
                positionMin = i;
            }
        }

        if (positionMin > positionMax) {
            int temp = positionMin;
            positionMin = positionMax;
            positionMax = temp;
        }

        while (positionMin < positionMax) {
            int temp = list.get(positionMin);
            list.set(positionMin, list.get(positionMax));
            list.set(positionMax, temp);
            positionMax--;
            positionMin++;
        }

        return list;
    }

    private static List<Integer> calculateMinusAndPluse(List<Integer> list) {
        int countMinus = 0;
        int countPlus = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) < 0) {
                countMinus++;
            }
            if (list.get(i) > 0) {
                countPlus++;
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) < 0) {
                list.set(i, list.get(i) - countPlus);
            } else
                list.set(i, list.get(i) - countMinus);
        }

        return list;
    }

    private static int countCurrentNumber(int[] arr, int k) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == k) {
                count++;
            }
        }
        return count;
    }

    private static List<Integer> calculate(List<Integer> arr) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int positionMin = 0;
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) > max) {
                max = arr.get(i);
            }
            if (arr.get(i) < min) {
                min = arr.get(i);
                positionMin = i;
            }
        }
        for (int i = positionMin + 1; i < arr.size(); i++) {
            arr.set(i, arr.get(i) - max);
        }
        return arr;
    }

    private static int[] calculate(int[] arr) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int positionMin = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
                positionMin = i;
            }
        }
        for (int i = positionMin + 1; i < arr.length; i++) {
            arr[i] -= max;
        }
        return arr;
    }

    private static void countingSortInsertion(int[] array) {
        int[] count = new int[101];
        for (int i = 0; i < array.length; i++) {
            count[array[i]]++;

            int position = 0;
            for (int j = 1; j <= 100; j++) {
                if (j < array[i]) {
                    position += count[j];
                } else if (j == array[i]) {
                    position += count[j] - 1;
                    break;
                }

            }
            System.out.print(position + " ");
        }
    }


    private static int countingUnique(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }

        int max = Arrays.stream(arr).max().getAsInt();

        int[] count = new int[max + 1];
        for (int num : arr) {
            count[num]++;
        }

        int unique = 0;
        for (int num : count) {
            if (num > 0)
                unique++;
        }
        return unique;
    }

    private static void simpleCountingSort(int[] arr) {
        if (arr.length == 0) {
            return;
        }

        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();

        int range = max - min + 1;
        int[] counting = new int[range];

        for (int num : arr) {
            counting[num - min]++;
        }

        int index = 0;
        for (int i = 0; i < counting.length; i++) {
            while (counting[i] > 0) {
                arr[index++] = i + min;
                counting[i]--;
            }
        }
    }

    private static void selection_sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min])
                    min = j;
            }
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
    }

    private static void insert_sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > temp) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = temp;
        }
    }

    private static void splitting(int n, int k, List<Integer> current, List<List<Integer>> newResult) {
        if (n == 0) {
            newResult.add(new ArrayList<>(current));
            return;
        }
        for (int i = Math.min(n, k); i > 0; i--) {
            current.add(i);
            splitting(n - i, i, current, newResult);
            current.remove(current.size() - 1);
        }
    }

    private static boolean isPalindrome(String s) {
        StringBuilder reverse = new StringBuilder();

        for (int i = s.length() - 1; i >= 0; i--)
            reverse.append(s.charAt(i));

        return s.contentEquals(reverse);
    }

    private static int calculate(int n) {
        Map<Integer, Integer> map = new HashMap<>();

        if (n <= 1)
            return 1;

        if (map.containsKey(n)) {
            return map.get(n);
        }

        int initial_value = (n * n) / 3 + calculate(n - 2);

        while (initial_value % 2 == 0)
            initial_value /= 2;

        initial_value += calculate(2 * n / 3);
        int value = calculate(initial_value % n);

        int res = value + calculate(n / 2) + n;
        map.put(n, res);

        return res;
    }

    private static int countZeros(int n) {
        if (n == 0)
            return 0;

        if (n % 10 == 0)
            return 1 + countZeros(n / 10);
        else
            return countZeros(n / 10);
    }

    private static boolean isPalindrome_recursive(String s) {
        s = s.toLowerCase();

        if (s.length() == 0 || s.length() == 1)
            return true;

        if (s.charAt(0) == s.charAt(s.length() - 1)) {
            return isPalindrome_recursive(s.substring(1, s.length() - 1));
        }
        return false;
    }

    private static void hanoiTowers(int n, int source, int target, int auxiliary) {
        if (n == 1) {
            System.out.println("Переместите диск с " + source + " на " + target);
        } else {
            hanoiTowers(n - 1, source, auxiliary, target);

            System.out.println("Переместите диск с " + source + " на " + target);

            hanoiTowers(n - 1, auxiliary, target, source);
        }
    }

    private static double calculatePower(int base, int exponent) {
        if (exponent == 0)
            return 1;

        return base * calculatePower(base, exponent - 1);
    }

    private static int countDigits(int n) {
        if (n == 0) return 0;
        int res = 0;
        int count = 0;

        while (n > 0) {
            res = n % 10;
            n /= 10;
            if (res < 10)
                count++;
        }
        return count;
    }

    private static int countDigits_recursive(int n) {
        if (n == 0) return 0;

        if (n < 0)
            n = -n;

        return 1 + countDigits_recursive(n / 10);

    }

    private static int recursive_sum(int[] arr, int start, int end) {
        if (start >= end)
            return 0;

        if (start == end - 1)
            return arr[start];

        int mid = (start + end) / 2;

        int leftSum = recursive_sum(arr, start, mid);
        int rightSum = recursive_sum(arr, mid, end);

        return leftSum + rightSum;
    }

    private static int fibonacci(int n) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    private static void bubble_sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    private static int binary_search(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            }
            if (arr[mid] < target) {
                left = mid + 1;
            }
            if (arr[mid] > target) {
                right = mid - 1;
            }
        }
        return -1;
    }

    private static int binary_search(int[] arr, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = left + (right - left) / 2;
        if (arr[mid] == target) {
            return mid;
        }
        if (arr[mid] > target) {
            return binary_search(arr, target, left, mid - 1);
        } else
            return binary_search(arr, target, mid + 1, right);
    }

    private static String countProgrammerAndProgrammistok(int N, int M) {
        StringBuilder sb = new StringBuilder();

        if (N > M + 1 || M > N + 1) {
            System.out.println("NO SOLUTION");
        }
        while (N > 0 || M > 0) {
            if (N > 0) {
                sb.append("B");
                N--;
            }
            if (M > 0) {
                sb.append("G");
                M--;
            }
        }

        if (N > 0) {
            sb.append("B".repeat(N));
        } else if (M > 0) {
            sb.append("G".repeat(M));
        }

        return sb.toString();
    }


    private static int KateVSMasha(int k, int m, int a, int b) {
        int countK = b / k - (a - 1) / k;
        int countM = b / m - (a - 1) / m;
        return countK - countM;
    }

}
