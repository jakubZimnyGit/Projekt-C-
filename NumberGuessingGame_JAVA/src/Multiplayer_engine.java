import java.util.*;
import java.util.HashMap;

public class Multiplayer_engine {       //very similar to class engine

    private final String path = "Multiplayer";
    private Player [] players;          // array of players object that are currently in the game
    private final int [] range;
    private  int numberToGuess;
    private HashMap<String, String> playersStats;

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
            this.players[i] = loadPlayer(player);
        }
    }
    Player loadPlayer(Player player){
        player.PlayerStats = File_Management.loadPlayerStats(player, path);
        if (player.PlayerStats == null){
            player.PlayerStats = new HashMap<>();
            return player;
        }
        player.Wins = Integer.parseInt(player.PlayerStats.get("Wins"));
        player.Lost = Integer.parseInt(player.PlayerStats.get("Lost"));
        player.GamesPlayed = Integer.parseInt(player.PlayerStats.get("GamesPlayed"));
        player.WinRate = Double.parseDouble(player.PlayerStats.get("WinRate"));
        return player;
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

    void updatePlayerStats(Player player){
        player.PlayerStats.put("name", player.nickName);
        player.PlayerStats.put("Wins", player.Wins.toString());
        player.PlayerStats.put("Lost", player.Lost.toString());
        player.PlayerStats.put("GamesPlayed", player.GamesPlayed.toString());
        player.PlayerStats.put("WinRate", player.WinRate.toString());
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
                    int playerGuess = guess;
                    win = checkWin(guess, player);
                    if(win){
                        break;
                    }
                }
            }
            gameOn = GameOn();  //asks player if he wants to play again
        }
    }

    boolean checkWin(int playerGuess, Player player){        // function is using array that we created in function before, to display all the players guesses at the same time
        boolean win = false;
            if (playerGuess > numberToGuess) {
                Message(player.nickName + " Try lower number");
            } else if (playerGuess < numberToGuess) {
                Message(player.nickName + " Try bigger number");
            }
            else {
                Message("Congratulations " + player.nickName);
                player.GamesPlayed++;
                player.Wins ++;
                player.WinRate = ((Double.valueOf(player.Wins)/Double.valueOf(player.GamesPlayed) * 100));
                updatePlayerStats(player);
                File_Management.SavePlayer(player, path);
                for (int i = 0; i < this.players.length; i++) {
                    if (!Objects.equals(players[i].nickName, player.nickName)){
                        players[i].Lost++;
                        players[i].GamesPlayed++;
                        players[i].WinRate = ((Double.valueOf(player.Wins)/Double.valueOf(player.GamesPlayed) * 100));
                        updatePlayerStats(players[i]);
                        File_Management.SavePlayer(players[i], path);
                    }
                }
                win = true;
            }

        return win;
    }


}
