package pl.altkom.scah.reservationservice.controller;

import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.tomakehurst.wiremock.client.WireMock;

import pl.altkom.scah.reservationservice.controller.model.CreateReservationRequest;
import pl.altkom.scah.reservationservice.controller.model.Reservation;
import pl.altkom.scah.reservationservice.controller.model.UpdateReservationRequest;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = ReservationControllerTest.PORT)
@AutoConfigureMockMvc
class ReservationControllerTest {

    final static int PORT = 9875;
    @Autowired
    private MockMvc mockMvc;

    @TestConfiguration
    public static class TestConfig {

        @Bean
        public ServiceInstanceListSupplier serviceInstanceListSupplier() {
            return new TestServiceInstanceListSupplier("service", ReservationControllerTest.PORT);
        }
    }

    @BeforeEach
    void init() {
        WireMock.resetAllRequests();
    }

    @Test
    void shouldReturnSingleReservationAndHttpStatusOk() throws Exception {

        //given

        //when
        final MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/reservation/50"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        //then
        final Reservation reservation = jsonToObject(mvcResult.getResponse().getContentAsString());
        Assertions.assertThat(reservation.getId()).isEqualTo(50L);
        Assertions.assertThat(reservation.getDog().getId()).isEqualTo(115);
        Assertions.assertThat(reservation.getOwner().getId()).isEqualTo(819);
    }

    @Test
    void shouldReturnHttpStatusNotFound() throws Exception {
        //given

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/reservation/11150"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
        //then
    }

    @Test
    void shouldCreateAndSaveReservationAndReturnStatusOk() throws Exception {

        //given
        final String requuest = objectToJson(new CreateReservationRequest(LocalDate.now(), 10, 819L, 115L));

        //when
        final MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/reservation")
                .content(requuest)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        //then
        final Reservation reservation = jsonToObject(mvcResult.getResponse().getContentAsString());
        Assertions.assertThat(reservation.getDays()).isEqualTo(10);
        Assertions.assertThat(reservation.getOwner().getId()).isEqualTo(819L);
        Assertions.assertThat(reservation.getDog().getId()).isEqualTo(115L);
    }

    @Test
    void shouldUpdateReservationAndReturnStatusOk() throws Exception {

        //given
        final String requuest = objectToJson(new UpdateReservationRequest(LocalDate.now(), 15, 819L, 115L));

        //when
        final MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/reservation/50")
                .content(requuest)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        //then
        final Reservation reservation = jsonToObject(mvcResult.getResponse().getContentAsString());
        Assertions.assertThat(reservation.getId()).isEqualTo(50L);
        Assertions.assertThat(reservation.getDays()).isEqualTo(15);
    }

    private String objectToJson(final Object obj) throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.writeValueAsString(obj);
    }

    private Reservation jsonToObject(final String json) throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.readValue(json, Reservation.class);
    }

    private List<Reservation> jsonToListOfObjects(final String json) throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.readValue(json, new TypeReference<List<Reservation>>() {
        });
    }
}