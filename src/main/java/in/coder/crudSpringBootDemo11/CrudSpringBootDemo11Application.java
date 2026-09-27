package in.coder.crudSpringBootDemo11;

import org.hibernate.annotations.processing.Exclude;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication//(exclude ={DataSourceAutoConfiguration.class})
public class CrudSpringBootDemo11Application {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringBootDemo11Application.class, args);
		System.out.println("Hello World");
	}

}
