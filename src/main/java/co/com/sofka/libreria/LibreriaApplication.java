package co.com.sofka.libreria;

import co.com.sofka.libreria.routers.RecursoRouter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class LibreriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibreriaApplication.class, args);
	}

}
