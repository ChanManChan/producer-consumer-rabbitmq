package com.u4.rabbitmq.producer;

import com.u4.rabbitmq.producer.entity.Employee;
import com.u4.rabbitmq.producer.entity.Picture;
import com.u4.rabbitmq.producer.generator.HumanResourceProducer;
import com.u4.rabbitmq.producer.generator.PictureProducer;
import com.u4.rabbitmq.producer.generator.PictureProducerTwo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
@EnableScheduling
public class ProducerApplication implements CommandLineRunner {

//	@Autowired
//	private HelloRabbitProducer helloRabbitProducer;

//	@Autowired
//	private EmployeeJsonProducer employeeJsonProducer;

//	@Autowired
//	private HumanResourceProducer humanResourceProducer;

//	@Autowired
//	private PictureProducer pictureProducer;

	@Autowired
	private PictureProducerTwo pictureProducerTwo;

	private final List<String> SOURCES = List.of("mobile", "web");
	private final List<String> TYPES = List.of("jpg", "png", "svg");


	public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// helloRabbitProducer.sendHello("Nanda Gopal " + Math.random());
		for (int i = 0; i < 10; i++) {
			// var e = new Employee("emp " + i, "Employee " + i, LocalDate.now());
			// employeeJsonProducer.sendMessage(e);
			// humanResourceProducer.sendMessage(e);
			var p = new Picture();
			p.setName("Picture " + i);
			p.setSize(ThreadLocalRandom.current().nextLong(1, 10001));
			p.setSource(SOURCES.get(i % SOURCES.size()));
			p.setType(TYPES.get(i % TYPES.size()));
			// pictureProducer.sendMessage(p);
			pictureProducerTwo.sendMessage(p);
		}
	}
}
