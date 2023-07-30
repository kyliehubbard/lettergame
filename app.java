/*
CISC-124 2023 Summer
Name: Kylie Hubbard
Student Number: 20294570
Email: kylie.hubbard@queensu.ca
Date: 2023-07-14
I confirm that this assignment solution is my own work and conforms to Queen’s
standards of Academic Integrity.

This program is a two player game that provides a randomized set of letters. Each player is required
to make words based on the randomizedletters provided until they are unable to. At the end of the game, 
the scores are added up and the player with the most points wins.
*/

import java.util.Random;
import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Set;
import java.util.HashSet;



class RandomLettersGame{
    private static int score1; 
    private static int score2;

    public RandomLettersGame(){
    /*
    -------------------------------------------------------
    Use: to initiate the users total score
    -------------------------------------------------------
    Parameters:
        None
    Returns:
        None
    -------------------------------------------------------
    */
        score1 = 0;
        score2 = 0;
    }

    public static int letterValue1(String word5){
    /*
    -------------------------------------------------------
    Use: to track the point value of each word inputted by
    the first user.
    -------------------------------------------------------
    Parameters:
        word5 - the word inputted by the user
    Returns:
        The added up score of the user with each word.
    -------------------------------------------------------
    */
        int length = word5.length();
    
        switch(length){
            case 1:
                score1 += 1;
                break;
            case 2:
                score1 += 2;
                break;
            case 3:
                score1 += 3;
                break;
            case 4:
                score1 += 3;
                break;
            case 5:
                score1 += 4;
                break;
            case 6:
                score1 += 5;
                break;
            case 7:
                score1 += 6;
                break;
            default:
                score1 += 10;
                break;
        }
        return score1;
    }

    public static int letterValue2(String word6){
    /*
    -------------------------------------------------------
    Use: to track the point value of each word inputted by
    the second user.
    -------------------------------------------------------
    Parameters:
        word6 - the word inputted by the user
    Returns:
        The added up score of the user with each word.
    -------------------------------------------------------
    */
        int length = word6.length();
        
        switch(length){
            case 1:
                score2 += 1;
                break;
            case 2:
                score2 += 2;
                break;
            case 3:
                score2 += 3;
                break;
            case 4:
                score2 += 3;
                break;
            case 5:
                score2 += 4;
                break;
            case 6:
                score2 += 5;
                break;
            case 7:
                score2 += 6;
                break;
            default:
                score2 += 10;
                break;
        }
        return score2;
    }

    public int getScore1(){
    /*
    -------------------------------------------------------
    Use: to be able to obtain the score of the first user.
    -------------------------------------------------------
    Parameters:
        None
    Returns:
        the added up score based on user input
    -------------------------------------------------------
    */
        return score1;
    }
    public int getScore2(){
    /*
    -------------------------------------------------------
    Use: to be able to obtain the score of the second user.
    -------------------------------------------------------
    Parameters:
        None
    Returns:
        the added up score based on user input
    -------------------------------------------------------
    */
        return score2;
    }


    public static boolean usedWord(String word3,Set<String>list1){
    /*
    -------------------------------------------------------
    Use: to check if a word has been inputted multiple times
    -------------------------------------------------------
    Parameters:
        word3 - the word guessed by the user.
        list1 - the list of words created by user input.
    Returns:
        True or False
    -------------------------------------------------------
    */
        if(list1.contains(word3)){
            return false;
        }else{
            list1.add(word3);
            return true;
        }
    }

    public static boolean checkLetters(String word2, String randomLetters, char firstVowel, char secondVowel) {
    /*
    -------------------------------------------------------
    Use: to check if letters have been used that are not part
    of the randomized set given
    -------------------------------------------------------
    Parameters:
        word2 - the word guessed by the user.
        randomLetters - the randomized letters
        firstVowel - first randomized vowel
        secondVowel - second randomized vowel
    Returns:
        True or False
    -------------------------------------------------------
    */
        String allLetters = randomLetters + firstVowel + secondVowel;
        for (int i = 0; i < word2.length(); i++) {
            char letter = Character.toLowerCase(word2.charAt(i));
            if (!allLetters.contains(String.valueOf(letter))) {
                return false;
            }
        }
        return true;
    }

