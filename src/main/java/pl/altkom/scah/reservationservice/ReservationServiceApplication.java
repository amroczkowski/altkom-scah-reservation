package pl.altkom.scah.reservationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import pl.altkom.scah.reservationservice.configuration.ClientsConfiguration;

@EnableConfigurationProperties(ClientsConfiguration.class)
@SpringBootApplication
public class ReservationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReservationServiceApplication.class, args);
    }
}
