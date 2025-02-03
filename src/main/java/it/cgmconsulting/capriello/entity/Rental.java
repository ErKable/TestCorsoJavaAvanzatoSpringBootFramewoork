package it.cgmconsulting.capriello.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Rental {

    @EmbeddedId
    private RentalId rentalId;
    private LocalDateTime rentalReturn;

    public Rental(RentalId rentalId){
        this.rentalId = rentalId;
    }
}
