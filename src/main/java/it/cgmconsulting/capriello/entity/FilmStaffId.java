package it.cgmconsulting.capriello.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FilmStaffId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false)
    @EqualsAndHashCode.Include
    private Film filmId;
    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false)
    @EqualsAndHashCode.Include
    private Staff staffId;
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    @EqualsAndHashCode.Include
    private Role roleId;
  }
