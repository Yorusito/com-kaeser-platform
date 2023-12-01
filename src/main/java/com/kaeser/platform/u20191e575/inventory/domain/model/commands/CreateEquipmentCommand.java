package com.kaeser.platform.u20191e575.inventory.domain.model.commands;

import com.kaeser.platform.u20191e575.inventory.domain.model.valueobjects.EEquipmentType;

public record CreateEquipmentCommand(
        String model,
        EEquipmentType equipmentType
) {
}
