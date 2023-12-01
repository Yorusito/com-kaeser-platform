package com.kaeser.platform.u20191e575.inventory.infrastructure.persistence.jpa.repositories;

import com.kaeser.platform.u20191e575.inventory.domain.model.entities.Equipment;
import com.kaeser.platform.u20191e575.inventory.domain.model.valueobjects.MaterialSerialNumberRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    boolean existsBySerialNumber(MaterialSerialNumberRecord serialNumber);

}
