package com.kaeser.platform.u20191e575.inventory.interfaces.rest.transform;

import com.kaeser.platform.u20191e575.inventory.domain.model.entities.Equipment;
import com.kaeser.platform.u20191e575.inventory.interfaces.rest.resources.EquipmentResource;

public class EquipmentResourceFromEntityAssembler {
    public static EquipmentResource toResourceFromEntity(Equipment entity){
        return new EquipmentResource(
                entity.getId(),
                entity.getMaterialSerialNumberString(),
                entity.getModel(),
                entity.getEquipmentTypeName());
    }
}
