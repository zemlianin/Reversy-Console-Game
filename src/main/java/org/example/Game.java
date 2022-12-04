package org.example;

// Класс Игра
public class Game {

    // Максимальный балл за все игры
    public static Integer maxMark = 0;

    Game(Player black, Player white, Integer weight, Integer height) {
        this.black = black;
        this.black.setColor("X");
        this.white = white;
        this.white.setColor("Y");
        field = new Field(weight, height, black.getColor(), white.getColor());
        lastMotion = 1;
    }

    // Метод Запуска игры
    // В случае исключения вывод слова Error
    void start() {
        try {
            while (white.tryMotion(field) || black.tryMotion(field)) {
                if (lastMotion == 0 && black.tryMotion(field)) {
                    System.out.print(field.output(black.getColor()));
                    while (!black.motion(field)) ;
                } else if (lastMotion == 1 && white.tryMotion(field)) {
                    System.out.print(field.output(white.getColor()));
                    while (!white.motion(field)) ;
                }
                if (lastMotion == 0) {
                    lastMotion = 1;
                } else {
                    lastMotion = 0;
                }

            }
            if (field.countChips(black.getColor()) > maxMark) {
                maxMark = field.countChips(black.getColor());
            }
            if (field.countChips(white.getColor()) > maxMark) {
                maxMark = field.countChips(white.getColor());
            }
            System.out.print(field.output(black.getColor()));
            System.out.print(field.searchWin(black.getColor(), white.getColor()));
        } catch (Exception e) {
            System.out.print("Error\n");
        }
    }

    // Первый игрой
    private Player black;
    // Второй игрок
    private Player white;
    // Игровое поле
    private Field field;
    // Номер игрокак делавшего последнее действие
    private Integer lastMotion;
}
