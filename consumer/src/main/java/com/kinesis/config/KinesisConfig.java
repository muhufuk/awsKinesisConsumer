package com.kinesis.config;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.kinesis.clientlibrary.interfaces.v2.IRecordProcessor;
import com.amazonaws.services.kinesis.clientlibrary.interfaces.v2.IRecordProcessorFactory;
import com.amazonaws.services.kinesis.clientlibrary.lib.worker.InitialPositionInStream;
import com.amazonaws.services.kinesis.clientlibrary.lib.worker.KinesisClientLibConfiguration;
import com.amazonaws.services.kinesis.clientlibrary.lib.worker.Worker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KinesisConfig
{
    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${consumer.stream.name}")
    private String awsMixedStreamName;

    @Bean
    public IRecordProcessorFactory recordProcessorFactory(IRecordProcessor recordProcessor) {
        return () -> recordProcessor;
    }

    @Bean
    public ClientConfiguration clientConfiguration() {
        return new ClientConfiguration();
    }

    @Bean
    public KinesisClientLibConfiguration kinesisClientLibConfiguration(final ClientConfiguration clientConfiguration) {
        return new KinesisClientLibConfiguration(applicationName, awsMixedStreamName,
                new DefaultAWSCredentialsProviderChain(), "Worker1")
                .withRegionName(Regions.EU_CENTRAL_1.getName())
                .withInitialPositionInStream(InitialPositionInStream.TRIM_HORIZON)
                .withCommonClientConfig(clientConfiguration);
    }

    @Bean
    public Worker worker(final IRecordProcessorFactory recordProcessorFactory, final KinesisClientLibConfiguration kinesisClientLibConfiguration) {
        return new Worker.Builder()
                .recordProcessorFactory(recordProcessorFactory)
                .config(kinesisClientLibConfiguration)
                .build();
    }
}
