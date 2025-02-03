package it.cgmconsulting.capriello.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //auto increment
    private long roleId;
    @Column(length = 30, nullable = false)
    private String roleName;

    Role(String roleName){
        this.roleName = roleName;
    }
}
