package com.kaeser.platform.u20191e575.inventory.domain.model.commands;

import com.kaeser.platform.u20191e575.inventory.domain.model.entities.Equipment;

public record CreateSparepartCommand(

        Integer supplierId,

        String model,

        String supplierProvidedSerialNumber,

        Long equipmentId

) {
}
