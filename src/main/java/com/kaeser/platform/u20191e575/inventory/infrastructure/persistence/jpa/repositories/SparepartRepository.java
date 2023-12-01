package com.kaeser.platform.u20191e575.inventory.infrastructure.persistence.jpa.repositories;

import com.kaeser.platform.u20191e575.inventory.domain.model.aggregates.Sparepart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SparepartRepository extends JpaRepository<Sparepart, Long> {

    List<Sparepart> findAllByEquipmentId(Long equipmentId);

    Optional<Sparepart> findBySupplierIdAndModelAndAndSupplierProvidedSerialNumber(
            Integer supplierId,
            String model,
            String supplierProvidedSerialNumber
    );
}
