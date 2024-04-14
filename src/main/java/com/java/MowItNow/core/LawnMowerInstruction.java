package com.java.MowItNow.core;


import lombok.Getter;

@Getter
public class LawnMowerInstruction {

    private int width;
    private int height;
    private MowerPosition mower;
    private String instructions;



    public LawnMowerInstruction(int width, int height, int x, int y, char orientation, String instructions) {
        this.width = width;
        this.height = height;
        this.mower = new MowerPosition(x, y, orientation);
        this.instructions = instructions;
    }


    public LawnMowerInstruction(int width, int height, MowerPosition initialPosition, String gagagagaa) {
    }

    public LawnMowerInstruction(int width, int height) {
    }


    public boolean isValidPosition(int x, int y) {
        // Check if the position is within the lawn boundaries (0 to width-1, 0 to height-1)
        return (x >= 0 && x < width) && (y >= 0 && y < height);
    }

}

