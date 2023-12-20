package com.sofka.logs.useCases;

import com.sofka.logs.models.dto.LogDTO;
import com.sofka.logs.models.mongo.ProductMovementLog;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
@AllArgsConstructor
public class ProductMovementLogSaveUseCase implements Function<LogDTO, Mono<LogDTO>> {
    private ReactiveMongoTemplate mongoTemplate;
    private ModelMapper modelMapper;

    @Override
    public Mono<LogDTO> apply(LogDTO logDTO) {
        var log = modelMapper.map(logDTO, ProductMovementLog.class);

        return mongoTemplate.save(log)
            .map(model -> modelMapper.map(model, LogDTO.class));
    }
}