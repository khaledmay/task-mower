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

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;

public class LawerMowerJobConfig {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private LawnMowerInstructionReader lawnMowerInstructionReader;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private LawnMowerProcessor lawnMowerProcessor;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private LawnMowerWriter lawnMowerWriter;


    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private CustomTaskExecutor customTaskExecutor; // Inject CustomTaskExecutor


    @Bean
    public LawnMowerInstructionReader lawnMowerInstructionReader() {
        return new LawnMowerInstructionReader(inputFilePath()); // Assuming inputFilePath() is correctly defined
    }

    @Bean
    public LawnMowerProcessor lawnMowerProcessor() {
        return new LawnMowerProcessor();
    }

    @Bean
    public LawnMowerWriter lawnMowerWriter() {
        return new LawnMowerWriter();
    }


    @Bean
    public LawnMowerTasklet lawnMowerTasklet() {
        return new LawnMowerTasklet();  // Create an instance of LawnMowerTasklet
    }


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
                .reader((ItemReader<? extends LawnMowerInstruction>) lawnMowerInstructionReader)
                .processor(lawnMowerProcessor)
                .writer((ItemWriter<? super MowerPosition>) lawnMowerWriter)
                .build();
    }

    @Bean
    public String inputFilePath() {
        // Configure the input file path here (e.g., from application properties)
        return "input.txt";
    }

}
