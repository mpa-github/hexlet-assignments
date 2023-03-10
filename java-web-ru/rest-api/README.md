# REST API

API (Application Programming Interface) — программный интерфейс приложения. Это набор инструментов, который позволяет одним программам работать с другими. API предусматривает, что программы могут работать на разных компьютерах. В этом случае требуется организовать интерфейс API так, чтобы ПО могло запрашивать функции друг друга через сеть.

API должен учитывать, что программы могут быть написаны на разных языках, поэтому для общения используется независимый от языка формат обмена данными. В этом упражнении мы будем работать с форматом [JSON](https://ru.wikipedia.org/wiki/JSON).

REST API позволяет использовать для общения между программами протокол HTTP. Другими словами, серверное приложение дает доступ к своим данным клиентскому приложению по определенному URL. Чтобы произвести нужные действия с ресурсом, нужно обратиться к нему определённым способом. Для этого используются четыре HTTP метода — GET, POST, PATCH, DELETE. Рассмотрим на примере работы с пользователем:

* GET-запрос на */api/v1/users* вернёт список всех пользователей
* GET-запрос на */api/v1/users/{id}* вернёт данные конкретного пользователя
* POST-запрос на */api/v1/users* создаёт нового пользователя
* PATCH-запрос на */api/v1/users/{id}* обновляет данные пользователя
* DELETE-запрос на */api/v1/users/{id}* удаляет пользователя

## Ссылки

* [Метод crud() — используется для разработки API. Он автоматически добавляет в приложение пять маршрутов для CRUD операций](https://javalin.io/documentation#handler-groups)
* [Получение данных из базы в виде JSON представления при помощи метода DB.json().toJson()](https://ebean.io/apidoc/11/io/ebean/text/json/JsonContext.html#toJson-java.lang.Object-)
* [Получение экземпляра модели из JSON представления при помощи метода DB.json().toBean()](https://ebean.io/apidoc/11/io/ebean/text/json/JsonContext.html#toBean-java.lang.Class-java.lang.String-)
* [Метод контекста body() — получает тело запроса в виде строки](https://javadoc.io/doc/io.javalin/javalin/latest/io/javalin/http/Context.html)
* [Метод контекста json() — устанавливает тип содержимого application/json в ответе и отправляет JSON строку в теле ответа](https://javadoc.io/static/io.javalin/javalin/4.1.1/io/javalin/http/Context.html#json(Object))
* [Postman — инструмент для тестирования API](https://www.postman.com/)

## src/main/java/exercise/App.java

## Задачи

* Допишите метод `addRoutes()`. Добавьте в приложение пять маршрутов для CRUD пользователя, которые были рассмотрены выше. Для этого удобно воспользоваться методом `crud()`, который автоматически добавит эти маршруты.

## src/main/java/exercise/controllers/UserController.java

## Задачи

* Допишите метод `getAll()`, который возвращает список всех пользователей в виде JSON-представления. 
* Для преобразования списка пользователей в JSON можно воспользоваться методом `DB.json().toJson()`

* Допишите метод `getOne()`, который возвращает конкретного пользователя в виде JSON-представления.

* Допишите метод `create()`, который создаёт нового пользователя из полученного JSON-представления и добавляет его в базу. 
* Для получения экземпляра модели из JSON-представления можно воспользоваться методом `DB.json().toBean()`.

* Допишите метод `update()`, который обновляет данные пользователя в базе данными из полученного JSON-представления.

* Допишите метод, который удаляет пользователя из базы.

* Запустите свое приложение и попробуйте отправлять различные запросы при помощи Postman.

## Подсказки

* Метод `crud()` автоматически добавляет в приложение пять маршрутов для CRUD операций: 
* получение всех сущностей `getAll()`, получение одной сущности `getOne()`, создание `create()`, редактирование `update()` и удаление `delete()` сущности.

* Чтобы отправить JSON строку в теле ответа, используйте метод контекста `json()`.

* В классе `User` есть метод `setId()`, который добавляет id пользователя в экземпляр модели. 
* Вы можете воспользоваться им для обновления пользователя в базе. Для этого нужно установить нужный id и вызвать метод `update()` на модели.

## Самостоятельная работа

* Добавьте валидацию данных при создании пользователя в метод `create()`. 
* Условия валидации для данных пользователя установите по своему желанию. 
* Можно взять те же условия, которые были в предыдущем задании. 
* Так как данные пользователя мы получаем в виде JSON-строки, для валидации всего тела запроса целиком можно воспользоваться методом [bodyValidator()](https://javadoc.io/static/io.javalin/javalin/4.1.1/io/javalin/http/Context.html#bodyValidator(Class)). Пример работы этого метода можно посмотреть в [разделе Валидация документации Javalin](https://javalin.io/documentation#validation)
