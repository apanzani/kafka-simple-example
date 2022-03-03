Per controllare che kafka sia su e che siano stati creati i topic
eseguire il comando -> docker run -it --network=host confluentinc/cp-kafkacat kafkacat -L -b localhost:19092

Per vedere se mi arrivano i messaggi sul container che sta facendo runnare Kafka mi posiziono
nella cartella che contiene i compose files ed
eseguo il comando -> docker-compose exec kafka-broker-1 kafka-console-consumer --bootstrap-server localhost:19092 --topic my-topic