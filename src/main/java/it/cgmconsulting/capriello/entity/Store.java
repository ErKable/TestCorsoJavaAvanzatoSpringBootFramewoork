package it.cgmconsulting.capriello.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //auto increment
    @EqualsAndHashCode.Include
    private long storeId;
    @Column(nullable = false, length = 60)
    private String storeName;
}
