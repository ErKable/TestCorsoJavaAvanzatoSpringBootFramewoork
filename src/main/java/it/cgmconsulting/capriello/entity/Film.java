package it.cgmconsulting.capriello.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //auto increment
    private long filmId;
    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private short releaseYear;
    @ManyToOne
    @JoinColumn(name = "languageId", nullable = false)
    private Language languageId;
    @ManyToOne
    @JoinColumn(name = "genreId", nullable = false)
    private Genre genreId;
}
