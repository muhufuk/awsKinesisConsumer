package com.kinesis.service;

import com.amazonaws.services.kinesis.clientlibrary.lib.worker.Worker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KinesisStreamProcessorService implements ApplicationRunner
{
    @Autowired
    private Worker worker;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            log.info("Starting Kinesis worker: {}", worker.getApplicationName());
            worker.run();
        } catch (Throwable t) {
            System.err.println("Caught throwable while processing data.");
            t.printStackTrace();
        }
    }
}
