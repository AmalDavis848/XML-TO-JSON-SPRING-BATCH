package com.example.RestBatch.reader;

import java.util.List;

import com.example.RestBatch.model.EmployeeContactXml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

public class EmployeeContactWriter implements ItemWriter<EmployeeContactXml> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeContactWriter.class);

    @Override
    public void write(Chunk<? extends EmployeeContactXml> items) throws Exception {

        for ( EmployeeContactXml contact : items) {
            LOGGER.info("Writing contact: {}", contact);
        }

    }

}
