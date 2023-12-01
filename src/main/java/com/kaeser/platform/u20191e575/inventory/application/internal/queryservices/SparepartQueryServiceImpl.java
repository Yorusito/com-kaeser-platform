package com.kaeser.platform.u20191e575.inventory.application.internal.queryservices;

import com.kaeser.platform.u20191e575.inventory.domain.model.aggregates.Sparepart;
import com.kaeser.platform.u20191e575.inventory.domain.model.queries.GetAllSparepartQuery;
import com.kaeser.platform.u20191e575.inventory.domain.model.queries.GetEquipmentSparepartsQuery;
import com.kaeser.platform.u20191e575.inventory.domain.model.queries.GetSparepartByIdQuery;
import com.kaeser.platform.u20191e575.inventory.domain.services.SparepartQueryService;
import com.kaeser.platform.u20191e575.inventory.infrastructure.persistence.jpa.repositories.SparepartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SparepartQueryServiceImpl implements SparepartQueryService {

    private final SparepartRepository sparepartRepository;

    public SparepartQueryServiceImpl(SparepartRepository sparepartRepository){
        this.sparepartRepository = sparepartRepository;
    }

    @Override
    public Optional<Sparepart> handle(GetSparepartByIdQuery query) { return sparepartRepository.findById(query.id());}

    @Override
    public List<Sparepart> handle(GetEquipmentSparepartsQuery query){
        return sparepartRepository.findAllByEquipmentId(query.equipmentId());
    }

    @Override
    public List<Sparepart> handle(GetAllSparepartQuery query) {return sparepartRepository.findAll();}

}
