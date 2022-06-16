package pl.altkom.scah.reservationservice.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import pl.altkom.scah.reservationservice.client.DogClient;
import pl.altkom.scah.reservationservice.client.OwnerClient;
import pl.altkom.scah.reservationservice.controller.mapper.RequestMapper;
import pl.altkom.scah.reservationservice.controller.mapper.ResponseMapper;
import pl.altkom.scah.reservationservice.controller.model.CreateReservationRequest;
import pl.altkom.scah.reservationservice.controller.model.Dog;
import pl.altkom.scah.reservationservice.controller.model.Owner;
import pl.altkom.scah.reservationservice.controller.model.Reservation;
import pl.altkom.scah.reservationservice.controller.model.UpdateReservationRequest;
import pl.altkom.scah.reservationservice.repository.ReservationRepository;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private final OwnerClient ownerClient;
    private final DogClient dogClient;
    private final ReservationRepository reservationRepository;

    public List<Reservation> getReservations() {
        final List<Owner> owners = pl.altkom.scah.reservationservice.client.mapper.ResponseMapper.mapOwners(ownerClient.getOwners());
        final List<Dog> dogs = pl.altkom.scah.reservationservice.client.mapper.ResponseMapper.mapDogs(dogClient.getDogs());
        return ResponseMapper.map(reservationRepository.findAll(), owners, dogs);
    }

    public Reservation getReservation(final Long reservationId) {
        final pl.altkom.scah.reservationservice.repository.model.Reservation reservation = reservationRepository
                .findById(reservationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        final Owner owner = pl.altkom.scah.reservationservice.client.mapper.ResponseMapper
                .map(ownerClient.getOwner(reservation.getOwnerId()));
        final Dog dog = pl.altkom.scah.reservationservice.client.mapper.ResponseMapper
                .map(dogClient.getDog(reservation.getDogId()));

        return ResponseMapper.map(reservation, owner, dog);
    }

    public Reservation createReservation(final CreateReservationRequest request) {
        final pl.altkom.scah.reservationservice.repository.model.Reservation savedReservation = reservationRepository
                .save(RequestMapper.bind(request));

        final Owner owner = pl.altkom.scah.reservationservice.client.mapper.ResponseMapper
                .map(ownerClient.getOwner(savedReservation.getOwnerId()));
        final Dog dog = pl.altkom.scah.reservationservice.client.mapper.ResponseMapper
                .map(dogClient.getDog(savedReservation.getDogId()));

        return ResponseMapper.map(savedReservation, owner, dog);
    }

    public Reservation updateReservation(final Long reservationId, final UpdateReservationRequest request) {

        final pl.altkom.scah.reservationservice.repository.model.Reservation sourceReservation = reservationRepository
                .findById(reservationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        final pl.altkom.scah.reservationservice.repository.model.Reservation modifiedReservation = reservationRepository
                .save(RequestMapper.bind(request, sourceReservation));
        final Owner owner = pl.altkom.scah.reservationservice.client.mapper.ResponseMapper
                .map(ownerClient.getOwner(modifiedReservation.getOwnerId()));
        final Dog dog = pl.altkom.scah.reservationservice.client.mapper.ResponseMapper
                .map(dogClient.getDog(modifiedReservation.getDogId()));

        return ResponseMapper.map(modifiedReservation, owner, dog);
    }
}
