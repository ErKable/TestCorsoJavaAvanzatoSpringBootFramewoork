package it.cgmconsulting.capriello.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //auto increment
    private long inventoryId;
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store storeId;
    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film filmId;

    public Inventory(Store store, Film film){
        this.storeId = store;
        this.filmId = film;
    }
}
