package com.kaeser.platform.u20191e575.inventory.domain.model.entities;

import com.kaeser.platform.u20191e575.inventory.domain.model.valueobjects.EEquipmentType;
import com.kaeser.platform.u20191e575.inventory.domain.model.valueobjects.MaterialSerialNumberRecord;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Equipment extends AbstractAggregateRoot<Equipment> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Embedded
    private final MaterialSerialNumberRecord serialNumber;

    @NotNull
    @NotBlank
    private String model;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private EEquipmentType equipmentType;


    public Equipment() {this.serialNumber = new MaterialSerialNumberRecord();}

    public Equipment(String model, EEquipmentType equipmentType){
        this();
        this.model = model;
        this.equipmentType = equipmentType;
    }

    public static EEquipmentType toRoleFromName(String name) { return EEquipmentType.valueOf(name);}

    public String getEquipmentTypeName() { return equipmentType.name();}

    public String getMaterialSerialNumberString(){
        return serialNumber.serialNumber();
    }

    public MaterialSerialNumberRecord getMaterialSerialNumber(){ return serialNumber;}

}
