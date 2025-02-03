package it.cgmconsulting.capriello.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //auto increment
    private long genreId;
    @Column(nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private String genreName;

    Genre(String genreName){
        this.genreName = genreName;
    }
}
