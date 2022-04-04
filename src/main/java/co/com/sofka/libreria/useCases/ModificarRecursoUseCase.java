package co.com.sofka.libreria.useCases;

import co.com.sofka.libreria.mapper.ClassToDTOMapper;
import co.com.sofka.libreria.models.RecursoDTO;
import co.com.sofka.libreria.repositories.RecursoRepository;
import co.com.sofka.libreria.useCases.IUseCases.IActualizarRecurso;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public class ModificarRecursoUseCase implements IActualizarRecurso {
    private final RecursoRepository recursoRepository;
    private final ClassToDTOMapper mapper;

    public ModificarRecursoUseCase(RecursoRepository recursoRepository, ClassToDTOMapper mapper) {
        this.recursoRepository = recursoRepository;
        this.mapper = mapper;
    }

    @Override
    public Mono<RecursoDTO> apply(RecursoDTO recursoDTO) {
        Objects.requireNonNull(recursoDTO.getId(), "Ingrese ID:");
        return recursoRepository.save(mapper.dtoToRecurso().apply(recursoDTO))
                .map(recurso -> mapper.recursoToDTO().apply(recurso));
    }
}
