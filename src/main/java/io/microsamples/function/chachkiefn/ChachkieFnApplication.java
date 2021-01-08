package io.microsamples.function.chachkiefn;

import lombok.Builder;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.random.EasyRandom;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.time.Instant;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@SpringBootApplication
public class ChachkieFnApplication {

	private final EasyRandom easyRandom = new EasyRandom();

	@Bean
	public Supplier<Flux<String>> names(){
		return () -> Flux.just("one", "two", "three");
	}

	@Bean
	public Function<Flux<String>, Flux<Chachkie>> chachkies() {

		return flux -> flux.map(value -> Chachkie.builder()
				.name(value)
				.when(easyRandom.nextObject(Instant.class))
				.build());
	}

	@Bean
	public Consumer<Flux<Chachkie>> chachkiesSink(){
		return Flux::log;
	}

	public static void main(String[] args) {
		SpringApplication.run(ChachkieFnApplication.class, args);
	}

}

@Value
@Builder
class Chachkie {
	String name;
	String status;
	Instant when;
}