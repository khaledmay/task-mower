package com.java.MowItNow.configuration;

import com.java.MowItNow.core.LawnMowerInstruction;
import com.java.MowItNow.core.MowerPosition;
import com.java.MowItNow.processor.LawnMowerProcessor;
import com.java.MowItNow.reader.LawnMowerInstructionReader;
import com.java.MowItNow.writer.LawnMowerWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class LawerMowerJobConfig {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job lawnMowerJob() {
        return jobBuilderFactory.get("lawnMowerJob")
                .start(mowLawnStep())
                .build();
    }

    @Bean
    public Step mowLawnStep() {
        return stepBuilderFactory.get("mowLawnStep")
                .<LawnMowerInstruction, MowerPosition>chunk(1)
                .reader(new LawnMowerInstructionReader())
                .processor((ItemProcessor<? super LawnMowerInstruction, ? extends MowerPosition>) new LawnMowerProcessor())
                .writer(new LawnMowerWriter())
                .build();
    }

    @Bean
    public LawnMowerInstructionReader lawnMowerInstructionReader() {
        return new LawnMowerInstructionReader("input.txt"); // Replace with actual file path
    }

    @Bean
    public LawnMowerProcessor lawnMowerProcessor() {
        return new LawnMowerProcessor();
    }

    @Bean
    public LawnMowerWriter lawnMowerWriter() {
        return new LawnMowerWriter();
    }
}
