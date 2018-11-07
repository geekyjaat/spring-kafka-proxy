package com.journal.demo.springkafkaproxy;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class KafkaController {

    private String topic;
    private KafkaTemplate<String, String> kafkaTemplate;
    private KafkaAdmin kafkaAdmin;
    private AdminClient adminClient;

    private final short defaultReplicationFactor = 1;

    public KafkaController(@Value("${app.default.topic}") String topic, KafkaTemplate<String, String> kafkaTemplate,
            KafkaAdmin kafkaAdmin) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaAdmin = kafkaAdmin;
        this.adminClient = AdminClient.create(kafkaAdmin.getConfig());

        // let us set up a producer listener for produce success/error as our post
        // method is not sending anything back.
        ProducerListener<String, String> listener = new ProducerListener<String, String>() {
            @Override
            public void onSuccess(ProducerRecord<String, String> producerRecord, RecordMetadata recordMetadata) {
                System.out.println(String.format("record - %s sent to kafka successfully", producerRecord));
            }
        };
        this.kafkaTemplate.setProducerListener(listener);
    }

    /**
     * Create our get controller to list all topics present
     * 
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @GetMapping("topics")
    public Set<String> topics() throws InterruptedException, ExecutionException {
        return this.adminClient.listTopics().names().get();
    }

    /**
     * Let us create a method to allow client to topics
     * 
     */
    @PostMapping("topics/{topicName}")
    public void addTopic(@PathVariable("topicName") String topicName) {
        this.adminClient.createTopics(Collections.singletonList(new NewTopic(topicName, 2, defaultReplicationFactor)));
    }

    /**
     * a post method to send messages to our default topic
     */
    @PostMapping("send")
    public void send(@RequestBody String message) {
        this.kafkaTemplate.send(this.topic, message);
    }

    /**
     * Now the only thing we need is a consumer listener
     */
    @KafkaListener(topics = "local-topic")
    public void listen(ConsumerRecord<String, String> record) throws Exception {
        System.out.println("########### Message consumed ###########");
        System.out.println(record.toString());
    }
}