# Microservice architektúra felépítése Spring Clouddal Kubernetes környezetben

Egy microservices alkalmazás felépítése, Java service-ekkel, a microservice tervezési minták felhasználásáva, Spring Cloud használatával, Kubernetesre deployolva.

## Tematika

* Microservice és monolitikus alkalmazás összehasonlítása
* Bevezetés a microservice tervezési mintákba
* Event sourcing
* Domain driven design
* Transaction script, domain model, aggregate
* Domain event
* Serialization formátumok (JSON, Protocol Buffers, Avro)
* REST hívások, OpenFeign, gRPC
* Circuit breaker, Spring Cloud Circuit Breaker, Resilience4J
* Service registry, deployment infrastructure based service registry
* Messaging, Kafka
* Tranzakciókezelés, sagas, choreography, orchestration
* API gateway, Spring Cloud Gateway
* GraphQL
* API composition
* CQRS, Elasticsearch
* Testing, contract testing
* Security, authentication and authorization, Keycloak
* Externalized configuration, Spring Cloud Config, deployment infrastructure based
* Health check
* Spring Boot Admin
* Log aggregation, Elasticsearch, Fluentd, Kibana
* Distributed tracing, Spring Cloud Sleuth, Zipkin
* Exception tracking
* Application metrics, Promeheus, Graphana
* Deployment, containerisation, orchestration, Kubernetes, Spring Cloud Kubernetes
* Átállás monolitikus alkalmazásról microservice alkalmazásra

## Irodalom

Chris Richardson: Microservices Patterns, https://microservices.io/patterns/microservices.html
Vaughn Vernon: Domain-Driven Design Distilled

## Mentoring application

### Vízió

A mai cégeknél gyakori probléma, hogy az alkalmazottak nem
látnak maguk előtt jól kidolgozott karrierútvonalat, nem kapnak
megfelelő támogatást a szakmai fejlődésükhöz. Exit interjúkon
gyakran elhangzik, hogy nem kaptak mentorálást, azaz személyes segítségnyújtást
a továbblépéshez, új dolgok megtanulásához.

Az alkalmazás célja annak támogatása, hogy az alkalmazottak
folyamatosan lássanak maguk előtt egy, vagy több célt, amelyek
eléréséért dolgozhatnak. A célok elérésének útvonala tiszta és átlátható
legyen. Az alkalmazottak vezetői is tiszta rálátással rendelkezzenek
a beosztottjaik előrehaladásáról.

Az alkalmazás egy multinacionális vállalat számára kell kifejleszteni,
amelyik több százezer alkalmazottal rendelkezik, különböző időzónákban,
akik napi szinten használják az alkalmazást.

Az alkalmazás elérhető legyen böngészőből és mobil eszközökről is.

Előreláthatóan dinamikusan fejlődő alkalmazásról van szó, melyet a visszajelzések
alapján folyamatosan fejleszteni kell, már most is rengeteg igény és ötlet
merült fel vele kapcsolatban.

Az alkalmazást már létező Kubernetes környezetbe kell telepíteni.


## DDD

Klasszikus service:

```java
class BillService {

  private BillItemDao dao;

  @Transactional
  public void incrementPrices(long billId, double percent) {
    List<BillItems> items = dao.findBillItems(billId);
    for (BillItem item: items) {
      item.setPrice(item.getPrice() * percent);
    }
  }

}
```

DDD service:

```java
class BillService {

  private BillRepository repo;

  @Transactional
  public void incrementPrices(long billId, double percent) {
    Bill bill = repo.findBillById(billId);
    bill.increasePriceBy(percent);
  }

}

```