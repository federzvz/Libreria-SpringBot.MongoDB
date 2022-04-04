package co.com.sofka.libreria.useCases;

import co.com.sofka.libreria.mapper.ClassToDTOMapper;
import co.com.sofka.libreria.models.RecursoDTO;
import co.com.sofka.libreria.repositories.RecursoRepository;
import co.com.sofka.libreria.useCases.IUseCases.IRecomendarPorTematicaYTipo;
import reactor.core.publisher.Flux;

import java.util.Objects;

public class RecomendarPorTematicaYTipoUseCase implements IRecomendarPorTematicaYTipo {
    private final RecursoRepository recursoRepository;
    private final ClassToDTOMapper mapper;

    public RecomendarPorTematicaYTipoUseCase(RecursoRepository recursoRepository, ClassToDTOMapper mapper) {
        this.recursoRepository = recursoRepository;
        this.mapper = mapper;
    }

    @Override
    public Flux<RecursoDTO> get(String tipo, String tematica) {
        Objects.requireNonNull(tipo, "ERROR: Debe ingresar el tipo.");
        Objects.requireNonNull(tematica,"ERROR: Debe ingresar la tematica.");
        return recursoRepository.findAllByTipoAndTematica(tipo, tematica)
                .map(mapper.recursoToDTO())
                .distinct();
    }
}
