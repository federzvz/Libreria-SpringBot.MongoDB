package co.com.sofka.libreria.useCases;

import co.com.sofka.libreria.mapper.ClassToDTOMapper;
import co.com.sofka.libreria.models.RecursoDTO;
import co.com.sofka.libreria.repositories.RecursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;
import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class EliminarRecursoUseCase implements Function<String, Mono<Void>> {
    private final RecursoRepository recursoRepository;

    public EliminarRecursoUseCase(RecursoRepository recursoRepository) {
        this.recursoRepository = recursoRepository;
    }


    @Override
    public Mono<Void> apply(String id) {
        Objects.requireNonNull(id, "Ingrese ID:");
        return recursoRepository.deleteById(id);
    }
}
