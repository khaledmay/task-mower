package com.java.MowItNow.reader;

import com.java.MowItNow.core.LawnMowerInstruction;
import com.java.MowItNow.core.MowerPosition;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;

public class LawnMowerInstructionReader extends FlatFileItemReader<LawnMowerInstruction> {

    public LawnMowerInstructionReader(String resourcePath) {
        super();
        setResource(new ClassPathResource(resourcePath));
        setLineMapper(new DefaultLineMapper<>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[]{"width", "height"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                setTargetType(LawnMowerInstruction.class);
            }});
        }});
    }

    public LawnMowerInstructionReader() {

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
        // Skip the first line (lawn dimensions)
        setLinesToSkip(1);
    }

    @Override
    public LawnMowerInstruction read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        LawnMowerInstruction instruction = super.read();
        if (instruction != null) {
            // Read next line as mower data and set it in the instruction object (explained later)
            String mowerDataLine = super.read().toString();
            if (mowerDataLine != null) {
                instruction.setMower(parseMowerData(mowerDataLine));
            }
        }
        return instruction;
    }


    public LawnMowerInstruction parseMowerDataLine(String line) throws IllegalArgumentException {
        // Replace "explained later" with actual parsing logic based on the data format.

        // Example parsing logic (assuming format: X Y Orientation Instructions)
        String[] parts = line.split(" ");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Invalid data line format.");
        }

        int x;
        int y;
        char orientation;

        try {
            x = Integer.parseInt(parts[0]);
            y = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid X or Y coordinate format.", e);
        }

        orientation = parts[2].charAt(0);
        if (!"NESW".contains(String.valueOf(orientation))) {
            throw new IllegalArgumentException("Invalid orientation character.");
        }

        String instructions = parts[3];

        // Create a LawnMowerInstruction object with parsed data
        LawnMowerInstruction instruction = new LawnMowerInstruction(x, y, orientation, instructions);

        return instruction;
    }


    private MowerPosition parseMowerData(String mowerDataLine) {
        // ... parse mower data (x, y, orientation) and create a Mower object
        return null;
    }
}
