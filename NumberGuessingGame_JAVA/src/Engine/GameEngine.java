package Engine;

import java.util.Scanner;
import Models.*;
public class GameEngine {
    protected Player player;
    protected Player[] players;
    protected String path;
    protected int [] range = new int[2];
    protected int score;



    void GetPlayers(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many players?: ");
        int numberOfPlayers = Integer.parseInt(scanner.nextLine());
        this.players = new Player[numberOfPlayers];

        for (int i = 0; i < numberOfPlayers; i++){
            System.out.println("Pass nickname for player number " + (i+1));
            String PlayerNickname = scanner.nextLine();
            Player player = new Player();
            player.nickName = PlayerNickname;
            this.players[i] = player.loadMultiplayer(path);
        }
    }

    void difficulty() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose the difficulty: \n1. Easy\n1. Medium\n3. Hard\n4. Custom");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                this.range[0] = 1;
                this.range[1] = 100;
                break;
            case "2":
                this.range[0] = 1;
                this.range[1] = 10000;
                break;
            case "3":
                this.range[0] = 1;
                this.range[1] = 1000000;
                break;
            case "4":
                System.out.println("pick range: \nMinimum: ");
                int min = Integer.parseInt(scanner.nextLine());
                System.out.println("pick range: \nMaximum: ");
                int max = Integer.parseInt(scanner.nextLine());
                this.range[0] = min;
                this.range[1] = max;

        }
    }
    Player LogIn(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your nickname: ");
        String nick = scanner.nextLine();
        this.player = new Player();
        player.nickName = nick;
        player.PlayerStats.put("name", nick);
        player.loadSingePlayer(path);
        return player;
    }

    void playerWelcome(){
        Scanner scanner = new Scanner(System.in);
        player.Greetings();
        System.out.println("Your best score is " + this.player.PersonalBest);
    }
    public static void optionChoice(String choice){
        switch (choice) {
            case "1":
                Singleplayer_Engine single = new Singleplayer_Engine();
                single.Game();
                break;
            case "2":
                Multiplayer_engine Multi = new Multiplayer_engine();
                break;
            case "3":
                vsCPU vsProgram= new vsCPU();
                break;
            case "4":
                System.exit(0);
                break;
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
        System.out.println("Continue?: \n1. Yes\n2. No");
        String choice = scanner.nextLine();
        return switch (choice) {
            case "1" -> true;
            default -> false;
        };
    }
    public boolean PlayerGuessNumber(){
        System.out.println("Guess the number (" + range[0] + " <---> " + range[1] + ")");
        return player.checkIfWin(player.isCorrect(player.numberToGuess));
    }
}
