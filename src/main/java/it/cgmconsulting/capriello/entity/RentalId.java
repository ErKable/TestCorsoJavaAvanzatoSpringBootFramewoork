package it.cgmconsulting.capriello.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentalId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customerId;
    @ManyToOne
    @JoinColumn(name = "inventory_id", nullable = false)
    private Inventory inventoryId;
    @Column(nullable = false)
    private LocalDateTime rentalDate;
}
