package Models;
import FilesManagement.File_Management;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;


public class Player {       //Models.Player class

    public String nickName;     // Models.Player's properties
    public Integer PersonalBest;
    public Integer Wins;
    public Integer Lost;
    public Integer GamesPlayed;
    public int roundsWon;
    public HashMap<String, String> PlayerStats;
    public int numberToGuess;
    public int score;
    public Integer winStreak;

    public Player(){     // function that allows us to create object of type Models.Player
        this.nickName = "CPU";
        this.PersonalBest = 0;
        this.Wins = 0;
        this.Lost = 0;
        this.GamesPlayed = 0;
        this.score = 1;
        PlayerStats  = new HashMap<>();
        this.winStreak = 0;
        this.roundsWon = 0;
    }
    public void Greetings()
    {
        System.out.println("Hello!\nI am " + nickName + ".");
    }

    public void RandomNumber(int [] range){
        Random rdm = new Random();
        this.numberToGuess = rdm.nextInt(range[0],range[1]);
    }

    public int guessTheNumber() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    public int bigOrLow(int numberToGuess, int guess){
        if (guess > numberToGuess){
            System.out.println("number is too big");
            score++;
            return 1;
        }
        else if(guess < numberToGuess){
            System.out.println("Number is too low");
            score++;
            return 2;
        }
        return 0;
    }
    public void getNumberForBot(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number for the CPU to guess: ");
        this.numberToGuess = scanner.nextInt();
    }

    public boolean checkIfWin(boolean win){
        if (win){
            System.out.println(this.nickName +" found the right number!!!");
            return true;
        }
        return false;
    }

    public boolean isCorrect (int numberToGuess){
        int guess = guessTheNumber();
        return bigOrLow(numberToGuess, guess) == 0;
    }
    public void loadSingePlayer(String path){
        this.PlayerStats = File_Management.loadPlayerStats(this, path);
        if (this.PlayerStats == null){
            this.PlayerStats = new HashMap<>();
            this.PlayerStats.put("name", this.nickName);
            this.PlayerStats.put("score", this.PersonalBest.toString());
        }
        this.PersonalBest = Integer.parseInt(this.PlayerStats.get("score"));
    }
    public void loadMultiplayer(String path){
        this.PlayerStats = File_Management.loadPlayerStats(this, path);
        if (this.PlayerStats == null){
            this.PlayerStats = new HashMap<>();
            this.PlayerStats.put("name", this.nickName);
            return;
        }
        this.Wins = Integer.parseInt(this.PlayerStats.get("Wins"));
        this.Lost = Integer.parseInt(this.PlayerStats.get("Lost"));
        this.GamesPlayed = Integer.parseInt(this.PlayerStats.get("GamesPlayed"));
        this.winStreak = Integer.parseInt(this.PlayerStats.get("winStreak"));
    }



}