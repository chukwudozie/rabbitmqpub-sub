package com.emeka.rabbitmq.producer;

import com.emeka.rabbitmq.producer.entity.Employee;
import com.emeka.rabbitmq.producer.producers.HumanResourceProducer;
import com.emeka.rabbitmq.producer.producers.PictureProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
//@EnableScheduling
public class ProducerApplication implements CommandLineRunner{

// Always autowire the producer class U wish to run
	@Autowired
	private PictureProducer producer;

	// valid sources and types
	private final List<String> SOURCES = List.of("mobile", "web");
	private final List<String> TYPES = List.of("jpg","png","svg");
//	create 10 dummy pictures using the variables above
	public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		producer.sendHello("Emeka Chukwudozie "+Math.random());
//		Create 5 dummy employees and send each of them to queue
		for(int i = 0 ; i < 10 ; i++){
			Employee employee = new Employee("emp/id/"+i, "Employee-"+i, LocalDate.now());
			System.out.println(producer.toString());
//			producer.sendMessage(employee);
		}
	}
}
