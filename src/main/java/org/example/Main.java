package org.example;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private User user;
    private Computer computer;

    private enum Move {
        ROCK, PAPER, SCISSORS;

        public int compareMoves(Move otherMove) {
            if (this == otherMove)
                return 0;

            switch (this) {
                case ROCK:
                    return (otherMove == SCISSORS ? 1 : -1);
                case PAPER:
                    return (otherMove == ROCK ? 1 : -1);
                case SCISSORS:
                    return (otherMove == PAPER ? 1 : -1);
            }
            return 0;
        }
    }

    private class User {
        private Scanner inputScanner;

        public User() {
            inputScanner = new Scanner(System.in);
        }

        public Move getMove() {
            System.out.print("Rock(R), Scissors (S) or Paper (P)? (input first letter)");
            String userInput = inputScanner.nextLine();
            userInput = userInput.toUpperCase();
            char firstLetter = userInput.charAt(0);
            if (firstLetter == 'R' || firstLetter == 'P' || firstLetter == 'S') {
                switch (firstLetter) {
                    case 'R':
                        return Move.ROCK;
                    case 'P':
                        return Move.PAPER;
                    case 'S':
                        return Move.SCISSORS;
                }
            }
            return getMove();
        }

        public boolean playAgain() {
            System.out.print("Would you like to play yet? (Y/N) ");
            String userInput = inputScanner.nextLine();
            userInput = userInput.toUpperCase();
            return userInput.charAt(0) == 'Y';
        }
    }

    private class Computer {
        public Move getMove() {
            Move[] moves = Move.values();
            Random random = new Random();
            int index = random.nextInt(moves.length);
            return moves[index];
        }
    }

    public Main() {
        user = new User();
        computer = new Computer();
    }

    public void startGame() {
        System.out.println("Welcome to game Rock, Scissors or Paper!");
        Move userMove = user.getMove();
        Move computerMove = computer.getMove();
        System.out.println("\nInput your move " + userMove + ".");
        System.out.println("Computer move " + computerMove + ".\n");

        int compareMoves = userMove.compareMoves(computerMove);
        switch (compareMoves) {
            case 0:
                System.out.println("Draw!");
                break;
            case 1:
                System.out.println(userMove + " against " + computerMove + ". You won!");
                break;
            case -1:
                System.out.println(computerMove + " against " + userMove + ". You lost.");
                break;
        }

        if (user.playAgain()) {
            System.out.println();
            startGame();
        } else {
            System.out.println("See you.");
        }
    }

    public static void main(String[] args) {
        Main game = new Main();
        game.startGame();
    }
}