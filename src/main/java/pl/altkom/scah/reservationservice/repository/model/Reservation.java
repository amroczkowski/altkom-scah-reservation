package pl.altkom.scah.reservationservice.repository.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Reservation {

    @Id
    @GeneratedValue(generator = "reservationSeq")
    @SequenceGenerator(name = "reservationSeq", sequenceName = "reservation_seq", allocationSize = 20)
    private Long id;

    private LocalDate startDate;
    private Integer days;
    private Long ownerId;
    private Long dogId;
}
