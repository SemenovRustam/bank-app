Bank-App: Быстрый локальный запуск
1. Запуск Minikube
# Стартуем кластер с нужными ресурсами
minikube start --cpus=12 --memory=16384 --driver=docker
eval $(minikube docker-env)


# Проверяем статус
minikube status
kubectl get nodes

собираем Docker-образы:  

docker build -t account:latest ./account             

docker build -t blocker:latest ./blocker

docker build -t cash:latest ./cash

docker build -t exchange:latest ./exchange

docker build -t exchange-generator:latest ./exchange-generator

docker build -t front:latest ./front

docker build -t gateway:latest ./gateway

docker build -t notifications:latest ./notifications

docker build -t transfer:latest ./transfer


2. Создаём namespace
   kubectl create namespace my-namespace

3. Запуск PostgreSQL и Jenkins через Docker Compose
   docker-compose up -d postgres jenkins


Проверка базы:

docker exec -it bank-app-postgres-1 psql -U postgres -l
createdb -h localhost -U postgres bank   # если база bank ещё не создана

4. Сборка всех микросервисов
   ./gradlew clean build


Или собрать один сервис:

cd account
./gradlew clean build

5. Деплой микросервисов в Minikube через Helm
# Деплой всех сервисов через umbrella chart
helm upgrade --install bank-app-umbrella ./bank-app-umbrella -n my-namespace -f ./bank-app-umbrella/values.yaml

# Проверка подов
kubectl get pods -n my-namespace

6. Полезные команды
# Логи конкретного пода
kubectl logs <имя-пода> -n my-namespace

# Перезапуск пода
kubectl delete pod <имя-пода> -n my-namespace

# Если Minikube завис
minikube stop
minikube delete
minikube start --cpus=12 --memory=16384 --driver=docker