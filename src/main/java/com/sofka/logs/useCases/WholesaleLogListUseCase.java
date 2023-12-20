package com.sofka.logs.useCases;

import com.sofka.logs.models.dto.LogDTO;
import com.sofka.logs.models.mongo.WholesaleLog;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.IntFunction;

@Service
@Validated
@AllArgsConstructor
public class WholesaleLogListUseCase implements IntFunction<Flux<LogDTO>> {
    private ReactiveMongoTemplate mongoTemplate;
    private ModelMapper modelMapper;

    @Override
    public Flux<LogDTO> apply(int page) {
        int pageSize = 100;
        long skip = (long)(page-1) * pageSize;

        Query query = new Query()
            .skip(skip)
            .limit(pageSize);

        return mongoTemplate.find(query, WholesaleLog.class)
            .map(product -> modelMapper.map(product, LogDTO.class));
    }
}