package co.com.sofka.libreria.useCases.IUseCases;

import co.com.sofka.libreria.models.RecursoDTO;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface IActualizarRecurso {
    Mono<RecursoDTO> apply(RecursoDTO recursoDTO);
}
