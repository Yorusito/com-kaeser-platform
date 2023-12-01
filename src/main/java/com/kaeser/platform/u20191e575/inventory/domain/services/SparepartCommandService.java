package com.kaeser.platform.u20191e575.inventory.domain.services;


import com.kaeser.platform.u20191e575.inventory.domain.model.commands.CreateSparepartCommand;

public interface SparepartCommandService {

    Long handle(CreateSparepartCommand command);
}
