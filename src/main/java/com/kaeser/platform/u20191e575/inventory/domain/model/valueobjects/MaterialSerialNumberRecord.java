package com.kaeser.platform.u20191e575.inventory.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public record MaterialSerialNumberRecord(String serialNumber) {

    public MaterialSerialNumberRecord(){ this(UUID.randomUUID().toString());}

    public MaterialSerialNumberRecord{
        if (serialNumber == null || serialNumber.isBlank()){
            throw new IllegalArgumentException("Serial Number cannot be null or blank");
        }
    }
}
