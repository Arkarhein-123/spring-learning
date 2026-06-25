package org.example._4springdatajparevison.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity

//@PrimaryKeyJoinColumn(name = "file_id") // => only for JOINED
//@DiscriminatorValue("FILE") // => Only for Single Table
public class File extends Resource{
    private String type;
}
