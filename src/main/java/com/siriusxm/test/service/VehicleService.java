package com.siriusxm.test.service;


import com.siriusxm.test.model.TransmissionType;
import com.siriusxm.test.model.Vehicle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Slf4j
public class VehicleService {

    public ResponseEntity<Mono<String>> createVehicle(Vehicle vehicle) {

        if (!validVehicle(vehicle)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Mono.just("BAD REQUEST - Please specify a valid transmission type (MANUAL or AUTO)"));
        }

        UUID vehicleId = UUID.randomUUID();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, vehicleId.toString())
                .body(Mono.just(""));
    }


    private boolean validVehicle(Vehicle vehicle) {
        return vehicle != null && vehicle.transmissionType != null && (vehicle.transmissionType.equals(TransmissionType.MANUAL.name()) || vehicle.transmissionType.equals(TransmissionType.AUTO.name()));
    }
}
