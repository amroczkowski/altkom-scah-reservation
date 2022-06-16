package pl.altkom.scah.reservationservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import com.github.tomakehurst.wiremock.core.Options;

@AutoConfigureWireMock(port = Options.DYNAMIC_PORT)
@SpringBootTest
class ReservationServiceApplicationTests {

    @Test
    void contextLoads() {
    }
}
