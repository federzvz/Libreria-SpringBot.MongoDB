package co.com.sofka.libreria.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
public class RecursoDTO {
    @Id
    private String id;
    private String nombre;
    private LocalDate fecha;
    private String tipo;
    private String tematica;

    public RecursoDTO() {
    }

    public RecursoDTO(String id, String nombre, LocalDate fecha, String tipo, String tematica) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.tipo = tipo;
        this.tematica = tematica;
    }
}
