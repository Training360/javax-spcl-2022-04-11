class: inverse, center, middle

# Microservice-ek Spring Clouddal Kubernetes környezetben

---
class: inverse, center, middle

# Kafka

---

## Kafka röviden

* Definíció
  * Nyílt forráskódú
  * Elosztott
  * Event streaming platform: event gyűjtés, tárolás, továbbítás
* Jellemzők
  * High throughput
  * Scalable
  * Permanent storage (distributed)
  * HA

---

## Kafka felépítése

* Event, vagy más néven record, message
  * Felépítése: Kulcs, érték (csak ez a kötelező, formátuma mindegy), timestamp, metadata
* Producer: Kafkahoz kapcsolódó eventeket előállító alkalmazás
* Consumer: eventeket feldolgozó alkalmazás
* Kafka brokerek clusterben tárolják az eventeket
* Az eventek topicokban vannak csoportosítva
  * Mint a könyvtár a fájlrendszereken
  * Csak a vége írható
  * Nem módosítható

---

## Eltérések

* Kafka event nem tűnik el feldolgozás után
* Konfigurálható, hogy az eventeket meddig tárolja
* A tárolt eventek számával nem csökken a teljesítmény
* Offset: azt tárolja, hogy az adott consumer melyik eventeket dolgozta fel

---

## Skálázhatóság, HA

* A topicok partíciókra vannak bontva, melyek különböző brókereken helyezkednek el
* Ugyanazzal az azonosítóval (nem kell egyedinek lennie) rendelkező eventek ugyanazon
  partícióba kerülnek (hash-eli az id-t)
* Azonosítóval nem rendelkezőek Round Robin alapján más partícióba kerülnek
* A partíción belül az eventek sorrendbeli kézbesítése garantált
* Partíciónként minden event kap egy sorszámot
* Egy producer/consumer egyszerre több partícióhoz is kapcsolódhat (skálázhatóság)
* Egy partícióhoz egyszerre több producer/consumer kapcsolódhat
* Partíciók replikálhatók (magas rendelkezésreállás)
  * Leader - írható
  * Follower - csak olvasható

---

## Consumer group

* Consumer kap egy group id-t
* Azonos group id-val rendelkező consumerek között load balance-olja az eventeket
* Különböző group id-val rendelkező össze consumernek továbbítja az eventet (publish)
* Egy partíciót csak egy consumer group member dolgoz fel

---

## Commited offset

* Feldolgozás után visszajelzés, hogy feldolgozásra került, offset léptethető
  * Automatikus
  * Manuális

---

## Consumer lag

* Mennyivel van lemaradva a consumer offset

---

## Kafka connect

* Source connector - külső forrásból eventek előállítása
* Sink connector - eventek továbbítása külső forrás felé

---

## Kafka streams

* Eventek elosztott feldolgozása
* Stream API-szerű, funkcionális API
* Java API-val rendelkezik
* Egyszerűbb igényre jó, bonyolultabbakra: Spark/Hadoop

## Kafdrop

* Webes admin eszköz

---

## Használatba vétel

* Zookeeper - brokerek koordinálására egy open source eszköz
  * kb., mint egy elosztott fájlrendszer
  * gyors, magas rendelkezésre állású
* Docker compose
* Console producer/console consumer

---

class: inverse, center, middle

# Kubernetes

---

# Kubernetes

