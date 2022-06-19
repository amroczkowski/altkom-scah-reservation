package pl.altkom.scah.reservationservice.client;

import java.util.List;

import org.springframework.stereotype.Component;

import pl.altkom.scah.reservationservice.client.model.Dog;

@Component
public class DogClientFallback implements DogClient {

    public Dog getDog(final Long dogId) {
        throw new IllegalStateException("Dog service not responding. Please try again later.");
    }

    public List<Dog> getDogs() {
        throw new IllegalStateException("Dog service not responding. Please try again later.");
    }
}
