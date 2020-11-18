# hexlet-project-4

Прототип проекта

### Требования к системе для запуска и разработки (без учета OS)

* Java 11

### Стек технологий проекта

* Java 11
* Spring Boot 2
* PostgreSQL (liquibase для миграций)

### Компиляция, запуск и работа с проектом

```bash
$ make # build & run
$ make test # compile and tests
```
Все миграции схемы базы хранятся в `/src/main/resources/db/changelog/` используется liquibase для генерации и обновления схемы.
При локальной разработке используется in memory база H2 в проде PostgreSQL.

Для генерации новой миграции, запустите:

```bash
$ make generate-migration
```
И на основании изменений Entity сгенирируется новый changeset.
