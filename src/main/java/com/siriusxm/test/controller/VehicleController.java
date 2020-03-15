package com.siriusxm.test.controller;

import com.siriusxm.test.model.Vehicle;
import com.siriusxm.test.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/vehicle-api")
public class VehicleController {

    private VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    /**
     * Creates a vehicle resource
     * @param vehicle
     */
    @RequestMapping("/v1/vehicles/vehicle")
    public ResponseEntity<Mono<String>> createVehicle(@RequestBody Vehicle vehicle) {

        try {
            return vehicleService.createVehicle(vehicle);
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Mono.just(String.format("Error processing request - %s", HttpStatus.INTERNAL_SERVER_ERROR.name())));
        }
    }

}
