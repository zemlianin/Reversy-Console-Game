package org.example;

public abstract class Player {
    protected String color;

    Boolean motion(Field field) {
        return true;
    }

    void setColor(String color) {
        this.color = color;
    }

    String getColor() {
        return color;
    }

    Boolean tryMotion(Field field) {
        Boolean flag = false;
        for (int i = 1; i < field.getHeight() + 1; i++) {
            for (int j = 1; j < field.getWeight() + 1; j++) {
                var field2 = new Field(field);
                flag = field2.step(i, j, color) || flag;
            }
        }
        return flag;
    }

}
