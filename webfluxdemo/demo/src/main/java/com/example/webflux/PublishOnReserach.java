package com.example.webflux;

import static java.lang.Thread.sleep;

import java.util.ArrayList;
import java.util.Random;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class PublishOnReserach {


  public static void main(String[] args) throws InterruptedException {
    final Random random = new Random();
    Flux.generate(ArrayList::new, (list, sink) -> {
      int value = random.nextInt(100);
      list.add(value);
      System.out.println("发射元素产生的线程为：" + Thread.currentThread().getName());
      sink.next(value);
      try {
        sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      if (list.size() == 200000) {
        sink.complete();
      }
      return list;
    })
        .flatMap(s -> Flux.just(s + "11", s + "22", s + "33"))
        .publishOn(Schedulers.boundedElastic(), 2)
        .map(x -> String.format("[%s]  %s", Thread.currentThread().getName(), x))

        .subscribe(System.out::println);

    sleep(20000);
  }

}
