package com.java.MowItNow.reader;

import com.java.MowItNow.core.LawnMowerInstruction;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LawnMowerInstructionReader {

    private BufferedReader reader;

    public LawnMowerInstructionReader(String file) {
        try {
            reader = new BufferedReader(new FileReader("input.txt"));
        } catch (IOException e) {
            System.err.println("Error opening input file: " + e.getMessage());
            System.exit(1);
        }
    }

    public LawnMowerInstructionReader() {

    }

    public LawnMowerInstructionReader(FileReader fileReader) {
    }

    //Reads the next line from the input file and parses it into a LawnMowerInstruction object.
    public LawnMowerInstruction readNextInstruction() throws IllegalArgumentException, IOException {
        String line = reader.readLine();
        if (line == null) {
            return null; // End of file
        }
        // Parse the line into a LawnMowerInstruction object
        return parseMowerDataLine(line);
    }
    public LawnMowerInstruction parseMowerDataLine(String line) throws IllegalArgumentException {
        // Example parsing logic (assuming format: X Y Orientation Instructions)
        String[] parts = line.split(" ");
        if (parts.length != 5) {
            throw new IllegalArgumentException("Invalid data line format.");
        }

        int x;
        int y;
        int width ;
        int height;
        char orientation;

        try {
            width = Integer.parseInt(parts[0]);
            height = Integer.parseInt(parts[1]);
            x = Integer.parseInt(parts[2]);
            y = Integer.parseInt(parts[3]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid width, height, X, or Y coordinate format.", e);
        }

        orientation = parts[4].charAt(0);
        if (!"NESW".contains(String.valueOf(orientation))) {
            throw new IllegalArgumentException("Invalid orientation character.");
        }

        String instructions = parts[4];


        return new LawnMowerInstruction(width,height, x, y, orientation, instructions);
    }

    public void close() throws IOException {
        if (reader != null) {
            reader.close();
        }
    }
}
