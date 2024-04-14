package com.java.MowItNow;

import com.java.MowItNow.core.LawnMowerInstruction;
import com.java.MowItNow.core.MowerPosition;
import com.java.MowItNow.processor.LawnMowerProcessor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LawnMowerProcessorTest {

    @Test
    public void testProcess() {
        // Create a lawn with dimensions 5x5
        int width = 5;
        int height = 5;

        // Create a mower starting at position (1, 2) facing North
        MowerPosition initialPosition = new MowerPosition(1, 2, 'N');
        LawnMowerInstruction instruction = new LawnMowerInstruction(width, height, initialPosition, "GAGAGAGAA");

        // Create a processor instance
        LawnMowerProcessor processor = new LawnMowerProcessor();

        // Process the instruction
        MowerPosition finalPosition = processor.process(instruction);

        // Validate the final position
        assertEquals(1, finalPosition.getX());
        assertEquals(3, finalPosition.getY());
        assertEquals('N', finalPosition.getOrientation());
    }
}
