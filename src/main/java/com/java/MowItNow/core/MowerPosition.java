package com.java.MowItNow.core;

public class MowerPosition {


        private int x;
        private int y;
        private char orientation;

    private final int[][] directionVectors = {
            {-1, 0}, // West
            {0, 1}, // North
            {1, 0}, // East
            {0, -1} // South
    };

        public MowerPosition(int x, int y, char orientation) {
            this.x = x;
            this.y = y;
            this.orientation = orientation;
        }

    @Override
    public String toString() {
        return x + " " + y + " " + orientation;
    }
    public void moveForward() {
        switch (orientation) {
            case 'N':
                y++;
                break;
            case 'E':
                x++;
                break;
            case 'S':
                y--;
                break;
            case 'W':
                x--;
                break;
        }
    }

    public void turnLeft() {
        switch (orientation) {
            case 'N':
                orientation = 'W';
                break;
            case 'W':
                orientation = 'S';
                break;
            case 'S':
                orientation = 'E';
                break;
            default: // Default to East if orientation is invalid
                orientation = 'N';
        }
    }

    public void turnRight() {
        switch (orientation) {
            case 'N':
                orientation = 'E';
                break;
            case 'E':
                orientation = 'S';
                break;
            case 'S':
                orientation = 'W';
                break;
            default: // Default to West if orientation is invalid
                orientation = 'N';
        }
    }


    private int getOrientationIndex() {
        switch (orientation) {
            case 'N':
                return 1;
            case 'E':
                return 2;
            case 'S':
                return 3;
            default:
                return 0; // Default to West if orientation is invalid
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public char getOrientation() {
        return orientation;
    }

    public void setOrientation(char orientation) {
        this.orientation = orientation;
    }

    public int getDirectionX() {
        return directionVectors[getOrientationIndex()][0];
    }

    public int getDirectionY() {
        return directionVectors[getOrientationIndex()][1];
    }

}
