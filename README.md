
## 🚀 Запуск приложения

#### Для пользователей windows можно скачать [файл запуска](https://github.com/dshparko/test_task/blob/master/runner.bat)
### Пошаговая установка: 
- Клонировать репозиторий
```bash
git clone https://github.com/dshparko/test_task.git
cd test_task
```
- Ввести данные вашей почты в файл [application.properties](https://github.com/dshparko/test_task/blob/master/notification-service/src/main/resources/application.properties) в notification-service
 ```bash
spring.mail.username=your email
spring.mail.password=your password
 ```
- Установить Docker/Docker-compose
- Затем поэтапно запустить команды в папке test_task
```bash
- docker-compose down --volumes --remove-orphans
- docker-compose build
- docker-compose up --detach
 ```
### Для просмотра эндпоинтов и их тестирования после запуска сервисов можно воспользоваться ссылкой на [документацию](http://localhost:8083/swagger-ui/index.html#/)

### Тестовые данные пользователей:
 ```bash
### Для логина
{
    "username":"petrov",
    "password":"securePassword123"
}

### Для регистрации
{
  "username": "ivanov",
  "firstname": "Ivan",
  "lastname": "Ivanov",
  "email": "ivan@example.com",
  "password": "securePassword123",
  "role": "ADMIN"
}
 ```

### Пример тестовых данных для проверки контроллера notification-service
```bash
{
  "action": "DELETE",
  "username": "darya",
  "email": "darya@mail.ru",
  "password": "securePassword123"
}
```
