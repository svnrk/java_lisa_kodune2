package com.company;

import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    static String inputString;
    static int randNumber;
    static int guessedNumber = 0;
    static Boolean gameOn = true;

    static int moneyLost = 0;
    static String introText = "Want to win a \u001b[33mmillion dollars\u001b[0m?%n" +
            "If so, guess the winning number (a number between 0 and 100000).%n%n";
    static String nextText = "Insert $1.00 and enter your number or 'q' to quit:";
    static String wrongGuessText = "Sorry, good guess, but not quite right. Do you want me to give you a hint (y|n)?";
    static String hintTextLow = "Your number is too low!";
    static String hintTextHigh = "Your number is too high!";
    static String lostGameText = "You lost $￿%d.00. Thanks for playing. Come again!";
    static String wonGameText = "%n\u001b[33mYOU WIN $%d.00";

    static void numberGuessingGame() {
        System.out.printf(introText);

        randNumber = randomNumberGenerator(100000);
        while (gameOn) {
            System.out.println(nextText);
            Scanner input = new Scanner(System.in);
            checkInputInt(input);

            inputString = input.next();
            checkInputString(inputString);
        }
    }

    private static void checkInputString(String inputString) {
        if (inputString.equals("q")) {
            gameOn = false;
            System.out.printf(lostGameText, moneyLost);
            System.exit(0);
        } else if (inputString.equals("y")) {
            moneyLost += 2;
            if (guessedNumber > randNumber) {
                System.out.println(hintTextHigh);
            } else {
                System.out.println(hintTextLow);
            }
        }
    }

    private static void checkInputInt(Scanner input) {
        if (input.hasNextInt()) {
            moneyLost++;
            guessedNumber = input.nextInt();
            System.out.print(guessedNumber + ": ");
            if (guessedNumber == randNumber) {
                System.out.printf(wonGameText, 1000000 - moneyLost);
                gameOn = false;
            } else {
                System.out.println(wrongGuessText);
            }
        }
    }

    private static int randomNumberGenerator(int bound) {
        Random rand = new Random();
        randNumber = rand.nextInt(bound);
        return randNumber;
    }
}
