package co.com.sofka.libreria.repositories;

import co.com.sofka.libreria.collections.Recurso;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecursoRepository extends ReactiveMongoRepository<Recurso, String> {

}
