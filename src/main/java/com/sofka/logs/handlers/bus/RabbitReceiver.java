package com.sofka.logs.handlers.bus;


import com.sofka.logs.ConfigurationPrinter;
import com.sofka.logs.models.dto.LogDTO;
import com.sofka.logs.useCases.*;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.rabbitmq.Receiver;

import java.util.Date;

@Component
@AllArgsConstructor
public class RabbitReceiver implements CommandLineRunner {
    private Receiver receiver;
    private RecordLogSaveUseCase recordLogSaveUseCase;
    private ErrorLogSaveUseCase errorLogSaveUseCase;
    private ProductMovementLogSaveUseCase productMovementLogSaveUseCase;
    private RetailSaleLogSaveUseCase retailSaleLogSaveUseCase;
    private WholesaleLogSaveUseCase wholesaleLogSaveUseCase;

    @Override
    public void run(String... args) throws Exception {
        Logger logger = LoggerFactory.getLogger(ConfigurationPrinter.class);

        receiver.consumeAutoAck(RabbitConfig.RECORD_QUEUE)
                .map(message -> {
                    var logDTO = new LogDTO();
                    logDTO.setDate(new Date());
                    logDTO.setMessage(new String(message.getBody()));
                    recordLogSaveUseCase.apply(logDTO)
                        .subscribe();
                    logger.info("Record Received");
                    return logDTO;
                }).subscribe();
        receiver.consumeAutoAck(RabbitConfig.ERROR_QUEUE)
            .map(message -> {
                var logDTO = new LogDTO();
                logDTO.setDate(new Date());
                logDTO.setMessage(new String(message.getBody()));
                errorLogSaveUseCase.apply(logDTO)
                    .subscribe();
                logger.info("Error Received");
                return logDTO;
            }).subscribe();
        receiver.consumeAutoAck(RabbitConfig.PRODUCT_MOVEMENTS_QUEUE)
            .map(message -> {
                var logDTO = new LogDTO();
                logDTO.setDate(new Date());
                logDTO.setMessage(new String(message.getBody()));
                productMovementLogSaveUseCase.apply(logDTO)
                    .subscribe();
                logger.info("Product Movement Received");
                return logDTO;
            }).subscribe();
        receiver.consumeAutoAck(RabbitConfig.RETAIL_SALES_QUEUE)
            .map(message -> {
                var logDTO = new LogDTO();
                logDTO.setDate(new Date());
                logDTO.setMessage(new String(message.getBody()));
                retailSaleLogSaveUseCase.apply(logDTO)
                    .subscribe();
                logger.info("Retail Sale Received");
                return logDTO;
            }).subscribe();
        receiver.consumeAutoAck(RabbitConfig.WHOLESALE_QUEUE)
            .map(message -> {
                var logDTO = new LogDTO();
                logDTO.setDate(new Date());
                logDTO.setMessage(new String(message.getBody()));
                wholesaleLogSaveUseCase.apply(logDTO)
                    .subscribe();
                logger.info("Wholesale Received");
                return logDTO;
            }).subscribe();
    }
}
