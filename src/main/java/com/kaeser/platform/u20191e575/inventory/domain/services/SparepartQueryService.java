package com.kaeser.platform.u20191e575.inventory.domain.services;

import com.kaeser.platform.u20191e575.inventory.domain.model.aggregates.Sparepart;
import com.kaeser.platform.u20191e575.inventory.domain.model.queries.GetAllSparepartQuery;
import com.kaeser.platform.u20191e575.inventory.domain.model.queries.GetEquipmentSparepartsQuery;
import com.kaeser.platform.u20191e575.inventory.domain.model.queries.GetSparepartByIdQuery;

import java.util.List;
import java.util.Optional;

public interface SparepartQueryService {

    Optional<Sparepart> handle(GetSparepartByIdQuery query);

    List<Sparepart> handle(GetEquipmentSparepartsQuery query);

    List<Sparepart> handle(GetAllSparepartQuery query);
}
