package com.java.MowItNow.core;

import lombok.Getter;

public class LawnMowerInstruction {

    @Getter
    private int width;
    @Getter
    private int height;
    private MowerPosition mower;
    private String instructions;




    public LawnMowerInstruction(int width, int height, MowerPosition mower, String instructions) {
        this.width = width;
        this.height = height;
        this.mower = mower;
        this.instructions = instructions;

    }

    public LawnMowerInstruction(int width, int height) {
    }

    public LawnMowerInstruction(int x, int y, char orientation, String instructions) {
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public MowerPosition getMower() {
        return mower;
    }

    public void setMower(MowerPosition mower) {
        this.mower = mower;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}