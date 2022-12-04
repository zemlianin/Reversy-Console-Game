package org.example;
import java.util.Scanner;
// Расширение класса игрок - человек
public class Person extends Player {

    Boolean motion(Field field) {
        Scanner in = new Scanner(System.in);
        outputSteps(field);
        System.out.printf("%s - Input cordinates\n", color);
        Integer x = 0, y = 0;
        if (in.hasNextInt()) {
            x = in.nextInt();
        }
        if (in.hasNextInt()) {
            y = in.nextInt();
        }
        return field.step(x, y, color);
    }

    void outputSteps(Field field) {
        for (int i = 1; i < field.getHeight() + 1; i++) {
            for (int j = 1; j < field.getWeight() + 1; j++) {
                var tryField = new Field(field);
                if (tryField.step(i, j, color)) {
                    System.out.print(i + " - " + j + "\n");
                }
            }
        }
    }
}
