package pl.altkom.scah.reservationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.altkom.scah.reservationservice.repository.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
