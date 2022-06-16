package pl.altkom.scah.reservationservice.controller.mapper;

import pl.altkom.scah.reservationservice.controller.model.CreateReservationRequest;
import pl.altkom.scah.reservationservice.controller.model.UpdateReservationRequest;
import pl.altkom.scah.reservationservice.repository.model.Reservation;

public class RequestMapper {

    public static Reservation bind(final CreateReservationRequest request) {
        final Reservation reservation = new Reservation();
        reservation.setStartDate(request.getStartDate());
        reservation.setDays(request.getDays());
        reservation.setOwnerId(request.getOwnerId());
        reservation.setDogId(request.getDogId());
        return reservation;
    }

    public static Reservation bind(final UpdateReservationRequest request, final Reservation destination) {
        destination.setStartDate(request.getStartDate());
        destination.setDays(request.getDays());
        destination.setOwnerId(request.getOwnerId());
        destination.setDogId(request.getDogId());
        return destination;
    }
}
