import javax.management.StringValueExp;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.HashMap;

public class Engine {
    public int score;      // game will have its own score
    private Player player;  // there will be one player at the time, so he is also the game's property
    private final int[] range;    // range of numbers, it's an array so the first number range[0] can be min and the second number range[1] is max
    HashMap<String, String> playerStats = new HashMap<String, String >();


    public Engine(){
        this.score = 0;             // score is counting player's guesses, so it has to be 0 at the start of the game
        this.range = new int[2];    // range should always have 2 elements min and max that is why I set size of array to be 2
    }
    static void Message(String message){
        System.out.println(message);
    }  // not necessary but a little bit faster than writing "System.out.println" does exactly the same thing


    Boolean GameOn() {                              // Function returns true if player want to continue the game so the loop goes on
        Scanner scanner = new Scanner(System.in);
        Message("Continue?: \n1. Yes\n2. No");
        String choice = scanner.nextLine();         // or false if he chose 2. then the loop is over   (Loop in the "game" function)
        return switch (choice) {
            case "1" -> true;
            case "2" -> false;
            default -> false;
        };
    }
    Player LogIn(){
        Scanner scanner = new Scanner(System.in);     //Function that gets player's nickname and uses different function to check if he's already on list
        Message("Enter your nickname: ");          // If he is, then the function "File_Management.GetData" will return the player object we are looking for
        String nick = scanner.nextLine();         // so we will have access to his best score
        this.player = new Player(nick);
        playerStats = File_Management.loadPlayerStats(player);
        loadPlayer(player, playerStats);
        playerStats = loadPlayer(player, playerStats);
        return player;
    }

    void mainMenu(){
        Scanner scanner = new Scanner(System.in);           // function lets player pick one of three options, single, multiplayer or exit the game
        this.player = LogIn();
        player.Greetings();
        Message("Your best score is " + this.player.PersonalBest);
        Message("1. Singleplayer (play for your best score).\n2. Mutliplayer (Play locally with your friends.)\n3. exit the game.");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                game();
                break;
            case "2":
                Multiplayer_engine MultiEngine = new Multiplayer_engine();
                MultiEngine.MultiplayerGame();
                break;
            case "3":
                System.exit(0);
                break;
        }
    }

    void difficulty(){
        Scanner scanner = new Scanner(System.in);
        Message("Choose the difficulty: \n1. Standard\n2. Custom");
        String choice = scanner.nextLine();         //Standard difficulty is range from 1 to 100
        switch (choice){                        // Custom difficulty level lets player choose their range of numbers
            case "1":
                this.range[0] = 1;
                this.range[1] = 100;
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

    void updatePlayerStats(HashMap<String, String> playerStats){
        playerStats.remove("score");
        playerStats.put("score", String.valueOf(score));
    }

    HashMap<String, String> loadPlayer(Player player, HashMap<String, String> playerStats){
        if (playerStats == null){
            playerStats = new HashMap<>();
            playerStats.put("name", player.nickName);
            playerStats.put("score", player.PersonalBest.toString());
            return playerStats;
        }
        player.PersonalBest = Integer.parseInt(playerStats.get("score"));
        return playerStats;
    }
    void game() {

        Random rdm = new Random();      //Random type object allows us to pick random number for the player to guess
        Boolean gameOn = true;     // variable gameOn decides if the loop goes on
        while (gameOn) {
            difficulty();
            Scanner scanner = new Scanner(System.in);
            int numberToGuess = rdm.nextInt(this.range[0], this.range[1]);
            int guess;
            this.score = 0;         // in case player wants to play more than one time we have to reset score.
            Message("Guess the number! (" + this.range[0] + " <---> " + this.range[1] + ")");
            do {
                guess = scanner.nextInt();
                if (guess > numberToGuess) {
                    Message("Try lower number");
                    score++;
                } else if (guess < numberToGuess) {
                    Message("Try bigger number");
                    this.score++;
                }
            } while (guess != numberToGuess);
            this.score++;       // guessing correctly means we skip all conditions which also means that score isn't incremented, we have to do this after loop
                                // to prevent player from having score - 1 type of situation, for example score = 0 if player guess first time
            Message("Congratulations!");
            if (score < player.PersonalBest) {
                Message("NEW BEST!!! ---> " + score);
                File_Management.SavePlayer(playerStats, player);
            } else if (player.PersonalBest == 0) {
                Message("NEW BEST!!! ---> " + score);
                player.PersonalBest = score;
                updatePlayerStats(playerStats);
                File_Management.SavePlayer(playerStats, player);
            } else {
                Message("score ---> " + score);
            }
            gameOn = GameOn();
        }
    }
}

