package co.com.sofka.libreria.useCases;

import co.com.sofka.libreria.mapper.ClassToDTOMapper;
import co.com.sofka.libreria.repositories.RecursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Objects;
import java.util.function.Function;


@Service
@Validated
public class PrestarRecursoUseCase implements Function<String, Mono<String>> {
    private final RecursoRepository recursoRepository;
    private final ModificarRecursoUseCase modificarRecursoUseCase;
    private final ClassToDTOMapper mapper;

    public PrestarRecursoUseCase(RecursoRepository recursoRepository, ClassToDTOMapper mapper) {
        this.recursoRepository = recursoRepository;
        this.mapper = mapper;
        this.modificarRecursoUseCase = new ModificarRecursoUseCase(recursoRepository,mapper);
    }

    @Override
    public Mono<String> apply(String id) {
        Objects.requireNonNull(id, "ERROR: ID ingresado es nulo.");
        return recursoRepository.findById(id).flatMap(
                recurso -> {
                        recurso.setFecha(LocalDate.now());
                        return modificarRecursoUseCase.apply(mapper.recursoToDTO().apply(recurso))
                                .thenReturn("El Recurso " + recurso.getNombre() + "ha sido prestado");
                }
        );
    }
}
