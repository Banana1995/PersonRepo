package com.example.webflux;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Controller
public class FrontController {

  @RequestMapping(path = "/api/hello")
  public void getSomething() {
    System.out.println("hellop====");

  }

  public static void main(String[] args) throws InterruptedException {
    Flux.range(1, 1000)
//        .publishOn(Schedulers.parallel(),4)
        .flatMap(i ->
//                Flux.defer(() ->
                FrontController.doQuery(i)
//                    .subscribeOn(Schedulers.boundedElastic())//为什么此处会有main线程
//            )
            , 2
        )
//        .flatMap(i -> FrontController.doQuery(i).
//            subscribeOn(Schedulers.boundedElastic()),2
//        )
        .log()
        .subscribeOn(Schedulers.boundedElastic())//此处不会有main线程
        .subscribe(System.out::println)
    ;
//        .subscribe(System.out::println);
    Thread.currentThread().join();
  }

  private static void swallow(String s) {
    return;
  }

  private static Flux<String> doQuery(Integer input) {
    try {
      Thread.currentThread().sleep(10);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
//    for(int i=0;i<100000;i++){
//    }
    return Flux.just(input + Thread.currentThread().getName());
  }
}
