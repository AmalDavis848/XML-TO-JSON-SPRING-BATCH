package com.example.RestBatch.config;

import com.example.RestBatch.model.EmployeeContactXml;
import com.example.RestBatch.reader.EmployeeContactWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class EmployeeContactProcessingJobConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeContactProcessingJobConfig.class);

    public static final String JOB_NAME = "Employee-Contact-Processing-Job";

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public Step processEmployeeContactsFile() {
        return new StepBuilder("processEmployeeContactsFile", jobRepository)
                .<EmployeeContactXml, EmployeeContactXml>chunk(100, transactionManager)
                .reader(employeeContactsReader())
                .writer(employeeContactsWriter())
                .build();
    }

    @Bean
    public Job processEmployeeContactsFileJob() {
        return new JobBuilder(JOB_NAME, jobRepository)
                .start(processEmployeeContactsFile())
                .build();
    }

    @Bean
    @StepScope
    public StaxEventItemReader<EmployeeContactXml> employeeContactsReader() {

        String filePath = "classpath:students.xml";

        Resource resource = new FileSystemResource(filePath);



        LOGGER.info("Reading file from path: {}", filePath);
        if (!resource.exists()) {
            LOGGER.error("File not found at path: {}", filePath);
            throw new RuntimeException("File not found at path: " + filePath);
        }

        StaxEventItemReader<EmployeeContactXml> xmlFileReader = new StaxEventItemReader<>();
        xmlFileReader.setResource(resource);
        xmlFileReader.setFragmentRootElementName("EmployeeContact");

        Jaxb2Marshaller xmlMarshaller = new Jaxb2Marshaller();
        xmlMarshaller.setClassesToBeBound(EmployeeContactXml.class);

        xmlFileReader.setUnmarshaller(xmlMarshaller);
        return xmlFileReader;
    }

    @Bean
    @StepScope
    public EmployeeContactWriter employeeContactsWriter() {
        return new EmployeeContactWriter();
    }
}
