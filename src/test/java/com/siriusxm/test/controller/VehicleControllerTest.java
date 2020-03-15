package com.siriusxm.test.controller;

import com.siriusxm.test.model.TransmissionType;
import com.siriusxm.test.model.Vehicle;
import com.siriusxm.test.service.VehicleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleControllerTest {


    @Test
    public void createVehicleValidTest1() {
        VehicleService vehicleService = Mockito.mock(VehicleService.class);
        VehicleController vehicleController = new VehicleController(vehicleService);
        Vehicle vehicle = new Vehicle("1A4AABBC5KD501999", 2018, "FCA", "RAM", TransmissionType.MANUAL.name());

        Mockito.when(vehicleService.createVehicle(vehicle)).thenReturn(ResponseEntity
                .status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, UUID.randomUUID().toString())
                .body(Mono.just("")));

        ResponseEntity<Mono<String>> monoResponseEntity = vehicleController.createVehicle(vehicle);
        assertEquals(HttpStatus.CREATED, monoResponseEntity.getStatusCode());
        assertTrue(monoResponseEntity.getHeaders().containsKey(HttpHeaders.LOCATION));
        assertEquals("", monoResponseEntity.getBody().block());
    }

    @Test
    public void createVehicleInValidTest1() {
        VehicleService vehicleService = Mockito.mock(VehicleService.class);
        VehicleController vehicleController = new VehicleController(vehicleService);
        Vehicle vehicle = new Vehicle("1A4AABBC5KD501999", 2018, "FCA", "RAM", TransmissionType.MANUAL.name());
        Mockito.when(vehicleService.createVehicle(vehicle)).thenThrow(new RuntimeException("Error in processing"));
        ResponseEntity<Mono<String>> monoResponseEntity = vehicleController.createVehicle(vehicle);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, monoResponseEntity.getStatusCode());
        assertFalse(monoResponseEntity.getHeaders().containsKey(HttpHeaders.LOCATION));
        assertEquals("Error processing request - INTERNAL_SERVER_ERROR", monoResponseEntity.getBody().block());
    }

}
