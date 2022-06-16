package pl.altkom.scah.reservationservice.client.mapper;

import java.util.List;
import java.util.stream.Collectors;

import pl.altkom.scah.reservationservice.controller.model.Dog;
import pl.altkom.scah.reservationservice.controller.model.Owner;

public class ResponseMapper {

    public static List<Owner> mapOwners(final List<pl.altkom.scah.reservationservice.client.model.Owner> owners) {
        return owners.stream()
                .map(ResponseMapper::map)
                .collect(Collectors.toList());
    }

    public static List<Dog> mapDogs(final List<pl.altkom.scah.reservationservice.client.model.Dog> dogs) {
        return dogs.stream()
                .map(ResponseMapper::map)
                .collect(Collectors.toList());
    }

    public static Owner map(final pl.altkom.scah.reservationservice.client.model.Owner owner) {

        final Owner result = new Owner();
        result.setId(owner.getId());
        result.setFirstName(owner.getFirstName());
        result.setLastName(owner.getLastName());
        result.setPhone(owner.getPhone());
        return result;
    }

    public static Dog map(final pl.altkom.scah.reservationservice.client.model.Dog dog) {

        final Dog result = new Dog();
        result.setId(dog.getId());
        result.setName(dog.getName());
        result.setDateOfBirth(dog.getDateOfBirth());
        result.setBreed(dog.getBreed());
        result.setOwnerId(dog.getOwnerId());
        return result;
    }
}
