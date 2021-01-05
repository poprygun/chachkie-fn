package io.microsamples.function.chachkiefn;

import lombok.Builder;
import lombok.Data;
import lombok.Value;
import org.jeasy.random.EasyRandom;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.time.Instant;
import java.util.function.Function;

@SpringBootApplication
public class ChachkieFnApplication {

	private final EasyRandom easyRandom = new EasyRandom();

	@Bean
	public Function<Flux<String>, Flux<Chachkie>> chachkies() {

		return flux -> flux.map(value -> Chachkie.builder()
				.name(value)
				.when(easyRandom.nextObject(Instant.class))
				.build());
	}

	public static void main(String[] args) {
		SpringApplication.run(ChachkieFnApplication.class, args);
	}

}

@Value
@Builder
class Chachkie {
	String name;
	Instant when;
}