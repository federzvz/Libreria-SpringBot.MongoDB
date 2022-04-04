package co.com.sofka.libreria.useCases.IUseCases;

import co.com.sofka.libreria.models.RecursoDTO;
import reactor.core.publisher.Flux;

@FunctionalInterface
public interface IRecomendarPorTematicaYTipo {
    Flux<RecursoDTO> get(String tipo, String tematica);
}
