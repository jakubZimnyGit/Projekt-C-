import Engine.GameEngine;

import java.io.*;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException {
        TheGame();
    }

    static void TheGame() throws IOException {
        MainMenu();
    }
    static void MainMenu() throws IOException {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Singleplayer (play for your best score).\n2. Mutliplayer (Play locally with your friends.)\n3. vs CPU.\n4. Tournament.\n5. exit the game.");
            String choice = scanner.nextLine();
            GameEngine.optionChoice(choice);
            scanner.close();
    }
}
