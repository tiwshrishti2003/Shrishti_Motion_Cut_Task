package com.Task1;

import java.util.Scanner;

public class Nogg {
/*
    Author: Shrishti Tiwari
    Date: 26-Sep-2023
    Description: Number Guessing Game
 */
    //Chose Number
    private static final int CHOSEN_NUMBER = 55;
    //Number of Retries
    private static final int MAX_TRIES = 5;
    public static void main(String[] args) {

        //Number of Retries
        int k = Nogg.MAX_TRIES;

        //Read User Input
        Scanner input = new Scanner(System.in);

        //If the number guessed is wrong, the flag will be true
        boolean correct = true;

        System.out.println("Hello user!");
        System.out.println("Please select a number from 1 to 100");
        int guess = -1;
        while (k > 0) {
            System.out.println("Enter your guess");
            guess = input.nextInt();
            if (guess == Nogg.CHOSEN_NUMBER) {
                System.out.println("You have guessed the number right");
                break;
            }
            else if (guess < Nogg.CHOSEN_NUMBER) {
                System.out.println("Your guess is too low");
            }
            else if (guess > Nogg.CHOSEN_NUMBER ) {
                System.out.println("Your guess is too high");
            }
            k--;
        }
        if (k == 0) {
            System.out.println("You ran out of number of tries");
        }
    }
}
