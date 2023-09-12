package br.com.ada.reactivejavasw.controller;


import br.com.ada.reactivejavasw.model.Client;
import br.com.ada.reactivejavasw.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/client")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/{name}")
    public Flux<Client> getClientByName(@PathVariable String name){
        Flux<Client> findFlux = userRepository.findClientByName(name);
        return findFlux;
    }
    @PostMapping()
    public Mono<Client> saveClient(@RequestBody Client client){
        Mono<Client> saveMono = userRepository.save(client);
        return saveMono;
    }
    @DeleteMapping("/{id}")
    public Mono<Client> deleteClientById(@PathVariable String id){
        Mono<Client> deleteMono = userRepository.deleteById(id);
        return deleteMono;
    }
    @PatchMapping("/{id}")
    public Mono<Client> updateClientById(@PathVariable String id ,@RequestBody Client client) throws ExecutionException, InterruptedException {
        Mono<Client> updateMono = userRepository.findById(id);
        updateMono.map(c -> {
           c.setAge(client.getAge());
           c.setEmail(client.getEmail());
           c.setName(client.getName());
           return c;
        });
        Mono<Client> clientUpdated = userRepository.save(updateMono.toFuture().get());
        return clientUpdated;
    }
}
