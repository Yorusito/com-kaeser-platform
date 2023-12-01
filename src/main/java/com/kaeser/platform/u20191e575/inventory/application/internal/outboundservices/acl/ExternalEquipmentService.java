package com.kaeser.platform.u20191e575.inventory.application.internal.outboundservices.acl;

import com.kaeser.platform.u20191e575.inventory.domain.model.entities.Equipment;
import com.kaeser.platform.u20191e575.inventory.interfaces.acl.EquipmentsContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalEquipmentService {
    private final EquipmentsContextFacade equipmentsContextFacade;

    public ExternalEquipmentService(EquipmentsContextFacade equipmentsContextFacade){
        this.equipmentsContextFacade = equipmentsContextFacade;
    }

    public Optional<Equipment> getEquipmentById(Long id) {
        var equipment = equipmentsContextFacade.getEquipmentById(id);
        if (equipment == null) return Optional.empty();
        return Optional.of(equipment);
    }
}
