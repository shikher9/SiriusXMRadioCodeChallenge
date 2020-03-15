package com.siriusxm.test.model;


import lombok.Data;

@Data
public class Vehicle {
        public final String vin;
        public final int year;
        public final String make;
        public final String model;
        public final String transmissionType;
}
