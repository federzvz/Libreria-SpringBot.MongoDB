package co.com.sofka.libreria.useCases;

import co.com.sofka.libreria.mapper.ClassToDTOMapper;
import co.com.sofka.libreria.models.RecursoDTO;
import co.com.sofka.libreria.repositories.RecursoRepository;
import co.com.sofka.libreria.useCases.IUseCases.ICrearRecurso;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class CrearRecursoUseCase implements ICrearRecurso {
    private final RecursoRepository recursoRepository;
    private final ClassToDTOMapper mapper;

    public CrearRecursoUseCase(RecursoRepository recursoRepository, ClassToDTOMapper mapper) {
        this.recursoRepository = recursoRepository;
        this.mapper = mapper;
    }

    @Override
    public Mono<RecursoDTO> apply(RecursoDTO recursoDTO){
        return recursoRepository.save(mapper.dtoToRecurso().apply(recursoDTO))
                .flatMap(recurso -> Mono.just(mapper.recursoToDTO().apply(recurso)));
    }
}
