package com.example.reactiveDemo.service;

import com.example.reactiveDemo.data.Response;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class ReactiveMathService {
    public Mono<Response> findSquare(int number){
//        return Mono.just(new Response(number * number));
        return Mono.fromSupplier(() ->number * number).map(Response::new);
    }

    public Flux<Response> multiplicationTable(int input){
        return Flux.range(1,12)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("Math Service Processing "+i))
                .map(i -> new Response(input * i));
    }
}
