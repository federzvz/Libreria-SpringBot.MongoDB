package co.com.sofka.libreria.routers;


import co.com.sofka.libreria.models.RecursoDTO;
import co.com.sofka.libreria.useCases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import java.util.function.Function;

@Configuration
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
    public RouterFunction<ServerResponse> update(ModificarRecursoUseCase modificarRecursoUseCase) {
        Function<RecursoDTO, Mono<ServerResponse>> executor = recursoDTO -> modificarRecursoUseCase.apply(recursoDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result));

        return route(
                PUT("/editar")
                        .and(accept(MediaType.APPLICATION_JSON)), request -> request
                        .bodyToMono(RecursoDTO.class)
                        .flatMap(executor)
        );
    }

    @Bean
    public RouterFunction<ServerResponse> prestarRecurso(PrestarRecursoUseCase prestarUseCase) {
        return route(
                PUT("/prestar/{id}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(prestarUseCase.apply(request.pathVariable("id")), String.class))
                        .onErrorResume((error) -> ServerResponse.badRequest().build())
        );
    }

    @Bean
    public RouterFunction<ServerResponse> recomedarPorTematica(RecomendarPorTematicaUseCase recomendarPorTematicaUseCase) {
        return route(
                GET("/recomendarportematica/{tematica}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(recomendarPorTematicaUseCase.get(request.pathVariable("tema")), RecursoDTO.class)
                        ).onErrorResume((Error) -> ServerResponse.badRequest().build())
        );
    }

    @Bean
    public RouterFunction<ServerResponse> recomendarPorTipo(RecomendarPorTipoUseCase recomendarPorTipoUseCase) {
        return route(
                GET("/recomendarportipo/{tipo}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(recomendarPorTipoUseCase.get(request.pathVariable("tipo")), RecursoDTO.class)
                        ).onErrorResume((Error) -> ServerResponse.badRequest().build())
        );
    }
    @Bean
    public RouterFunction<ServerResponse> recomendarPorTipoyTematica(RecomendarPorTematicaYTipoUseCase recomendarPorTematicaYTipo) {
        return route(
                GET("/recomendarportipoytematica/{tipo}/{tematica}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(recomendarPorTematicaYTipo.get(request.pathVariable("tipo"), request.pathVariable("tematica")), RecursoDTO.class)
                        ).onErrorResume((Error) -> ServerResponse.badRequest().build())
        );
    }
    @Bean
    public RouterFunction<ServerResponse> regresarRecurso(DevolverRecurso devolverRecurso) {
        return route(
                PUT("/regresar/{id}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(devolverRecurso.apply(request.pathVariable("id")), String.class))
                        .onErrorResume((error) -> ServerResponse.badRequest().build())
        );
    }
}
