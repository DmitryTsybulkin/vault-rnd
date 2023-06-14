# Getting Started

### Перед началом

Для работы с приложением понадобится:
1. jdk 11;
2. gradle;
3. docker

Настройки для приложения хранятся в application.yml. Настройки для доступа к БД лежать в vault-db.json.
Этот файл можно использовать в интерфейсе vault'а: сохранить по пути: `/secret/db-creds`. Либо создать 
через end-point: `POST /secret`, а затем перезапустить приложение, чтобы БД инициализировалась.

### Запуск vault c веб интерфейсом и с зараннее определённым токеном доступа

`docker run --cap-add=IPC_LOCK -e 'VAULT_DEV_ROOT_TOKEN_ID=secret_token' -p 8200:8200 -d --name=dev-vault vault`

или через docker-compose:

`docker-compose up`

### Остановка

`docker stop vault` или через docker-compose: `docker-compose down`

### Доступные end-point'ы

- `GET /secret?path=&id=` - получить секрет по путю и Id;
- `POST /secret?path=&id=&value=` - добавить секрет;
- `GET /secret/encrypt?secret=` - закодировать секрет и получить результат;
- `GET /secret/decrypt?hash=` - декодировать секрет и получить результат

### Источники

- [Vault project](https://www.vaultproject.io/)
- [Hashicorp Vault docs](https://developer.hashicorp.com/vault/docs)
- [Hashicorp Vault site for developers](https://developer.hashicorp.com/)
- [Vault Configuration with spring boot guide](https://spring.io/guides/gs/vault-config/)
- [Accessing Vault with spring boot guide](https://spring.io/guides/gs/accessing-vault/)
- [Docker hub official page of Vault image](https://hub.docker.com/_/vault)
- [Vault: pros & cons](https://www.contino.io/insights/hashicorp-vault)