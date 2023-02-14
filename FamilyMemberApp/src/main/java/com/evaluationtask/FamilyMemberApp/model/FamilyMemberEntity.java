package com.evaluationtask.FamilyMemberApp.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table(name="familymember")
public class FamilyMemberEntity {
    @Id
    private Long id;
    private Long familyid;
    private String firstname;
    private String lastname;
    private Long socialnumber;
}