    public static boolean validWord(String word){
    /*
    -------------------------------------------------------
    Use: to check if word guessed is a valid English word.
    -------------------------------------------------------
    Parameters:
        word - the word guessed by the user
    Returns:
        True or False
    -------------------------------------------------------
    */
        try {
            BufferedReader in = new BufferedReader(new FileReader("wordlist.txt"));
            String str;
            while ((str = in.readLine()) != null){
                if (str.equalsIgnoreCase(word)){
                    return true;
                }
            }
            in.close();
        } catch (IOException e){
        }
        return false;
    }
    public static void main(String[] args) {
        System.out.println("Hello, welcome to the word game! Player 1, here are your random letters...");
        Random randomize = new Random();

        // ASCII char codes
        int low = 97;
        int high = 123;
        
        // to ensure users receive at least two vowels in random generator
        char[] vowel = {'a', 'e', 'i', 'o', 'u'};
        Random randomize2 = new Random();
        char vowel1 = vowel[randomize2.nextInt(vowel.length)];
        char vowel2 = vowel[randomize2.nextInt(vowel.length)];

        // to put vowels with array of other randomized letters
        System.out.print(vowel1 + ", ");
        System.out.print(vowel2 + ",");

        // empty string for randomized letters
        String arrayToString = " ";

        // converting ASCII char codes from ints and randomizing
        int[] arr = new int[9]; 
        for (int i = 0; i < arr.length; i++) {
            arr[i] = randomize.nextInt(high-low) + low; 
            arrayToString += (char) arr[i];
            if (i != arr.length - 1) {
                arrayToString += ", ";
            }
        }
        System.out.println(arrayToString);

        // intiating score tracking
        RandomLettersGame calculator = new RandomLettersGame();

        // player 1
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Enter a word based on the letters above! Please type 'DONE' to finish guessing.");
        String answer = scanner1.nextLine();
        String lowerAnswer = answer.toLowerCase(); // users can enter in upper case/lower case and will still output the same result

        // to make a list to track all guessed words
        Set<String>guessedWords = new HashSet<>();

        boolean breakLoop = false;
        
        while(!breakLoop){
            switch(lowerAnswer){
                case "done":
                    System.out.println("Thank you! Your final score is: " + score1 + ". Please type enter to end turn.");
                    breakLoop = true;
                    break;
                default:
                    // all methods below required to check for errors 
                    checkLetters(lowerAnswer, arrayToString, vowel1, vowel2);

                    if (!validWord(lowerAnswer)){
                        System.out.println("This word is invalid.");
                    } else if(!usedWord(lowerAnswer,guessedWords)){
                        System.out.println("Word has already been guessed.");
                    } else {
                        System.out.println("Your score is " + calculator.letterValue1(lowerAnswer));
                    }

                    System.out.println("Guess again?");
                    break;
            }
            lowerAnswer = scanner1.nextLine().toLowerCase();
        }

        // player 2 
        Scanner scanner2 = new Scanner(System.in);

        System.out.println("Player 2, it's your turn!");

        // reprinting same random letters
        System.out.print(vowel1 + ", ");
        System.out.print(vowel2 + ",");
        System.out.println(arrayToString);

        System.out.println("Enter a word based on the letters above! Please type 'QUIT' when you are finished. ");
        String answer2 = scanner2.nextLine();
        String lowerAnswer2 = answer2.toLowerCase(); // users can enter in upper case/lower case and will still output the same result

        // to make a list to track all guessed words
        Set<String>guessedWords2 = new HashSet<>();

        boolean breakLoop2 = false;
        
        while(!breakLoop2){
            switch(lowerAnswer2){
                case "quit":
                    System.out.println("Here are your results...");
                    System.out.println("Player 1's Score: " + score1 + " || " + "Player 2's Score: " + score2 + "\n");
                    if (score1 > score2){
                        System.out.println("Congrats Player 1, you won!");
                    } else if (score2 > score1){
                        System.out.println("Congrats Player 2, you won!");
                    } else {
                        System.out.println("It was a tie!");
                    }
                    System.out.println("See you next time!");
                    breakLoop2 = true;
                    break;
                default:
                    // all methods below required to check for errors 
                    checkLetters(lowerAnswer2, arrayToString, vowel1, vowel2);

                    if (!validWord(lowerAnswer2)){
                        System.out.println("This word is invalid.");
                    } else if(!usedWord(lowerAnswer2,guessedWords2)){
                        System.out.println("Word has already been guessed.");
                    } else {
                        System.out.println("Your score is " + calculator.letterValue2(lowerAnswer2));
                    }
                    System.out.println("Guess again?");
                    break;
            }
            lowerAnswer2 = scanner2.nextLine().toLowerCase();
        }
        scanner2.close();
    }
}
