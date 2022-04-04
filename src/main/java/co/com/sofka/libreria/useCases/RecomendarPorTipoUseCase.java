package co.com.sofka.libreria.useCases;

import co.com.sofka.libreria.mapper.ClassToDTOMapper;
import co.com.sofka.libreria.models.RecursoDTO;
import co.com.sofka.libreria.repositories.RecursoRepository;
import co.com.sofka.libreria.useCases.IUseCases.IRecomendarPorTipo;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

@Service
@Validated
public class RecomendarPorTipoUseCase implements IRecomendarPorTipo {
    private final RecursoRepository recursoRepository;
    private final ClassToDTOMapper mapper;

    public RecomendarPorTipoUseCase(RecursoRepository recursoRepository, ClassToDTOMapper mapper) {
        this.recursoRepository = recursoRepository;
        this.mapper = mapper;
    }

    @Override
    public Flux<RecursoDTO> get(String tipo) {
        return recursoRepository.findAllByTipo(tipo).map(mapper.recursoToDTO());
    }
}
