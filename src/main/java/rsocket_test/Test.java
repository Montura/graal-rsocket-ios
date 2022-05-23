package rsocket_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(proxyBeanMethods = false)
public class Test {
    public static void main(String[] args) {
        System.out.println("Hello from Java");
        SpringApplication.run(Test.class, args);
    }
}
