package com.siriusxm.test.service;

import com.siriusxm.test.model.TransmissionType;
import com.siriusxm.test.model.Vehicle;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class VehicleServiceTest {


    @Test
    public void createVehicleValidTest1() {
        Vehicle vehicle = new Vehicle("1A4AABBC5KD501999", 2018, "FCA", "RAM", TransmissionType.MANUAL.name());
        VehicleService vehicleService = new VehicleService();
        ResponseEntity<Mono<String>> monoResponseEntity = vehicleService.createVehicle(vehicle);
        assertEquals(HttpStatus.CREATED, monoResponseEntity.getStatusCode());
        assertNotNull(monoResponseEntity.getHeaders().containsKey(HttpHeaders.LOCATION));
        assertEquals("", monoResponseEntity.getBody().block());
    }

    @Test
    public void createVehicleValidTest2() {
        Vehicle vehicle = new Vehicle("1A4AABBC5KD501999", 2018, "FCA", "RAM", TransmissionType.AUTO.name());
        VehicleService vehicleService = new VehicleService();
        ResponseEntity<Mono<String>> monoResponseEntity = vehicleService.createVehicle(vehicle);
        assertEquals(HttpStatus.CREATED, monoResponseEntity.getStatusCode());
        assertNotNull(monoResponseEntity.getHeaders().containsKey(HttpHeaders.LOCATION));
        assertEquals("", monoResponseEntity.getBody().block());
    }

    @Test
    public void createVehicleInValidTest1() {
        final String errorMessage = "BAD REQUEST - Please specify a valid transmission type (MANUAL or AUTO)";
        Vehicle vehicle = new Vehicle("1A4AABBC5KD501999", 2018, "FCA", "RAM", null);
        VehicleService vehicleService = new VehicleService();
        ResponseEntity<Mono<String>> monoResponseEntity = vehicleService.createVehicle(vehicle);
        assertEquals(HttpStatus.BAD_REQUEST, monoResponseEntity.getStatusCode());
        assertEquals(errorMessage, monoResponseEntity.getBody().block());
    }

    @Test
    public void createVehicleInValidTest2() {
        final String errorMessage = "BAD REQUEST - Please specify a valid transmission type (MANUAL or AUTO)";
        Vehicle vehicle = null;
        VehicleService vehicleService = new VehicleService();
        ResponseEntity<Mono<String>> monoResponseEntity = vehicleService.createVehicle(vehicle);
        assertEquals(HttpStatus.BAD_REQUEST, monoResponseEntity.getStatusCode());
        assertEquals(errorMessage, monoResponseEntity.getBody().block());
    }

    @Test
    public void createVehicleInValidTest3() {
        final String errorMessage = "BAD REQUEST - Please specify a valid transmission type (MANUAL or AUTO)";
        Vehicle vehicle = new Vehicle("1A4AABBC5KD501999", 2018, "FCA", "RAM", "ertert");
        VehicleService vehicleService = new VehicleService();
        ResponseEntity<Mono<String>> monoResponseEntity = vehicleService.createVehicle(vehicle);
        assertEquals(HttpStatus.BAD_REQUEST, monoResponseEntity.getStatusCode());
        assertEquals(errorMessage, monoResponseEntity.getBody().block());
    }


}
