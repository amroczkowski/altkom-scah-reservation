package pl.altkom.scah.reservationservice.client;

import java.util.List;

import org.springframework.stereotype.Component;

import pl.altkom.scah.reservationservice.client.model.Owner;

@Component
public class OwnerClientFallback implements OwnerClient {
    @Override
    public Owner getOwner(final Long ownerId) {
        throw new IllegalStateException("Owner service not responding. Please try again later.");
    }

    @Override
    public List<Owner> getOwners() {
        throw new IllegalStateException("Owner service not responding. Please try again later.");
    }
}
