package Models;

import java.util.Random;
public class Bot extends Player {

    public Bot() {
        super();
    }
    public int[] range;

    @Override
    public int guessTheNumber() {
        Random rdm = new Random();
        int number = rdm.nextInt(range[0], range[1]);
        System.out.println(number);
        return number;
    }
    private void tooBig(int guess){
        this.range[1] = guess;
    }
    private void tooLow(int guess){
        this.range[0] = guess;
    }

    @Override
    public int bigOrLow(int numberToGuess, int guess){
        if (guess > numberToGuess){
            System.out.println("number is too big");
            tooBig(guess);
            score++;
            return 1;
        }
        else if(guess < numberToGuess){
            System.out.println("Number is too low");
            tooLow(guess);
            score++;
            return 2;
        }
        return 0;
    }
    public void setRange(int [] rangeInGame){
        this.range = new int[2];
        this.range[0] = rangeInGame[0];
        this.range[1] = rangeInGame[1];
    }
}
