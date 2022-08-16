# KAFKA配置选项
Kafka3.2.0 API：文档https://kafka.apache.org/documentation/#producerconfigs
## 1、生产者
| 配置项                                 | 是否必须 |常用配置|
|-------------------------------------|----|---|
| bootstrap.servers                   | 是  |localhost:9092|
| key.serializer                      | 是  |org.apache.kafka.common.serialization.IntegerSerializer|
| value.serializer                    | 是  | org.apache.kafka.common.serialization.StringSerializer |
| buffer.memory                       
| compression.type                    |    | none, gzip, snappy, lz4, or zstd |
| retries                             
| acks                                |    |[all,-1,0,1]
| batch.size                          
| client.id                           
| transactional.id                    
| transaction.timeout.ms              
| auto.commit.interval.ms             |||
| enable.auto.commit                  |||
| client.dns.lookup                   
| connections.max.idle.ms             
| delivery.timeout.ms                 
| linger.ms                           
| max.block.ms                        
| max.request.size                    
| partitioner.class                   
| receive.buffer.bytes                
| request.timeout.ms                  
| sasl.client.callback.handler.class  
| sasl.jaas.config                    
| sasl.kerberos.service.name          
| sasl.login.callback.handler.class   
| sasl.login.class                    
| sasl.mechanism                      
| sasl.oauthbearer.jwks.endpoint.url  
| sasl.oauthbearer.token.endpoint.url 
| security.protocol                   
| ssl.key.password                    
| ssl.keystore.certificate.chain      
| ssl.keystore.key                    
| ssl.keystore.location               
| ssl.keystore.password               
| ssl.truststore.certificates         
| ssl.truststore.location             
| ssl.truststore.password             


