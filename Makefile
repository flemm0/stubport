# Defaults
TOPIC ?= test-topic
PARTITIONS ?= 1
REPLICAS ?= 1

.PHONY: kafka-local-start kafka-list-topics kafka-create-topic kafka-delete-topic kafka-print-topic kafka-local-stop \
	compile run

kafka-local-start:
	docker compose up -d

kafka-list-topics:
	docker compose exec broker /opt/kafka/bin/kafka-topics.sh \
		--bootstrap-server=localhost:9092 \
		--list

kafka-create-topic:
	docker compose exec broker /opt/kafka/bin/kafka-topics.sh \
		--create \
		--topic $(TOPIC) \
		--bootstrap-server=localhost:9092 \
		--partitions $(PARTITIONS) \
		--replication-factor $(REPLICAS)

kafka-delete-topic:
	docker compose exec broker /opt/kafka/bin/kafka-topics.sh \
		--delete \
		--topic $(TOPIC) \
		--bootstrap-server=localhost:9092

kafka-print-topic:
	docker compose exec broker /opt/kafka/bin/kafka-console-consumer.sh \
		--bootstrap-server=localhost:9092 \
		--topic $(TOPIC) \
		--from-beginning \
		--timeout-ms 10000

kafka-local-stop:
	docker compose down

compile:
	mvn compile

run:
	mvn exec:java -Dexec.mainClass="com.bita.App"
