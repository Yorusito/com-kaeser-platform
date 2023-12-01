package com.kaeser.platform.u20191e575.inventory.application.internal.commandservices;

import com.kaeser.platform.u20191e575.inventory.application.internal.outboundservices.acl.ExternalEquipmentService;
import com.kaeser.platform.u20191e575.inventory.domain.model.aggregates.Sparepart;
import com.kaeser.platform.u20191e575.inventory.domain.model.commands.CreateSparepartCommand;
import com.kaeser.platform.u20191e575.inventory.domain.services.SparepartCommandService;
import com.kaeser.platform.u20191e575.inventory.infrastructure.persistence.jpa.repositories.SparepartRepository;
import org.springframework.stereotype.Service;

@Service
public class SparepartCommandServiceImpl implements SparepartCommandService{

    private final SparepartRepository sparepartRepository;

    private final ExternalEquipmentService externalEquipmentService;

    public SparepartCommandServiceImpl(SparepartRepository sparepartRepository, ExternalEquipmentService externalEquipmentService)
    { this.sparepartRepository = sparepartRepository;
        this.externalEquipmentService = externalEquipmentService;
    }

    @Override
    public Long handle(CreateSparepartCommand command){

        var supplierId = command.supplierId();
        var model = command.model();
        var supplierProvidedSerialNumber = command.supplierProvidedSerialNumber();

        var equipment = externalEquipmentService.getEquipmentById(command.equipmentId());
        if (equipment.isEmpty()) throw new IllegalArgumentException("Equipment not found");

        sparepartRepository.findBySupplierIdAndModelAndAndSupplierProvidedSerialNumber(supplierId,model,supplierProvidedSerialNumber).map(sparepart -> {
            throw new IllegalArgumentException("SparePart with supplierId " + command.supplierId() +
                    ", model " + command.model() + " and supplierProvidedSerialNumber " + command.supplierProvidedSerialNumber() +
                    " already exists");
        });
        Sparepart sparepart;
        sparepart = new Sparepart(command.supplierId(),command.model(),command.supplierProvidedSerialNumber(), equipment.get());
        sparepartRepository.save(sparepart);
        return sparepart.getId();
    }

}
