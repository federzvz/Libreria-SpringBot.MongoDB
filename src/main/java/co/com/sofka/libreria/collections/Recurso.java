package co.com.sofka.libreria.collections;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "recurso")
public class Recurso {
    @Id
    private String id;
    private String nombre;
    private LocalDate fecha;
    private String tipo;
    private String tematica;

    public Recurso() {
    }

    public Recurso(String id, String nombre, LocalDate fecha, String tipo, String tematica) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.tipo = tipo;
        this.tematica = tematica;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    @Override
    public String toString() {
        return "Recurso{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", fecha=" + fecha +
                ", tipo='" + tipo + '\'' +
                ", tematica='" + tematica + '\'' +
                '}';
    }
}
