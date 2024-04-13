package com.java.MowItNow.core;

public class MowerPosition {


        private int x;
        private int y;
        private char orientation;

        public MowerPosition(int x, int y, char orientation) {
            this.x = x;
            this.y = y;
            this.orientation = orientation;
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
            case 'E':
                orientation = 'N';
                break;
            case 'S':
                orientation = 'E';
                break;
            case 'W':
                orientation = 'S';
                break;
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
            case 'W':
                orientation = 'N';
                break;
        }
    }

    public boolean isWithinLawn(LawnMowerInstruction lawn) {
        return 0 <= x && x < lawn.getWidth() && 0 <= y && y < lawn.getHeight();
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

        @Override
        public String toString() {
            return x + " " + y + " " + orientation;
        }

}
