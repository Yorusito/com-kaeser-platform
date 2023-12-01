package com.kaeser.platform.u20191e575.inventory.interfaces.rest.transform;

import com.kaeser.platform.u20191e575.inventory.domain.model.commands.CreateSparepartCommand;
import com.kaeser.platform.u20191e575.inventory.interfaces.rest.resources.CreateSparepartResource;

public class CreateSparepartCommandFromResourceAssembler {
    public static CreateSparepartCommand toCommandFromResource(CreateSparepartResource resource){
        return new CreateSparepartCommand(
                resource.supplierId(),
                resource.model(),
                resource.supplierProvidedSerialNumber(),
                resource.equipmentId());
    }
}
