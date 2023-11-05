import Engine.GameEngine;
import Engine.TournamentV2;

import java.io.*;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException {
        TheGame();
    }

    static void TheGame() throws IOException {                  // TO DO: 1. input validation
        MainMenu();                                             //        2. title's atributes
    }                                                           //        3. clear code
    static void MainMenu() throws IOException {
            GameEngine engine = new GameEngine();
            int choice = engine.IntegerInputValidation("1. Singleplayer (play for your best score).\n2. Mutliplayer (Play locally with your friends.)\n3. vs CPU.\n4. Tournament.\n5. Big Tournament(2,4,8,16,32 players).\n" + 
                    "6.(or more) exit the game.");
            GameEngine.optionChoice(choice);
    }
}
