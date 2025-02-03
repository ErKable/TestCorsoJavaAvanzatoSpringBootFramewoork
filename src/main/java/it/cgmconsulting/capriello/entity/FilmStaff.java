package it.cgmconsulting.capriello.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class FilmStaff {
    @EmbeddedId
    @EqualsAndHashCode.Include
    private FilmStaffId filmStaffId;
}
