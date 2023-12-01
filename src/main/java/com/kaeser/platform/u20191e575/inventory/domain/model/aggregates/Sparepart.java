package com.kaeser.platform.u20191e575.inventory.domain.model.aggregates;

import com.kaeser.platform.u20191e575.inventory.domain.model.entities.Equipment;
import com.kaeser.platform.u20191e575.inventory.domain.model.valueobjects.MaterialSerialNumberRecord;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;


@EntityListeners(AuditingEntityListener.class)
@Entity
public class Sparepart extends AbstractAggregateRoot<Sparepart> {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private final MaterialSerialNumberRecord serialNumber;


    @Getter
    private Integer supplierId;


    @Getter
    private String model;


    @Getter
    private String supplierProvidedSerialNumber;


    @Getter
    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;


    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    public Sparepart() {this.serialNumber = new MaterialSerialNumberRecord();}

    public Sparepart( Integer supplierId, String model, String supplierProvidedSerialNumber, Equipment equipment){
        this();
        this.supplierId = supplierId;
        this.model = model;
        this.supplierProvidedSerialNumber = supplierProvidedSerialNumber;
        this.equipment = equipment;

    }

    public String getMaterialSerialNumberString(){
        return serialNumber.serialNumber();
    }

    public MaterialSerialNumberRecord getMaterialSerialNumber(){ return serialNumber;}


}
