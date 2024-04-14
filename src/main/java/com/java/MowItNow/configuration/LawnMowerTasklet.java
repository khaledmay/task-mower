package com.java.MowItNow.configuration;

import com.java.MowItNow.core.LawnMowerInstruction;
import com.java.MowItNow.core.MowerPosition;
import com.java.MowItNow.processor.LawnMowerProcessor;
import com.java.MowItNow.reader.LawnMowerInstructionReader;
import com.java.MowItNow.writer.LawnMowerWriter;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class LawnMowerTasklet implements Tasklet {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private LawnMowerInstructionReader lawnMowerInstructionReader;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private LawnMowerProcessor lawnMowerProcessor;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private LawnMowerWriter lawnMowerWriter;



    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        // Read lawnmower data from the reader
        List<MowerPosition> lawnMowers = new ArrayList<>();
        LawnMowerInstruction lawnMowerInstruction;

        while ((lawnMowerInstruction = lawnMowerInstructionReader.readNextInstruction()) != null) {
            lawnMowers.add(lawnMowerInstruction.getMower());
        }

        for (MowerPosition lawnMower : lawnMowers) {
            lawnMowerProcessor.process(lawnMowerInstruction); // Fix this line if necessary
        }

        // Write final positions to the writer
        lawnMowerWriter.writeFinalPositions(lawnMowers);

        // Indicate successful completion of the step
        return RepeatStatus.FINISHED;
    }
}
