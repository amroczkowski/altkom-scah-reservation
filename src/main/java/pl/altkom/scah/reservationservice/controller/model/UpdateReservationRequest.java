package pl.altkom.scah.reservationservice.controller.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UpdateReservationRequest {

    private LocalDate startDate;
    private Integer days;
    private Long ownerId;
    private Long dogId;
}
