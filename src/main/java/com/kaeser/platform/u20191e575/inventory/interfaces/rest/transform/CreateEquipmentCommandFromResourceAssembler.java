package com.kaeser.platform.u20191e575.inventory.interfaces.rest.transform;

import com.kaeser.platform.u20191e575.inventory.domain.model.commands.CreateEquipmentCommand;
import com.kaeser.platform.u20191e575.inventory.domain.model.entities.Equipment;
import com.kaeser.platform.u20191e575.inventory.interfaces.rest.resources.CreateEquipmentResource;

public class CreateEquipmentCommandFromResourceAssembler {
    public static CreateEquipmentCommand toCommandFromResource(CreateEquipmentResource resource){
        return new CreateEquipmentCommand(
                resource.model(),
                Equipment.toRoleFromName(resource.equipmentType()));
    }
}
