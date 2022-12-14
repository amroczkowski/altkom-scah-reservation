package pl.altkom.scah.reservationservice.client;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import pl.altkom.scah.reservationservice.client.model.Owner;
import pl.altkom.scah.reservationservice.configuration.ClientsConfiguration;

@Component
public class OwnerClient {

    private final WebClient client;

    public OwnerClient(final ClientsConfiguration clientsConfiguration) {
        this.client = WebClient.builder()
                .baseUrl(clientsConfiguration.getOwnerService().getUrl())
                .build();
    }

    public Owner getOwner(final Long ownerId) {
        return client
                .get()
                .uri("/owner/{ownerId}", Map.of("ownerId", ownerId))
                .retrieve()
                .bodyToMono(Owner.class)
                .block();
    }

    public List<Owner> getOwners() {
        return client
                .get()
                .uri("/onwer", Map.of())
                .retrieve()
                .bodyToFlux(Owner.class)
                .collectList()
                .block();
    }
}
