package org.ivc.transportation;

import javax.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@SpringBootApplication
@EnableTransactionManagement
public class TransportationApplication {

    @PostConstruct
    @Transactional
    public void init() throws IOException {
    }

    public static void main(String[] args) {
        SpringApplication.run(TransportationApplication.class, args);

    }
    
    

}
