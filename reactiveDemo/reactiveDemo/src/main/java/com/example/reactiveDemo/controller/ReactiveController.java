package com.example.reactiveDemo.controller;

import com.example.reactiveDemo.data.Response;
import com.example.reactiveDemo.service.ReactiveMathService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reactive-math")
public class ReactiveController {
    private final ReactiveMathService reactiveMathService;

    @GetMapping("/square/{number}")
    public Mono<Response> findSquare(@PathVariable int number) {
        return reactiveMathService.findSquare(number);
    }

    @GetMapping(value = "/table/{input}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Response> multiplicationTable(@PathVariable int input){
        return reactiveMathService.multiplicationTable(input);
    }
}
