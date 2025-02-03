package it.cgmconsulting.capriello.service;

import it.cgmconsulting.capriello.entity.Film;
import it.cgmconsulting.capriello.entity.Inventory;
import it.cgmconsulting.capriello.entity.Store;
import it.cgmconsulting.capriello.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    public void save(Inventory inventory){
        inventoryRepository.save(inventory);
    }

    public List<Inventory> findByFilmIdAndStoreId(Film filmId, Store storeId) {
        return inventoryRepository.findByFilmIdAndStoreId(filmId, storeId);
    }
}
