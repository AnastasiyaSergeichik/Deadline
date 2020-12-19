# Deadline
Тестирование функции "Вход в приложение" в личный кабинетинтернет-банка с использованием базы данных mysql.

**Начало работы**
 Запустить SUT (app-deadline.jar), которая работает по схеме БД (schema.sql)

**Установка и запуск** 
1. Открыть проект IntelliJ IDEA
2. Создать файл docker-compose.yml
3. Запустить Docker
4. В командной строке запустить контейнер с помощью команды - doker-compose up -d или docker-compose up -d --force-recreate (заставляет контейнер пересобраться принудительно).
5. В командной строке с помощью java -jar ./artifacts/app-deadline.jar -P:jdbc.url=jdbc:mysql://localhost:3306/app -P:jdbc.user=app -P:jdbc.password=pass запустить приложение app-deadline.jar.
6. Запустить автотесты gradlew test
7. При повторном запуске автотестов нужно перезапустить приложение app-deadline.jar
8. Работу контейнера завершить командой docker-compose down 
