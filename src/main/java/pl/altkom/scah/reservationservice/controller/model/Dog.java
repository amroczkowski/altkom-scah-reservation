package pl.altkom.scah.reservationservice.controller.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Dog {

    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    private String breed;
    private Long ownerId;
}
