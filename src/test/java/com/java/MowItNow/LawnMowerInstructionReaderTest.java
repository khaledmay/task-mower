package com.java.MowItNow;

import com.java.MowItNow.core.LawnMowerInstruction;
import com.java.MowItNow.reader.LawnMowerInstructionReader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.util.Arrays;

public class LawnMowerInstructionReaderTest {
    @Test
    public void testReadNextInstruction() throws Exception {
        // Mock the file content
        String fileContent = "5 5\n1 2 N\nGAGAGAGAA\n3 3 E\nAADAADADDA";
        BufferedReader bufferedReader = new BufferedReader(new StringReader(fileContent));
        // Create LawnMowerInstructionReader instance with the mock BufferedReader
        LawnMowerInstructionReader instructionReader = new LawnMowerInstructionReader(String.valueOf(bufferedReader));

        // Read and validate the first instruction
        LawnMowerInstruction instruction1 = instructionReader.readNextInstruction();
        assertEquals(5, instruction1.getWidth());
        assertEquals(5, instruction1.getHeight());
        assertEquals(1, instruction1.getMower().getX()); // Make sure mower position is properly initialized
        assertEquals(2, instruction1.getMower().getY());
        assertEquals('N', instruction1.getMower().getOrientation());
        assertEquals("GAGAGAGAA", instruction1.getInstructions());

        // Read and validate the second instruction
        LawnMowerInstruction instruction2 = instructionReader.readNextInstruction();
        assertEquals(3, instruction2.getWidth());
        assertEquals(3, instruction2.getHeight());
        assertEquals(3, instruction2.getMower().getX());
        assertEquals(3, instruction2.getMower().getY());
        assertEquals('E', instruction2.getMower().getOrientation());
        assertEquals("AADAADADDA", instruction2.getInstructions());

        // Verify that no more instructions can be read
        assertEquals(null, instructionReader.readNextInstruction());
    }
}
