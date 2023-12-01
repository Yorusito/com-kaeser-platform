package com.kaeser.platform.u20191e575.inventory.interfaces.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.kaeser.platform.u20191e575.inventory.domain.model.commands.CreateEquipmentCommand;
import com.kaeser.platform.u20191e575.inventory.domain.model.queries.GetEquipmentByIdQuery;
import com.kaeser.platform.u20191e575.inventory.domain.services.EquipmentCommandService;
import com.kaeser.platform.u20191e575.inventory.domain.services.EquipmentQueryService;
import com.kaeser.platform.u20191e575.inventory.interfaces.rest.resources.CreateEquipmentResource;
import com.kaeser.platform.u20191e575.inventory.interfaces.rest.resources.EquipmentResource;
import com.kaeser.platform.u20191e575.inventory.interfaces.rest.transform.CreateEquipmentCommandFromResourceAssembler;
import com.kaeser.platform.u20191e575.inventory.interfaces.rest.transform.EquipmentResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for managing Equipments.
 *
 * @author Sebastian Andre Ramirez Mendez
 */
@RestController
@RequestMapping(value = "/api/v1/equipments", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Equipments", description = "Equipments Management Endpoints")
public class EquipmentsController {

    private final EquipmentCommandService equipmentCommandService;
    private final EquipmentQueryService equipmentQueryService;

    /**
     * Constructor for EquipmentsController.
     *
     * @param equipmentCommandService The service for handling equipment commands.
     * @param equipmentQueryService The service for handling equipment queries.
     */
    public EquipmentsController(EquipmentCommandService equipmentCommandService, EquipmentQueryService equipmentQueryService) {
        this.equipmentCommandService = equipmentCommandService;
        this.equipmentQueryService = equipmentQueryService;
    }

    /**
     * POST endpoint for creating a new Equipment.
     *
     * @param createEquipmentResource The resource containing information for creating an equipment.
     * @return ResponseEntity with the created EquipmentResource or a bad request response.
     */
    @PostMapping
    public ResponseEntity<EquipmentResource> createEquipment(@RequestBody CreateEquipmentResource createEquipmentResource) {
        var createEquipmentCommand = CreateEquipmentCommandFromResourceAssembler.toCommandFromResource(createEquipmentResource);
        var equipmentId = equipmentCommandService.handle(createEquipmentCommand);

        if (equipmentId == 0L) {
            return ResponseEntity.badRequest().build();
        }

        var getEquipmentByIdQuery = new GetEquipmentByIdQuery(equipmentId);
        var equipment = equipmentQueryService.handle(getEquipmentByIdQuery);

        if (equipment.isEmpty()) return ResponseEntity.badRequest().build();
        var equipmentResource = EquipmentResourceFromEntityAssembler.toResourceFromEntity(equipment.get());
        return new ResponseEntity<>(equipmentResource, HttpStatus.CREATED);
    }

    /**
     * GET endpoint for retrieving an Equipment by its ID.
     *
     * @param equipmentId The ID of the equipment to retrieve.
     * @return ResponseEntity with the retrieved EquipmentResource or a bad request response.
     */
    @GetMapping("/{equipmentId}")
    public ResponseEntity<EquipmentResource> getEquipmentById(@PathVariable Long equipmentId) {
        var getEquipmentByIdQuery = new GetEquipmentByIdQuery(equipmentId);
        var equipment = equipmentQueryService.handle(getEquipmentByIdQuery);

        if (equipment.isEmpty()) return ResponseEntity.badRequest().build();
        var equipmentResource = EquipmentResourceFromEntityAssembler.toResourceFromEntity(equipment.get());
        return ResponseEntity.ok(equipmentResource);
    }
}