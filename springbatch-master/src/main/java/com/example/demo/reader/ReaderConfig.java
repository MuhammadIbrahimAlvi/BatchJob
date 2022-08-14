package com.example.demo.reader;

import com.example.demo.model.Employee;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;

@Configuration
public class ReaderConfig {

    @Bean
    @StepScope
    protected FlatFileItemReader<Employee> reader(@Value("#{jobParameters['fileName']}") Resource fileName) throws Exception {
        DefaultLineMapper<Employee> defaultLineMapper = new DefaultLineMapper<>();
        DefaultLineMapper lineMapper = new DefaultLineMapper();
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(new String[]{"id", "name", "email", "phone", "designation", "accountNumber"});
        FlatFileItemReader fileReader = new FlatFileItemReader();
        fileReader.setLinesToSkip(1);
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper());
        fileReader.setLineMapper(lineMapper);
        fileReader.setResource(new PathResource("/Users/mialvi/Documents/Gap/batchapplication/src/main/resources/Employee.csv"));
        fileReader.afterPropertiesSet();
        return fileReader;
    }

    @Bean
    public FieldSetMapper<Employee> fieldSetMapper() {
        return new EmployeeReader();
    }
}
