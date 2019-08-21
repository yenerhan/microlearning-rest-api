package com.yener.microlearning.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity(name = "roles")
public class Role implements Serializable {


    private static final long serialVersionUID = -6517820206978129972L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "SEQ_ROLE", allocationSize = 1)
    private Long roleId;

    private String roleName;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
