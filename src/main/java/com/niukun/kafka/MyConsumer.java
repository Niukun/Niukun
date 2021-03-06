package com.niukun.kafka;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

//Automatic Offset Committing
public class MyConsumer {

	// private static KafkaConsumer<String, String> consumer;

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		Properties props = new Properties();
		props.put("bootstrap.servers", "192.168.1.11:9092");//172.21.219.0 //59.78.100.126
		props.put("group.id", "num01");
		props.put("enable.auto.commit", "false");
		props.put("auto.commit.interval.ms", "15000");
		props.put("auto.offset.reset", "earliest");
		props.put("auto.commit.interval.ms", "1000");
		props.put("session.timeout.ms", "30000");
		props.put("heartbeat.interval.ms", "5000");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
		consumer.subscribe(Arrays.asList("erp"));
		BufferedWriter bufw = new BufferedWriter(new FileWriter("erp.txt"));
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (ConsumerRecord<String, String> record : records) {
				System.out.printf("offset = %d, key = %s, value = %s", record.offset(), record.key(), record.value());
				System.out.println();
				bufw.write(record.value());
				bufw.newLine();
				bufw.flush();
			}
			// consumer.commitAsync();
		}
	}
}
