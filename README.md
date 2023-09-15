# retail-discounts
A retail shop discount calculator


# RUN THE DOCKERIZED DB & APP
D: && cd D:\projects\Apps\retail-discounts
docker-compose up -d mongo && docker-compose up -d mongo-express && mvn clean install && docker-compose up -d app

# RUN DOCKERIZED DB ONLY
D: && cd D:\projects\Apps\retail-discounts
docker-compose up -d mongo && docker-compose up -d mongo-express && mvn clean install

# RUN Tests
mvn test

# RUN SONAR SCAN
mvn sonar:sonar -Dsonar.java.binaries=target/classes -Dsonar.login=sqa_1c3d332a32e5c580ec43dd08fd28c1af9479b6aa

# JACOCO report path
D:\projects\Apps\retail-discounts\target\site\jacoco