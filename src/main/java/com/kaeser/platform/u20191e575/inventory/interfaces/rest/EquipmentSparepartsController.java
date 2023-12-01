package com.kaeser.platform.u20191e575.inventory.interfaces.rest;

import com.kaeser.platform.u20191e575.inventory.domain.model.queries.GetEquipmentSparepartsQuery;
import com.kaeser.platform.u20191e575.inventory.domain.services.EquipmentQueryService;
import com.kaeser.platform.u20191e575.inventory.domain.services.SparepartQueryService;
import com.kaeser.platform.u20191e575.inventory.interfaces.rest.resources.SparepartResource;
import com.kaeser.platform.u20191e575.inventory.interfaces.rest.transform.SparepartResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * REST Controller for managing SpareParts related to Equipment.
 *
 * @author Sebastian Andre Ramirez Mendez
 */
@RestController
@RequestMapping(value = "/api/v1/equipments/{equipmentId}/sparesparts", produces = APPLICATION_JSON_VALUE)
@Tag(name = "SpareParts")
public class EquipmentSparepartsController {

    private final SparepartQueryService sparepartQueryService;

    /**
     * Constructor for EquipmentSparepartsController.
     *
     * @param sparepartQueryService The service for handling sparepart queries.
     */
    public EquipmentSparepartsController(SparepartQueryService sparepartQueryService) {
        this.sparepartQueryService = sparepartQueryService;
    }

    /**
     * GET endpoint for retrieving the Spareparts related to a specific Equipment.
     *
     * @param equipmentId The ID of the equipment for which to retrieve spareparts.
     * @return ResponseEntity with the list of SparepartResource or a bad request response.
     */
    @GetMapping
    public ResponseEntity<List<SparepartResource>> getEquipmentSpareparts(@PathVariable Long equipmentId) {
        var getEquipmentSparepartsQuery = new GetEquipmentSparepartsQuery(equipmentId);
        var spareparts = sparepartQueryService.handle(getEquipmentSparepartsQuery);
        var sparepartsResource = spareparts.stream().map(SparepartResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(sparepartsResource);
    }
}
