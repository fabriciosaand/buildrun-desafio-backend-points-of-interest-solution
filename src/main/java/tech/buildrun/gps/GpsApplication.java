package tech.buildrun.gps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tech.buildrun.gps.entity.PointOfInterest;
import tech.buildrun.gps.repository.PointOfInterestRepository;

import java.util.Arrays;

@SpringBootApplication
public class GpsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GpsApplication.class, args);
	}

	@Autowired
	private PointOfInterestRepository repository;

	@Override
	public void run(String... args) throws Exception {

		repository.save(new PointOfInterest(null, "Lanchonete", 27L, 12L));
		repository.save(new PointOfInterest(null, "Posto", 31L, 18L));
		repository.save(new PointOfInterest(null, "Joalheria", 15L, 12L));
		repository.save(new PointOfInterest(null, "Floricultura", 19L, 21L));
		repository.save(new PointOfInterest(null, "Pub", 12L, 8L));
		repository.save(new PointOfInterest(null, "Supermercado", 23L, 6L));
		repository.save(new PointOfInterest(null, "Churrascaria", 28L, 2L));
	}
}
