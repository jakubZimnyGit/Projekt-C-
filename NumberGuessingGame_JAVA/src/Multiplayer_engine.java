import java.util.Random;
import java.util.Scanner;

public class Multiplayer_engine {       //very similar to class engine

    private Player [] players;          // array of players object that are currently in the game
    private final int [] range;
    private  int numberToGuess;

    public Multiplayer_engine() {
        range = new int [2];
    }

    static void Message(String message){
        System.out.println(message);
    }
    void GetPlayers(){              // Function creates array of players
        Scanner scanner = new Scanner(System.in);
        Message("How many players?: "); //asks how many players are going to play
        int numberOfPlayers = Integer.parseInt(scanner.nextLine());
        this.players = new Player[numberOfPlayers];    //assign value to property of class, array "Players"

        for (int i = 0; i < numberOfPlayers; i++){      // creating players and adding them to array
            Message("Pass nickname for player number " + (i+1));
            String PlayerNickname = scanner.nextLine();
            Player player = new Player(PlayerNickname);
            this.players[i] = player;
        }
    }

    void difficulty(){
        Scanner scanner = new Scanner(System.in);
        Message("Choose the difficulty: \n1. Standard\n2. Advanced");
        String choice = scanner.nextLine();
        switch (choice){
            case "1":
                this.range[0] = 1;
                this.range[1] = 1000;
                break;
            case "2":
                Message("pick range: \nMinimum: " );
                int min = Integer.parseInt(scanner.nextLine());
                Message("pick range: \nMaximum: " );
                int max = Integer.parseInt(scanner.nextLine());
                this.range[0] = min;
                this.range[1] = max;

        }
    }
    Boolean GameOn() {
        Scanner scanner = new Scanner(System.in);
        Message("Continue?: \n1. Yes\n2. No");
        String choice = scanner.nextLine();
        return switch (choice) {
            case "1" -> true;
            default -> false;
        };
    }
    void MultiplayerGame(){
        GetPlayers();
        boolean gameOn = true;
        while (gameOn) {
            int[] playersGuesses = new int[players.length];         // for game to be fair, function is creating array of guesses
            boolean win = false;                        // so guesses can be displayed at the same time, after everyone took a guess,
            difficulty();                               // not giving any of players extra advantage
            Random rdm = new Random();
            numberToGuess = rdm.nextInt(this.range[0], this.range[1]);
            while (!win) {
                for (int i = 0; i < this.players.length; i++) {     // Loops through every player so everyone can take a guess
                    Player player = this.players[i];
                    Scanner scanner = new Scanner(System.in);
                    int guess;
                    Message(player.nickName + " guess the number! (" + this.range[0] + " <---> " + this.range[1] + ")");
                    guess = scanner.nextInt();
                    playersGuesses[i] = guess;
                }
                win = checkWin(playersGuesses);     //if someone guess correctly, variable "win" will be set on true and loop will not continue
            }
            gameOn = GameOn();  //asks player if he wants to play again
        }
    }

    boolean checkWin(int [] playersGuesses){        // function is using array that we created in function before, to display all the players guesses at the same time
        boolean win = false;
        for (int i = 0; i < playersGuesses.length; i++){
            if (playersGuesses[i] > numberToGuess) {
                Message(players[i].nickName + " Try lower number");
            } else if (playersGuesses[i] < numberToGuess) {
                Message(players[i].nickName + " Try bigger number");
            }
            else {
                Message("Congratulations " + players[i].nickName);
                win = true;
            }
        }
        return win;
    }


}
