package pl.altkom.scah.reservationservice.client.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Dog {

    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    private String breed;
    private Long ownerId;
}
