package pl.altkom.scah.reservationservice.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import pl.altkom.scah.reservationservice.controller.model.Dog;
import pl.altkom.scah.reservationservice.controller.model.Owner;
import pl.altkom.scah.reservationservice.repository.model.Reservation;

public class ResponseMapper {
    public static List<pl.altkom.scah.reservationservice.controller.model.Reservation> map(final List<Reservation> reservations,
            final List<Owner> owners, final List<Dog> dogs) {
        return reservations.stream()
                .map(reservation -> map(reservation,
                        owners.stream().filter(owner -> owner.getId().equals(reservation.getOwnerId())).findFirst().orElseThrow(),
                        dogs.stream().filter(dog -> dog.getId().equals(reservation.getDogId())).findFirst().orElseThrow()))
                .collect(Collectors.toList());
    }

    public static pl.altkom.scah.reservationservice.controller.model.Reservation map(final Reservation reservation,
            final Owner owner, final Dog dog) {

        final pl.altkom.scah.reservationservice.controller.model.Reservation result
                = new pl.altkom.scah.reservationservice.controller.model.Reservation();
        result.setId(reservation.getId());
        result.setDog(dog);
        result.setOwner(owner);
        result.setDays(reservation.getDays());
        result.setStartDate(reservation.getStartDate());
        return result;
    }
}
