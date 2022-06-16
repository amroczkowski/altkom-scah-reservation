package pl.altkom.scah.reservationservice.client;

import java.util.List;

import pl.altkom.scah.reservationservice.client.model.Dog;

public interface DogClient {

    Dog getDog(final Long dogId);

    List<Dog> getDogs();
}
