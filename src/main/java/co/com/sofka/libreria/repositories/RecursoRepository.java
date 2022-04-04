package co.com.sofka.libreria.repositories;

import co.com.sofka.libreria.collections.Recurso;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface RecursoRepository extends ReactiveMongoRepository<Recurso, String> {
    Flux<Recurso> findAllByTipo(String tipo);
    Flux<Recurso> findAllByTematica(String tematica);
    Flux<Recurso> findAllByTipoAndTematica(String tipo, String tematica);
}
