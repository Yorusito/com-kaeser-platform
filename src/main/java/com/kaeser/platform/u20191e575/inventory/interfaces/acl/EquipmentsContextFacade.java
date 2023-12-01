package com.kaeser.platform.u20191e575.inventory.interfaces.acl;

import com.kaeser.platform.u20191e575.inventory.domain.model.entities.Equipment;
import com.kaeser.platform.u20191e575.inventory.domain.model.queries.GetEquipmentByIdQuery;
import com.kaeser.platform.u20191e575.inventory.domain.services.EquipmentQueryService;
import org.springframework.stereotype.Service;

@Service
public class EquipmentsContextFacade {

    private final EquipmentQueryService equipmentQueryService;

    public EquipmentsContextFacade(EquipmentQueryService equipmentQueryService) {
        this.equipmentQueryService = equipmentQueryService;
    }

    public Equipment getEquipmentById(Long id){
        var getEquipmentByIdQuery = new GetEquipmentByIdQuery(id);
        var equipment = equipmentQueryService.handle(getEquipmentByIdQuery);
        if (equipment.isEmpty()) return null;
        return equipment.get();
    }
}
