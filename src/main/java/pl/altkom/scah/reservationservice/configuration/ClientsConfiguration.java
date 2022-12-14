package pl.altkom.scah.reservationservice.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ConfigurationProperties(prefix = "pl.altkom.scah")
public class ClientsConfiguration {

    private ClientConfiguration dogService;

    private ClientConfiguration ownerService;
}
