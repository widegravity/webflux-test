package com.example.demo.Controller;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


class EmployeeControllerTest {

    @Test
    void subscribeOn_twice_for_single_executor_should_complete() {
        getOneFromMono(Schedulers.newSingle("test"));
    }

    @Test
    void subscribeOn_twice_for_single_threaded_boundedElastic_should_complete() {
        // This never completes

        Scheduler scheduler = Schedulers.newBoundedElastic(1, 100000, "scheduler");
        //getOneFromMono(scheduler).subscribe();
        getOneFromMono(scheduler).block();
    }

    @Test
    void subscribeOn_twice_for_single_threaded_executor_should_complete() {
        final Scheduler executorScheduler = Schedulers.fromExecutor(
                new ThreadPoolExecutor(0, 1,
                        60L, TimeUnit.SECONDS,
                        new LinkedBlockingDeque<>()));

        getOneFromMono(executorScheduler);
    }

    private Mono<Integer> getOneFromMono(final Scheduler scheduler) {
        final Mono<Integer> integerMono = getMono(scheduler)
                .zipWith(getMono(scheduler), (i1, i2) -> i1);

        return integerMono;
    }

    private Mono<Integer> getMono(final Scheduler scheduler) {
        return Mono.fromCallable(() -> {
//                    System.out.println(Thread.currentThread().getName());
//                    try {
//                        Thread.sleep(5000);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
                    return 1;
                })
                .subscribeOn(scheduler);
    }


}