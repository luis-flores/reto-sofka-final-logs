package com.sofka.logs.routes;

import com.sofka.logs.handlers.LogHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@AllArgsConstructor
public class LogRouter {
    private LogHandler handler;

    @Bean
    public RouterFunction<ServerResponse> productRoutes() {
        return RouterFunctions.route()
            .GET("/records/{page}", handler::listRecords)
            .GET("/errors/{page}", handler::listErrors)
            .GET("/productMovements/{id}/{page}", handler::listProductMovements)
            .GET("/retail_sales/{page}", handler::listRetailSales)
            .GET("/wholesales/{page}", handler::listWholesales)
            .build();
    }
}
