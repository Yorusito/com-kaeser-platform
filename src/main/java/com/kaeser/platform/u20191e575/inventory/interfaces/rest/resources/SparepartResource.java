package com.kaeser.platform.u20191e575.inventory.interfaces.rest.resources;

import com.kaeser.platform.u20191e575.inventory.domain.model.entities.Equipment;

public record SparepartResource(
        Long id,

        String serialNumber,

        Integer supplierId,

        String model,

        String supplierProvidedSerialNumber,

        Long equipmentId
) {
}
