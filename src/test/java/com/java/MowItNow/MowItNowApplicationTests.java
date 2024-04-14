package com.java.MowItNow;


import com.java.MowItNow.core.MowerPosition;
import com.java.MowItNow.reader.LawnMowerInstructionReader;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MowItNowApplicationTests {



	private LawnMowerInstructionReader lawnMowerInstructionReader;


	@Test
	public void testSampleInput() throws Exception {
		// Read sample data from a String instead of file (optional)
		String sampleInput = "5 5\n1 2 N GAGAGAGAA\n3 3 E AADAADADDA";

		LawnMowerInstructionReader mockReader = new LawnMowerInstructionReader(new ByteArrayInputStream(sampleInput.getBytes()).toString());
		MowerPosition LawnMower = lawnMowerInstructionReader.readNextInstruction().getMower();

		//LawnMower = lawnMowerProcessor.process(LawnMower) ;// If using mock processor

		// Assert final positions (modify assertions as needed)

		assertEquals("1 3 N", LawnMower.getX());
		assertEquals("5 1 E", LawnMower.getY());  // Modify expected output format
	}
}
