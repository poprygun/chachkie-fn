package io.microsamples.function.chachkiefn;

import lombok.Builder;
import lombok.Data;
import org.jeasy.random.EasyRandom;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.cloud.function.context.PollableBean;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.time.Instant;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@SpringBootApplication
public class ChachkieFnApplication {

	private final EasyRandom easyRandom = new EasyRandom();

	@PollableBean
	public Supplier<Flux<String>> names(){
		return () -> Flux.just("one", "two", "three").doOnNext(System.out::println);
	}

	@Bean
	public Function<Flux<String>, Flux<Chachkie>> chachkies() {
		return flux -> flux.map(value -> Chachkie.builder()
				.name(value + "-->" + UUID.randomUUID())
				.status("processing")
				.when(easyRandom.nextObject(Instant.class))
				.build()).doOnNext(System.out::println);
	}

	@Bean
	public Consumer<Flux<Chachkie>> chachkiesSink(){
		return chachkieFlux -> chachkieFlux
				.doOnNext(c -> c.setStatus("processed")).subscribe(System.out::println);
	}

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(ChachkieFnApplication.class);
		springApplication.setApplicationStartup(new BufferingApplicationStartup(1000));
		springApplication.run(args);
	}

}

@Data
@Builder
class Chachkie {
	String name;
	String status;
	Instant when;
}