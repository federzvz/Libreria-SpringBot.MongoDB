package co.com.sofka.libreria.routers;


import co.com.sofka.libreria.models.RecursoDTO;
import co.com.sofka.libreria.useCases.CrearRecursoUseCase;
import co.com.sofka.libreria.useCases.ObtenerRecursosUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import java.util.function.Function;

@Configuration
@RequestMapping("/recurso")
public class RecursoRouter {
    @Bean
    public RouterFunction<ServerResponse> crear(CrearRecursoUseCase crearRecursoUseCase) {
        Function<RecursoDTO, Mono<ServerResponse>> executor = recursoDTO -> crearRecursoUseCase.apply(recursoDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result));

        return route(
                POST("/crear").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(RecursoDTO.class).flatMap(executor)
        );
    }

    @Bean
    @GetMapping(value = "/hello")
    public RouterFunction<ServerResponse> obtenerRecursos(ObtenerRecursosUseCase obtenerRecursosUseCase){
        return route(
                GET("/").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(obtenerRecursosUseCase.apply(), RecursoDTO.class))
        );
    }
}
