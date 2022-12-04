package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Integer command = 0;
        Scanner in = new Scanner(System.in);
        var gamePersonSmartBot = new Game(/*X*/new Person(), new Bot(1), 8, 8);
        var gamePersonSimpleBot = new Game(/*X*/new Person(), new Bot(0), 8, 8);
        var gamePersonPerson = new Game(/*X*/new Person(), new Person(), 8, 8);
        var gameSmartBotSimpleBot = new Game(/*X*/new Bot(0), new Bot(1), 8, 8);
        var gameSmartBotSmartBot = new Game(/*X*/new Bot(1), new Bot(1), 8, 8);
        var gameSimpleBotSimpleBot = new Game(/*X*/new Bot(0), new Bot(0), 8, 8);
        while (command != 8) {
            System.out.printf("Menu\n1. Play Person(X) with SmartBot(Y)\n2. Play Person(X) with SimpleBot(Y)\n3. Play Person(X) with Person(Y)\n" +
                    "4. Play SimpleBot(X) with SmartBot(Y) \n5. Play SimpleBot(X) with SimpleBot(Y) \n6. Play SmartBot(X) with SmartBot(Y)\n7. Output record\n" +
                    "8. Close App(input 8 or any string)\n ");
            if (in.hasNextInt()) {
                command = in.nextInt();
            } else {
                command = 8;
            }
            switch (command) {
                case (1):
                    gamePersonSmartBot.start();
                    break;
                case (2):
                    gamePersonSimpleBot.start();
                    break;
                case (3):
                    gamePersonPerson.start();
                    break;
                case (4):
                    gameSmartBotSimpleBot.start();
                    break;
                case (5):
                    gameSimpleBotSimpleBot.start();
                    break;
                case (6):
                    gameSmartBotSmartBot.start();
                    break;
                case (7):
                    System.out.printf((Game.maxMark) + "\n");
                    break;
            }
        }
    }
}