package co.com.sofka.libreria.mapper;

import co.com.sofka.libreria.collections.Recurso;
import co.com.sofka.libreria.models.RecursoDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ClassToDTOMapper {
    public Function<RecursoDTO, Recurso> dtoToRecurso() {
        return recurso -> {
            return new Recurso(recurso.getId(),
                    recurso.getNombre(),
                    recurso.getFecha(),
                    recurso.getTipo(),
                    recurso.getTematica());
        };
    }
    public Function<Recurso, RecursoDTO> recursoToDTO() {
        return dto -> new RecursoDTO(
                dto.getId(),
                dto.getNombre(),
                dto.getFecha(),
                dto.getTipo(),
                dto.getTematica()
        );
    }
}
