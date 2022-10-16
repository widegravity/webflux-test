package com.example.demo.Repository;

import com.example.demo.Entity.Employee;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Component
public class EmployeeRepository {
    public Mono<Employee> findEmployeeById(String id) {
        return Mono.fromCallable(() -> Employee.ofTest())
                .subscribeOn(Schedulers.boundedElastic());
    }
}
