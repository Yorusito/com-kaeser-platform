package com.kaeser.platform.u20191e575.inventory.domain.services;

import com.kaeser.platform.u20191e575.inventory.domain.model.entities.Equipment;
import com.kaeser.platform.u20191e575.inventory.domain.model.queries.GetAllEquipmentsQuery;
import com.kaeser.platform.u20191e575.inventory.domain.model.queries.GetEquipmentByIdQuery;

import java.util.List;
import java.util.Optional;

public interface EquipmentQueryService {
    Optional<Equipment> handle(GetEquipmentByIdQuery query);

    List<Equipment> handle (GetAllEquipmentsQuery query);
}
