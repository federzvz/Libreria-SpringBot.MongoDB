package co.com.sofka.libreria.useCases.IUseCases;

import co.com.sofka.libreria.models.RecursoDTO;
import reactor.core.publisher.Flux;

@FunctionalInterface
public interface IRecomendarPorTematica {
    Flux<RecursoDTO> get(String tematica);
}
