#server launch
bin/zookeeper-server-start.sh   config/zookeeper.properties
bin/kafka-server-start.sh  config/server.properties
#create topic
kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic topic_test
#topic list
bin/kafka-topics.sh --list --bootstrap-server localhost:9092
#procuce msg
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic test
#Hello world！
#Hello Kafka！
#comsume msg
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --from-beginning
#Hello world!
#Hello Kafka!

