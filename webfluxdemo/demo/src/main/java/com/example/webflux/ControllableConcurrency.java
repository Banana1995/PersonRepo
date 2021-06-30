package com.example.webflux;

import java.util.Arrays;
import java.util.List;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ControllableConcurrency {

  public Flux<String> allTradeIds(String database) {
    return Flux.fromArray(new String[]{"1" + database, "2" + database, "3" + database});
  }


  public Flux<String> allTradeIds() {
    //每个查库操作需要请求两个集合的查询
    return Flux.merge(
        allTradeIds("local"),
        allTradeIds("host")
    );
  }

  public Flux<Flux<String>> allTradeIdControllable() {
    //一个请求导致 多个查库操作（比如三个时间分区）
    List<String> collects = Arrays.asList("8080", "8081", "8082");
    return Flux.fromIterable(collects)
        .map(this::allTradeIds);
  }

  public Mono<Void> downloadAllTrades() {
    return allTradeIdControllable()
        .flatMap(flux -> flux, 1)//通过这种方式控制并发
        .doOnNext(s -> {
          System.out.println(s);
        }).then();
  }
// TODO: 2021/6/30 q1：为什么这样可以控制并发？q2:直接用merge为什么不可以控制并发？

}
