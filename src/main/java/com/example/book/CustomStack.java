package com.example.book;

public class CustomStack<T> {
    private final int maxSize;
    private int top;
    private final T[] stack;

    @SuppressWarnings("unchecked")
    public CustomStack(int maxSize) {
        this.maxSize = maxSize;
        stack = (T[]) new Object[maxSize];
        top = -1;
    }

    public void push(T value) {
        stack[++top] = value;
    }

    public T pop() {
        return stack[top--];
    }

    public T peek() {
        return stack[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }
}

class Reverser {
    private String input;
    private StringBuilder output;

    public Reverser(String input) {
        this.input = input;
    }

    public String reverse() {
        int stackSize = input.length();
        CustomStack<Character> stack = new CustomStack<>(stackSize);
        output = new StringBuilder();

        for (int i = 0; i < stackSize; i++) {
            char ch = input.charAt(i);
            stack.push(ch);
        }

        while (!stack.isEmpty()) {
            char c = stack.pop();
            output.append(c);
        }
        return output.toString();
    }
}

class BracketCheck {
    private String input;

    public BracketCheck(String input) {
        this.input = input;
    }

    public boolean check() {
        int stackSize = input.length();
        CustomStack<Character> stack = new CustomStack<>(stackSize);

        for (char ch : input.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            }
            if (ch == ')' || ch == '}' || ch == ']') {
                if (stack.isEmpty() || !isMatchingPair(stack.pop(), ch)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '{' && close == '}') ||
                (open == '[' && close == ']');
    }
}


class ThisMain {
    public static void main(String[] args) {
        BracketCheck bracketCheck = new BracketCheck("{([])}");
        System.out.println(bracketCheck.check());


    }
}
