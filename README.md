# Microservice architektúra felépítése Spring Clouddal Kubernetes környezetben

Egy microservices alkalmazás felépítése, Java service-ekkel, a microservice tervezési minták felhasználásáva, Spring Cloud használatával, Kubernetesre deployolva.

## Tematika

* Microservice és monolitikus alkalmazás összehasonlítása
* Bevezetés a microservice tervezési mintákba
* Domain driven design
* Transaction script, domain model, aggregate
* Domain event
* Event storming
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

## Implementáció

```shell
docker run -d -e POSTGRES_DB=course -e POSTGRES_USER=course -e POSTGRES_PASSWORD=course -p 5434:5432  --name course-postgres postgres
docker run -d -e "discovery.type=single-node" -e xpack.security.enabled=false -p 9200:9200 -p 9300:9300 --name elasticsearch elasticsearch:8.1.2
```

```shell
docker compose up -d
docker exec -it kafka-kafka-1 kafka-console-producer.sh --topic quickstart-events --bootstrap-server localhost:9092
docker exec -it kafka-kafka-1 kafka-console-consumer.sh --topic quickstart-events --from-beginning --bootstrap-server localhost:9092
```

```shell
docker run -d -e POSTGRES_DB=career -e POSTGRES_USER=career -e POSTGRES_PASSWORD=career -p 5435:5432  --name career-postgres postgres
```

```shell
docker run -d -e POSTGRES_DB=employees -e POSTGRES_USER=employees -e POSTGRES_PASSWORD=employees -p 5432:5432  --name employees-postgres postgres
```

```shell
docker run -d -p 9411:9411 --name zipkin openzipkin/zipkin
```