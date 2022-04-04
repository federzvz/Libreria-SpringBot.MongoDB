package co.com.sofka.libreria.useCases;

import co.com.sofka.libreria.mapper.ClassToDTOMapper;
import co.com.sofka.libreria.models.RecursoDTO;
import co.com.sofka.libreria.repositories.RecursoRepository;
import co.com.sofka.libreria.useCases.IUseCases.IObtenerRecursos;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Validated
public class ObtenerRecursosUseCase implements IObtenerRecursos {
    private final RecursoRepository recursoRepository;
    private final ClassToDTOMapper mapper;


    public ObtenerRecursosUseCase(RecursoRepository recursoRepository, ClassToDTOMapper mapper) {
        this.recursoRepository = recursoRepository;
        this.mapper = mapper;
    }

    public Flux<RecursoDTO> apply(){
        return recursoRepository.findAll()
                .flatMap(recurso -> Mono.just(mapper.recursoToDTO().apply(recurso)));
    }
}
