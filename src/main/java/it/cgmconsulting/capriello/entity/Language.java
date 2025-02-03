package it.cgmconsulting.capriello.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //auto increment
    private long languageId;
    @Column(nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private String languageName;

    Language(String languageName){
        this.languageName = languageName;
    }
}
