package pl.altkom.scah.reservationservice.controller.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Owner {

    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
}