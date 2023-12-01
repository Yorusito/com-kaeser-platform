package com.kaeser.platform.u20191e575.inventory.interfaces.rest;

import com.kaeser.platform.u20191e575.inventory.domain.model.commands.CreateSparepartCommand;
import com.kaeser.platform.u20191e575.inventory.domain.model.queries.GetSparepartByIdQuery;
import com.kaeser.platform.u20191e575.inventory.domain.services.SparepartCommandService;
import com.kaeser.platform.u20191e575.inventory.domain.services.SparepartQueryService;
import com.kaeser.platform.u20191e575.inventory.interfaces.rest.resources.CreateSparepartResource;
import com.kaeser.platform.u20191e575.inventory.interfaces.rest.resources.SparepartResource;
import com.kaeser.platform.u20191e575.inventory.interfaces.rest.transform.CreateEquipmentCommandFromResourceAssembler;
import com.kaeser.platform.u20191e575.inventory.interfaces.rest.transform.CreateSparepartCommandFromResourceAssembler;
import com.kaeser.platform.u20191e575.inventory.interfaces.rest.transform.SparepartResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for managing SpareParts.
 * @author Sebastian Andre Ramirez Mendez
 *
 */
@RestController
@RequestMapping(value = "/api/v1/spareparts", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "SpareParts", description = "SpareParts Management Endpoints")
public class SparepartsController {

    private final SparepartCommandService sparepartCommandService;
    private final SparepartQueryService sparepartQueryService;

    /**
     * Constructor for SparepartsController.
     *
     * @param sparepartCommandService The service for handling sparepart commands.
     * @param sparepartQueryService The service for handling sparepart queries.
     */
    public SparepartsController(SparepartCommandService sparepartCommandService, SparepartQueryService sparepartQueryService) {
        this.sparepartCommandService = sparepartCommandService;
        this.sparepartQueryService = sparepartQueryService;
    }

    /**
     * POST endpoint for creating a new Sparepart.
     *
     * @param createSparepartResource The resource containing information for creating a sparepart.
     * @return ResponseEntity with the created SparepartResource or a bad request response.
     */
    @PostMapping
    public ResponseEntity<SparepartResource> createSparepart(@RequestBody CreateSparepartResource createSparepartResource) {
        var createSparepartCommand = CreateSparepartCommandFromResourceAssembler.toCommandFromResource(createSparepartResource);
        var sparepartId = sparepartCommandService.handle(createSparepartCommand);

        if (sparepartId == 0L) return ResponseEntity.badRequest().build();

        var getSparepartByIdQuery = new GetSparepartByIdQuery(sparepartId);
        var sparepart = sparepartQueryService.handle(getSparepartByIdQuery);

        if (sparepart.isEmpty()) return ResponseEntity.badRequest().build();

        var sparepartResource = SparepartResourceFromEntityAssembler.toResourceFromEntity(sparepart.get());
        return new ResponseEntity<>(sparepartResource, HttpStatus.CREATED);
    }

    /**
     * GET endpoint for retrieving a Sparepart by its ID.
     *
     * @param sparepartId The ID of the sparepart to retrieve.
     * @return ResponseEntity with the retrieved SparepartResource or a bad request response.
     */
    @GetMapping("/{sparepartId}")
    public ResponseEntity<SparepartResource> getSparepartById(@PathVariable Long sparepartId) {
        var getSparepartByIdQuery = new GetSparepartByIdQuery(sparepartId);
        var sparepart = sparepartQueryService.handle(getSparepartByIdQuery);

        if (sparepart.isEmpty()) return ResponseEntity.badRequest().build();

        var sparepartResource = SparepartResourceFromEntityAssembler.toResourceFromEntity(sparepart.get());
        return ResponseEntity.ok(sparepartResource);
    }
}