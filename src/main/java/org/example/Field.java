package org.example;

public class Field {

    Integer getHeight() {
        return height;
    }


    Integer getWeight() {
        return weight;
    }


    private Integer height;
    private Integer weight;
    private String black;
    private String white;

    String getBlack() {
        return black;
    }

    String getWhite() {
        return white;
    }

    private String[][] area;
    private Double[][] marks;

    Field(Field other) {
        this(other.getHeight(), other.getWeight(), other.getBlack(), other.getBlack());
        for (int i = 1; i < height + 1; i++) {
            for (int j = 1; j < weight + 1; j++) {
                area[i][j] = other.area[i][j];
            }
        }
    }

    Field(Integer height, Integer weight, String black, String white) {
        this.height = height;
        this.weight = weight;
        this.black = black;
        this.white = white;
        area = new String[height + 1][weight + 1];
        marks = new Double[height + 1][weight + 1];
        for (int i = 0; i < height + 1; i++) {
            for (int j = 0; j < weight + 1; j++) {
                if (i == 0) {
                    area[i][j] = Integer.toString(j);
                } else if (j == 0) {
                    area[i][j] = Integer.toString(i);
                } else {
                    area[i][j] = Integer.toString(0);
                }
            }
        }
        for (int i = 1; i < height + 1; i++) {
            for (int j = 1; j < weight + 1; j++) {
                if ((i == 1 && j == 1) || (i == 1 && j == weight + 1) || (i == height + 1 && j == weight + 1) || (i == height + 1 && j == 1)) {
                    marks[i][j] = 0.8;
                } else if (i == 1 || j == weight + 1 || i == height + 1 || j == 1) {
                    marks[i][j] = 0.4;
                } else {
                    marks[i][j] = 0.0;
                }
            }
        }
        area[height / 2][weight / 2] = black;
        area[height / 2 + 1][weight / 2 + 1] = black;
        area[height / 2][weight / 2 + 1] = white;
        area[height / 2 + 1][weight / 2] = white;
    }

    String output(String color) {
        String output = "";
        for (int i = 0; i < area.length; i++) {
            for (int j = 0; j < area.length; j++) {
                var tryField = new Field(this);
                if (!area[i][j].equals("0")) {
                    output += area[i][j] + " ";
                } else {
                    if (tryField.step(i, j, color)) {
                        output += "□ ";
                    } else {
                        output += "■ ";
                    }
                }
            }
            output += "\n";
        }
        return output;
    }

    Boolean step(Integer x, Integer y, String color) {
        if(x<=0||y<=0||x>height||y>weight){
            return false;
        }
        if (!area[x][y].equals("0")) {
            return false;
        }
        Boolean isRightStep = false;
        if (x + 1 < height + 1 && area[x + 1][y] != color && !area[x + 1][y].equals("0")) {
            isRightStep = move(x, y, color, 1, 0) || isRightStep;
        }
        if (x + 1 < height + 1 && y - 1 > 0 && area[x + 1][y - 1] != color && !area[x + 1][y - 1].equals("0")) {
            isRightStep = move(x, y, color, 1, -1) || isRightStep;
        }
        if (y - 1 > 0 && area[x][y - 1] != color && !area[x][y - 1].equals("0")) {
            isRightStep = move(x, y, color, 0, -1) || isRightStep;
        }
        if (x - 1 > 0 && y - 1 > 0 && area[x - 1][y - 1] != color && !area[x - 1][y - 1].equals("0")) {
            isRightStep = move(x, y, color, -1, -1) || isRightStep;
        }
        if (x - 1 > 0 && area[x - 1][y] != color && !area[x - 1][y].equals("0")) {
            isRightStep = move(x, y, color, -1, 0) || isRightStep;
        }
        if (x - 1 > 0 && y + 1 < weight + 1 && area[x - 1][y + 1] != color && !area[x - 1][y + 1].equals("0")) {
            isRightStep = move(x, y, color, -1, 1) || isRightStep;
        }
        if (y + 1 < weight + 1 && area[x][y + 1] != color && !area[x][y + 1].equals("0")) {
            isRightStep = move(x, y, color, 0, 1) || isRightStep;
        }
        if (x + 1 < height + 1 && y + 1 < weight + 1 && area[x + 1][y + 1] != color && !area[x + 1][y + 1].equals("0")) {
            isRightStep = move(x, y, color, 1, 1) || isRightStep;
        }
        return isRightStep;
    }

    Boolean move(Integer x, Integer y, String color, Integer dx, Integer dy) {
        Integer gap = 1;
        while (x + dx * gap < height + 1 && y + dy * gap < weight + 1 &&
                x + dx * gap > 0 && y + dy * gap > 0 && (!area[x + gap * dx][y + gap * dy].equals("0"))) {
            if (area[x + gap * dx][y + dy * gap] == color) {
                changeColors(x, y, x + gap * dx, y + gap * dy, color);
                return true;
            }
            gap++;
        }
        return false;
    }

    void changeColors(Integer y1, Integer x1, Integer y2, Integer x2, String color) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        if (dy == 0) {
            for (int i = x1; i != x2; i += dx / Math.abs(dx)) {
                if (area[y1][i] != color && area[y1][i] != "0") {
                    area[y1][i] = color;
                }
            }
        } else if (dx == 0) {
            for (int i = y1; i != y2; i += dy / Math.abs(dy)) {
                if (area[i][x1] != color && area[i][x1] != "0") {
                    area[i][x1] = color;
                }
            }
        } else {
            for (int i = 0; i < Math.abs(dy); i += 1) {
                int cx = x1 + i * dx / Math.abs(dx);
                int cy = y1 + i * dy / Math.abs(dy);
                if (area[cy][cx] != color && area[cy][cx] != "0") {
                    area[cy][cx] = color;
                }
            }
        }

    }

    String searchWin(String color1, String color2) {
        if (countChips(color1) > countChips(color2)) {
            return color1 + " - Winner, " + color1 + " - " + countChips(color1) + " : "
                    + color2 + " - " + (countChips(color2)) + "\n";
        } else if (countChips(color1) < countChips(color2)) {
            return color2 + " - Winner, " + color1 + " - " + countChips(color1) + " : "
                    + color2 + " - " + countChips(color2) + "\n";
        } else {
            return color1 + " and " + color2 + " - Winners\n";
        }
    }

    int countChips(String color) {
        int sum = 0;
        for (int i = 1; i < height + 1; i++) {
            for (int j = 1; j < weight + 1; j++) {
                if (color == area[i][j]) {
                    sum++;
                }
            }
        }
        return sum;
    }

    double countMarks(String color) {
        double sum = 0;
        for (int i = 1; i < height + 1; i++) {
            for (int j = 1; j < weight + 1; j++) {
                if (color == area[i][j]) {
                    sum += marks[i][j];
                }
            }
        }
        return sum;
    }

    String[][] getArea() {
        return area;
    }
}
