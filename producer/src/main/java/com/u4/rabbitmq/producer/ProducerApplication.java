package com.u4.rabbitmq.producer;

import com.u4.rabbitmq.producer.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDate;

@SpringBootApplication
@EnableScheduling
public class ProducerApplication implements CommandLineRunner {

//	@Autowired
//	private HelloRabbitProducer helloRabbitProducer;

//	@Autowired
//	private EmployeeJsonProducer employeeJsonProducer;

	@Autowired
	private HumanResourceProducer humanResourceProducer;

	public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// helloRabbitProducer.sendHello("Nanda Gopal " + Math.random());
		for (int i = 0; i < 5; i++) {
			var e = new Employee("emp " + i, "Employee " + i, LocalDate.now());
			// employeeJsonProducer.sendMessage(e);
			humanResourceProducer.sendMessage(e);
		}
	}
}
