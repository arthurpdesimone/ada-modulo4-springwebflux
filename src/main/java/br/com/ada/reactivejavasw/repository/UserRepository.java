package br.com.ada.reactivejavasw.repository;

import br.com.ada.reactivejavasw.model.Client;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<Client, Long> {
    Flux<Client> findClientByName(String name);
    Mono<Client> deleteById(String id);
    Mono<Client> findById(String id);
}
