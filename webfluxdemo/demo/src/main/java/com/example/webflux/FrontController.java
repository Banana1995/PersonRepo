package com.example.webflux;

import java.util.ArrayList;
import java.util.List;
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
//    System.out.println("*********Calling Concurrency************");
//    List<Integer> elements = new ArrayList<>();
//    Flux.just(1, 2, 3, 4)
//        .map(i -> i * 2)
//        .log()
//        .publishOn(Schedulers.boundedElastic())
//        .subscribeOn(Schedulers.parallel())
//        .subscribe(elements::add);
//    System.out.println("-------------------------------------");


    Flux.range(1, 1000)
        .doOnNext(a ->{System.out.println(a+" next "+Thread.currentThread().getName());})
//        .publishOn(Schedulers.parallel(),4)
        .flatMap(i ->
//                Flux.defer(() ->
                FrontController.doQuery(i)
                    .subscribeOn(Schedulers.boundedElastic())//为什么此处会有main线程
//            )
            , 2
        )
//        .flatMap(i -> FrontController.doQuery(i).
//            subscribeOn(Schedulers.boundedElastic()),2
//        )
        .log()
//        .subscribeOn(Schedulers.boundedElastic())//此处不会有main线程
        .subscribe(a ->{System.out.println(a+Thread.currentThread().getName());})
    ;
    Thread.currentThread().join();
//        try {
//      Thread.currentThread().sleep(10000);
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }
  }

  private static void swallow(String s) {
    return;
  }

  private static Flux<String> doQuery(Integer input) {
    try {
      Thread.currentThread().sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
//    for(int i=0;i<100000;i++){
//    }
    return Flux.just(input + Thread.currentThread().getName());
  }
}
