package com.kaeser.platform.u20191e575.inventory.application.internal.commandservices;

import com.kaeser.platform.u20191e575.inventory.domain.model.commands.CreateEquipmentCommand;
import com.kaeser.platform.u20191e575.inventory.domain.model.entities.Equipment;
import com.kaeser.platform.u20191e575.inventory.domain.services.EquipmentCommandService;
import com.kaeser.platform.u20191e575.inventory.infrastructure.persistence.jpa.repositories.EquipmentRepository;
import org.springframework.stereotype.Service;

@Service
public class EquipmentCommandServiceImpl implements EquipmentCommandService {

    private final EquipmentRepository equipmentRepository;

    public EquipmentCommandServiceImpl(EquipmentRepository equipmentRepository) { this.equipmentRepository = equipmentRepository;}

    @Override
    public Long handle(CreateEquipmentCommand command){


        var equipment = new Equipment(command.model(), command.equipmentType());

        if (equipmentRepository.existsBySerialNumber(equipment.getMaterialSerialNumber()))
            throw new IllegalArgumentException("Serial number already exists");

        equipmentRepository.save(equipment);
        return equipment.getId();

    }
}

