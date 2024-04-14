package com.java.MowItNow;

import com.java.MowItNow.core.LawnMowerInstruction;
import com.java.MowItNow.core.MowerPosition;
import com.java.MowItNow.processor.LawnMowerProcessor;
import com.java.MowItNow.reader.LawnMowerInstructionReader;
import com.java.MowItNow.writer.LawnMowerWriter;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


@EnableBatchProcessing
@SpringBootApplication
public class MowItNowApplication {

	public static void main(String[] args) throws IOException {
		// Load input file as a resource stream
		BufferedReader reader = new BufferedReader(new InputStreamReader(MowItNowApplication.class.getClassLoader().getResourceAsStream("input.txt")));
		String line;

		// Initialize lists to store instructions and final positions
		List<LawnMowerInstruction> instructions = new ArrayList<>();
		List<MowerPosition> finalPositions = new ArrayList<>();

		// Create LawnMowerProcessor and LawnMowerWriter instances
		LawnMowerProcessor processor = new LawnMowerProcessor();
		LawnMowerWriter writer = new LawnMowerWriter(); // Assuming LawnMowerWriter exists

		// Read each line, parse data, process instructions, and write final positions
		while ((line = reader.readLine()) != null) {
			LawnMowerInstruction instruction = new LawnMowerInstructionReader("input.txt").parseMowerDataLine(line);
			instructions.add(instruction);

            MowerPosition finalPosition = null;
            try {
                finalPosition = processor.process(instruction);
            } catch (IllegalArgumentException e) {
				System.err.println("Error processing instruction: " + e.getMessage());
			}
		reader.close();

		writer.writeFinalPositions(finalPositions );
	}
}
}
