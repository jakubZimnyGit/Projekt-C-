import Engine.GameEngine;
import Engine.Singleplayer_Engine;
import Models.Player;

import java.io.*;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        giereczka();
    }

    static void giereczka(){
        MainMenu();
    }
    static void MainMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Singleplayer (play for your best score).\n2. Mutliplayer (Play locally with your friends.)\n3. vs CPU.\n4. exit the game.");
        String choice = scanner.nextLine();
        GameEngine.optionChoice(choice);
    }
}
