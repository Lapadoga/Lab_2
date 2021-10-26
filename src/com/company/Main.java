package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("The calculator perceives: \n" +
                "+ (addition)\n" +
                "- (subtraction and unary minus)\n" +
                "* (multiplication)\n" +
                "/ (division)\n" +
                "\"(\"and \")\"" +
                "\n Insert the expression: ");
        try {
            while (true) {
                String exp = sc.nextLine();
                Calculator pars = new Calculator(exp);
                System.out.println(pars.computing());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
