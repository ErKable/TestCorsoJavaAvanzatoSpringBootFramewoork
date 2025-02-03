package it.cgmconsulting.capriello.repository;

import it.cgmconsulting.capriello.entity.Film;
import it.cgmconsulting.capriello.entity.Inventory;
import it.cgmconsulting.capriello.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findByFilmIdAndStoreId(Film filmId, Store storeId);
}
