package com.emeka.rabbitmq.producer;

import com.emeka.rabbitmq.producer.entity.Employee;
import com.emeka.rabbitmq.producer.entity.Furniture;
import com.emeka.rabbitmq.producer.entity.Picture;
import com.emeka.rabbitmq.producer.producers.FurnitureProducer;
import com.emeka.rabbitmq.producer.producers.HumanResourceProducer;
import com.emeka.rabbitmq.producer.producers.PictureProducer;
import com.emeka.rabbitmq.producer.producers.PictureProducerForTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
//@EnableScheduling
public class ProducerApplication implements CommandLineRunner{

// Always autowire the producer class U wish to run
	@Autowired
//	private PictureProducerForTopic producer;
	private FurnitureProducer producer;

	// valid sources and types
//	private final List<String> SOURCES = List.of("mobile", "web");
//	private final List<String> TYPES = List.of("jpg","png","svg");
//	create 10 dummy pictures using the variables above

	private final List<String> COLORS = List.of("white","red", "green");
	private final List<String> MATERIALS = List.of("wood","plastic", "steel");
	public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		producer.sendHello("Emeka Chukwudozie "+Math.random());
//		Create 5 dummy employees and send each of them to queue
//		for(int i = 0 ; i < 10 ; i++){
//			Employee employee = new Employee("emp/id/"+i, "Employee-"+i, LocalDate.now());
//			System.out.println(producer.toString());
//			producer.sendMessage(employee);
//		}

		// Create 10 dummy pictures to be published
//		for(int i = 0 ; i < 10; i++){
//			Picture picture = new Picture();
//			picture.setName("Picture-"+i);
//			// let size be random long between 1 and 10000
//			picture.setSize(ThreadLocalRandom.current().nextLong(1,10000));
//			picture.setSource(SOURCES.get(i % SOURCES.size()));
//			picture.setType(TYPES.get(i % TYPES.size()));
//			producer.sendMessage(picture);
//		}
		// create 10 dummy furniture and publish
		for(int i = 0; i < 10; i++){
			Furniture furniture = new Furniture();
			furniture.setColor(COLORS.get(i % COLORS.size()));
			furniture.setName("Furniture-"+i);
			furniture.setMaterial(MATERIALS.get(i % MATERIALS.size()));
			furniture.setPrice(i);
			producer.sendMessage(furniture);
		}
	}
}
