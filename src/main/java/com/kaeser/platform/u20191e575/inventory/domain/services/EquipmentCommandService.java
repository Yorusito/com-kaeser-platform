package com.kaeser.platform.u20191e575.inventory.domain.services;

import com.kaeser.platform.u20191e575.inventory.domain.model.commands.CreateEquipmentCommand;

public interface EquipmentCommandService {
    Long handle(CreateEquipmentCommand command);
}