* Container orchestration (bármilyen CRI implementáció, Docker, vagy akár containerd)
* Több fizikai számítógép (resource management)
* Konténerek szétosztása, életciklus (scheduling, affinity, anti-affinity)
* Deklaratív megközelítés
* Újraindítás alkalmazás/konténer hiba esetén (service management)
* Google -> CNCF
* (Google) Go-ban implementált
* [CNCF Cloud Native Interactive Landscape](https://landscape.cncf.io/)
* Különböző disztribúciók
  * On-premises és cloud megoldások is
  * Különböző gyártók
  * Különböző igények (pl. tesztelés, limitált erőforrások)

# Részei

* Master node (Control plane)
  * API server (REST, authentication, állapotmentes)
  * etcd (key-value adatbázis, konfiguráció és aktuális állapot)
  * Scheduler (dönt, hogy mely konténer hol indítható)
  * Controller manager (elvárt állapot fenntartásért felelős, monitoring loopok, update-eli az etcd-ben az állapotot)
  * Cloud controller manager (opcionális)
* Worker node
  * kubelet (konténerek vezérlése)
  * kube-proxy (hálózat)

# Hálózat

* Cluster DNS (based on CoreDNS)

# Magas rendelkezésreállás

* Legalább három master node (elég csak az etcd-ből)
* Legalább három worker node

# Windowson

* Docker Desktop
* Single node cluster

# Adminisztráció

* `kubectl` parancs
  * https://kubernetes.io/docs/reference/kubectl/cheatsheet/
  * Sokszor manifest fájlokat használ, melyek yaml fájlok
* GUI
* REST API, különböző programozási nyelven megírt kliensek

# Nodes

```
kubectl config get-contexts
kubectl config view
kubectl cluster-info
kubectl get nodes
kubectl get all -A 
kubectl api-resources
```

* `kubectl config view`, mögötte egy konfig fájl, hova csatlakozik a kliens, és milyen felhasználóval (ezt a parancssorban nem lehet látni csak a fájlban)
* `-A` azaz `--all-namespaces`, pl. `kube-system` namespace a rendszerkomponenseknek
* namespace való pl. különböző környezetek (pl. ugyanazon a Kubernetes clusteren) megkülönböztetésére, pl. teszt, uat., stb.
* pod, service, daemonset, deployment, replicaset

# Objects

* Pod
  * Bálnaraj
  * Tipikusan egy konténer
  * 0-n ún. sidecar konténer
  * Konténerek egy ip-t kapnak (vigyázni a portütközéssel), komminukálhatnak egymással localhoston vagy socketen
  * Ideiglenes ip, hiszen bármikor történhet újraindítás
  * Halandóak, ha leáll, hibára fut, Kubernetes törli és újat indít
  * Atomic unit
* Deployment
  * Inkább ezeket használjuk, bár podot is lehet
  * Deployment része a pod definíció
  * Skálázható
  * Rolling update

# Deployment létrehozása

```
docker build -t training360/time-service:0.0.1-SNAPSHOT .
kubectl create deployment time-service --image=training360/time-service:0.0.1-SNAPSHOT --dry-run=client -o=yaml > deployment.yaml
```

Mik szerepelhetnek a yaml fájlon belül?

```shell
kubectl explain pod
```

# Deployment strategy

* Recreate - először podok törlése, majd létrehozása
* Rolling update - Párhuzamosan, a leállási idő csökkentése miatt

# Telepítés

```
kubectl apply -f deployment/deployment.yaml
kubectl get deployments
kubectl get pods
kubectl get all
kubectl logs time-service-58f9c7cdd5-54nwt
kubectl logs -f time-service-58f9c7cdd5-54nwt
```

# Bejelentkezés, futattás

```
kubectl exec -it time-service-58f9c7cdd5-ht276 -- bash 
kubectl exec -it time-service-58f9c7cdd5-ht276 -- ls -l 
```

# Törlés

```
kubectl delete -f deployment\deployment.yaml
```


# Service

* Egy stabil végpontot, DNS nevet, ip-címet és portot kínál
* Load-balance-ol a pod-ok között
* Három típus
  * ClusterIP - clusteren belül, nincs külső hozzáférés
  * NodePort - saját LB kell (30_000-től felfelé), automatikusan CluserIP-t is csinál
  * LoadBalancer - ráül a clusteren kívül LB-re (saját infra, vagy cloud provider), kevésbé konfigolható
  * ExternalName - DNS CNAME alapján


# ClusterIP

```
echo --- >> deployment/deployment.yaml 
kubectl create service clusterip time-service --tcp=8080:8080 --dry-run=client -o=yaml >> deployment\deployment.yaml
kubectl port-forward service/time-service 8080:8080
```

# NodePort + Ingress

* NodePort

```
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v0.43.0/deploy/static/provider/cloud/deploy.yaml
```

`C:\Windows\System32\drivers\etc\hosts`

```
127.0.0.1 timeservice.internal
```

```
apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
  name: time-service-ingress
spec:
  defaultBackend:
    service:
      name: time-service
      port:
        number: 8080
  rules:
    - host: timeservice.local
      http:
        paths:
          - path: /
            pathType: Prefix
            backend:
              service:
                name:  time-service
                port:
                  number: 8080
```

# LoadBalancer

```
echo --- >> deployment/deployment.yaml 
kubectl create service loadbalancer time-service --tcp=8080:8080 --dry-run=client -o=yaml >> deployment\deployment.yaml
```

# Újraindítás

```http
### Shutdown
POST http://localhost:8080/actuator/shutdown
```

# GUI

```
choco install lens
```

# Több példány

* Háttérben a ReplicaSet
* Lehet közvetlenül is ReplicaSet-et kezelni, de ajánlott mindig Deployment-en keresztül

```
replicas: 3
```

```shell
time-service-58f9c7cdd5-r62zq
```

# DaemonSet

* Biztosítja, hogy minden (vagy több) node futtasson a pod egy példányát
* A következő parancs mutatja az ip-címeket és node-okat is

`kubectl get all -A -o wide`

# Rollout, Undo

* Deploymentkor új ReplicaSet jön létre, és erre történik átkapcsolás

```
kubectl rollout history deployment.apps/keycloak
kubectl rollout history deployment.apps/keycloak --revision=1 -o yaml
kubectl rollout undo deployment.apps/keycloak
kubectl rollout status -w deployment.apps/keycloak
```

# ConfigMap, Secret

# Persistent Volume

# StatefulSet

* Stateful alkalmazások esetén
* Hasonló, mint a Deployment, de biztosítja a Pod-ok egyediségét és sorrendiségét
* Akkor használjuk, ha szükség van valamelyikre:
  * Fix hálózati azonosító
  * Stable, persistent storage (???)
  * Sorrendezett telepítés és skálázás
  * Sorrendezett frissítés

# Job, CronJob

# Helm

```
choco install kubernetes-helm
mkdir charts
cd charts
```
