# amqp-publisher
Docker - RabbitMQ Publisher - Publish messages with/without delay

## Assumptions
 - Docker is already installed on your machine

 ## Steps

 - run command 'docker compose up' to bring up your RabbitMQ instance locally.
 - verify that rabbitMQ is up and running by logging into management console on https://localhost:15672. (credentials - guest/guest)
 - run command 'mvn spring-boot:run'
