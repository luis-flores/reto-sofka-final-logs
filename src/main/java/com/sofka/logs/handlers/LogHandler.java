package com.sofka.logs.handlers;

import com.sofka.logs.models.dto.LogDTO;
import com.sofka.logs.useCases.*;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class LogHandler {
    private RecordLogListUseCase recordLogListUseCase;
    private ErrorLogListUseCase errorLogListUseCase;
    private ProductMovementLogListUseCase productMovementLogListUseCase;
    private RetailSaleLogListUseCase retailSaleLogListUseCase;
    private WholesaleLogListUseCase wholesaleLogListUseCase;

    public Mono<ServerResponse> listRecords(ServerRequest request) {
        int page = Integer.parseInt(request.pathVariable("page"));

        return ServerResponse.ok()
            .contentType(MediaType.TEXT_EVENT_STREAM)
            .body(recordLogListUseCase.apply(page), LogDTO.class);
    }

    public Mono<ServerResponse> listErrors(ServerRequest request) {
        int page = Integer.parseInt(request.pathVariable("page"));

        return ServerResponse.ok()
            .contentType(MediaType.TEXT_EVENT_STREAM)
            .body(errorLogListUseCase.apply(page), LogDTO.class);
    }

    public Mono<ServerResponse> listProductMovements(ServerRequest request) {
        int page = Integer.parseInt(request.pathVariable("page"));
        String id = request.pathVariable("id");

        return ServerResponse.ok()
            .contentType(MediaType.TEXT_EVENT_STREAM)
            .body(productMovementLogListUseCase.apply(id, page), LogDTO.class);
    }

    public Mono<ServerResponse> listRetailSales(ServerRequest request) {
        int page = Integer.parseInt(request.pathVariable("page"));

        return ServerResponse.ok()
            .contentType(MediaType.TEXT_EVENT_STREAM)
            .body(retailSaleLogListUseCase.apply(page), LogDTO.class);
    }

    public Mono<ServerResponse> listWholesales(ServerRequest request) {
        int page = Integer.parseInt(request.pathVariable("page"));

        return ServerResponse.ok()
            .contentType(MediaType.TEXT_EVENT_STREAM)
            .body(wholesaleLogListUseCase.apply(page), LogDTO.class);
    }
}
