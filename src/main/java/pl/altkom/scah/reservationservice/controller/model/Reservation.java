package pl.altkom.scah.reservationservice.controller.model;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Reservation {

    private Long id;

    private LocalDate startDate;
    private Integer days;
    private Owner owner;
    private Dog dog;
}
