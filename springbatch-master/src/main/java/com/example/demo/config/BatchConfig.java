package com.example.demo.config;

import com.example.demo.model.Employee;
import com.example.demo.processor.EmployeeListProcessor;
import com.example.demo.skipjob.JobSkipPolicy;
import com.example.demo.writer.EmployeeWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.StepListenerFailedException;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.example.demo.reader.ReaderConfig;

import javax.sql.DataSource;

@Configuration
@Import(ReaderConfig.class)
@EnableBatchProcessing
public class BatchConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private FlatFileItemReader<Employee> flatFileItemReader;

    @Bean
    public Job readCSVFilesJob() {
        return jobBuilderFactory
                .get("readCSVFilesJob")
                .incrementer(new RunIdIncrementer())
                .start(step())
                .build();
    }

    @Bean
    public Step step() {
        return stepBuilderFactory.get("step")
                .<Employee, Employee>chunk(10)
                .reader(flatFileItemReader)
                .faultTolerant().skipPolicy(skipPolicy())
                .processor(itemProcessor())
                .writer(writer())
                .build();
    }

    @Bean
    public ItemWriter<Employee> writer() {
        return new EmployeeWriter();
    }

    @Bean
    public ItemProcessor<Employee, Employee> itemProcessor() {
        return new EmployeeListProcessor();
    }

    @Bean
    public JobSkipPolicy skipPolicy() {
        return new JobSkipPolicy();
    }
}
