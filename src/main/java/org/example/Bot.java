package org.example;
// Расширение класса игрок - бот
public class Bot extends Player {
    private Integer skill;

    Bot(Integer skill) {
        this.skill = skill;
    }

    Boolean motion(Field field) {
        Integer[] coordinates;
        if (skill == 0) {
            coordinates = selectJustStep(field, color);
        } else {
            coordinates = selectSmartStep(field);
        }
        System.out.print("Bot do action on (" + coordinates[0] + ", " + coordinates[1] + ")\n");
        return field.step(coordinates[0], coordinates[1], color);
    }

    Integer[] selectJustStep(Field field, String color) {

        Double maxSum = 0.0;
        Integer tryX = 0, tryY = 0;
        Double difference;

        for (int i = 1; i < field.getHeight() + 1; i++) {
            for (int j = 1; j < field.getWeight() + 1; j++) {
                Field TryField = new Field(field);
                TryField.step(i, j, color);
                if (TryField.countChips(color) > field.countChips(color) && field.getArea()[i][j].equals("0")) {
                    difference = TryField.countMarks(color) - field.countMarks(color);
                    if (i == 1 || j == field.getWeight() || i == field.getHeight() || j == 1) {
                        difference += 2;
                    } else {
                        difference++;
                    }
                    if (difference > maxSum) {
                        maxSum = difference;
                        tryX = i;
                        tryY = j;
                    }
                }
            }
        }
        return new Integer[]{tryX, tryY};
    }

    Integer[] selectSmartStep(Field field) {
        Double maxSum = -100.0;
        Integer tryX = 0, tryY = 0;
        Integer[] points;
        Double difference;
        String otherColor;
        if (field.getBlack() == color) {
            otherColor = field.getWhite();
        } else {
            otherColor = field.getBlack();
        }
        for (int i = 1; i < field.getHeight() + 1; i++) {
            for (int j = 1; j < field.getWeight() + 1; j++) {
                Field fieldFirstStep = new Field(field);
                fieldFirstStep.step(i, j, color);
                if (fieldFirstStep.countChips(color) > field.countChips(color) && field.getArea()[i][j].equals("0")) {
                    difference = fieldFirstStep.countMarks(color) - field.countMarks(color);
                    if (i == 1 || j == field.getWeight() || i == field.getHeight() || j == 1) {
                        difference += 2;
                    } else {
                        difference++;
                    }
                    points = selectJustStep(fieldFirstStep, otherColor);
                    Field fieldSecondStep = new Field(fieldFirstStep);
                    fieldSecondStep.step(points[0], points[1], otherColor);
                    difference = difference - (fieldSecondStep.countMarks(otherColor) - fieldFirstStep.countMarks(otherColor)) / 4;
                    if ((points[0] == 1 && points[1] == 1) || (points[0] == 1 && points[1] == field.getWeight() + 1) ||
                            (points[0] == field.getHeight() && points[1] == field.getWeight()) || (points[0] == field.getHeight() && points[1] == 1)) {
                        difference -= 3;
                    }
                    if (points[0] == 1 || points[1] == field.getWeight() || points[0] == field.getHeight() || points[1] == 1) {
                        difference -= 1;
                    } else {
                        difference -= 0.5;
                    }
                    if (difference > maxSum) {
                        maxSum = difference;
                        tryX = i;
                        tryY = j;
                    }
                }
            }
        }
        return new Integer[]{tryX, tryY};
    }
}
