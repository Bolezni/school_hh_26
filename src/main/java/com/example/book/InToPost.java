package com.example.book;

import java.util.ArrayList;

public class InToPost {
    private CustomStack<Character> stack;
    private String input;
    private String output = "";

    public InToPost(String input) {
        this.input = input;
        stack = new CustomStack<>(input.length());
    }

    public String doTrans(){
        for(int i = 0; i < input.length(); i++){
            char ch = input.charAt(i);
            switch(ch){
                case '+':
                case '-':
                    gotOper(ch,1);
                    break;
                case '*':
                case '/':
                    gotOper(ch,2);
                    break;
                case '(':
                    stack.push(ch);
                    break;
                case ')':
                    gotParen(ch);
                    break;
                default:
                    output += ch;
                    break;
            }
        }
        while (!stack.isEmpty()){
            output += stack.pop();
        }
        return output;
    }

    private void gotOper(char opThis, int prec1){
        while(!stack.isEmpty()){
            char opTop = stack.pop();
            if(opTop == '('){
                stack.push(opTop);
                break;
            }
            else {
                int prec2;
                if(opTop == '+' || opTop == '-')
                    prec2 = 1;
                else
                    prec2 = 2;
                if(prec2 < prec1){
                    stack.push(opTop);
                    break;
                }else
                    output += opTop;
            }
        }
        stack.push(opThis);
    }

    private void gotParen(char ch){
        while(!stack.isEmpty()){
            char opTop = stack.pop();
            if(opTop == '('){
                break;
            }else{
                output += opTop;
            }
        }
    }
}

class ParsePost{
    private final String input;

    public ParsePost(String input){
        this.input = input;
    }

    public int doParse(){
        CustomStack<Integer> stack = new CustomStack<>(20);
        char ch;
        int j;
         int num1, num2, interAns;

         for (j = 0; j < input.length(); j++) {
             ch = input.charAt(j);
             if(ch >= '0' && ch <= '9'){
                 stack.push(ch - '0');
             }else {
                 num2 = stack.pop();
                 num1 = stack.pop();

                 interAns = switch (ch) {
                     case '+' -> num1 + num2;
                     case '-' -> num1 - num2;
                     case '*' -> num1 * num2;
                     case '/' -> num1 / num2;
                     default -> 0;
                 };

                 stack.push(interAns);
             }
         }
         interAns = stack.pop();
         return interAns;
    }
}

class ThisMainInToPost{
    public static void main(String[] args) {
        String input = "2+3*4";
        String output;
        InToPost post = new InToPost(input);
        output = post.doTrans();
        System.out.println(output);
        ParsePost parse = new ParsePost(output);
        int res = parse.doParse();
        System.out.println(res);

    }
}
