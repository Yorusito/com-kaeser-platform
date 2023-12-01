package com.kaeser.platform.u20191e575.inventory.interfaces.rest.transform;

import com.kaeser.platform.u20191e575.inventory.domain.model.aggregates.Sparepart;
import com.kaeser.platform.u20191e575.inventory.interfaces.rest.resources.SparepartResource;

public class SparepartResourceFromEntityAssembler {
    public static SparepartResource toResourceFromEntity(Sparepart entity){
        return new SparepartResource(
                entity.getId(),
                entity.getMaterialSerialNumberString(),
                entity.getSupplierId(),
                entity.getModel(),
                entity.getSupplierProvidedSerialNumber(),
                entity.getEquipment().getId()
        );
    }
}
