package pl.altkom.scah.reservationservice.client;

import java.util.List;

import pl.altkom.scah.reservationservice.client.model.Owner;

public interface OwnerClient {

    Owner getOwner(final Long ownerId);

    List<Owner> getOwners();
}
