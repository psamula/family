package com.evaluationtask.FamilyMemberApp.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table(name = "familymember")
public class FamilyMemberEntity {
    @Id
    private Long id;
    private Long familyId;
    private String firstName;
    private String lastName;
    private Long socialNumber;
}
