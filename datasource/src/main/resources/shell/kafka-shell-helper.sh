#server launch
bin/zookeeper-server-start.sh -daemon  config/zookeeper.properties
bin/kafka-server-start.sh -daemon config/server.properties
#create topic
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test
#topic list
bin/kafka-topics.sh --list --zookeeper localhost:2181
#procuce msg
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic test
Hello world！
Hello Kafka！
#comsume msg
bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic test --from-beginning
Hello world!
Hello Kafka!

