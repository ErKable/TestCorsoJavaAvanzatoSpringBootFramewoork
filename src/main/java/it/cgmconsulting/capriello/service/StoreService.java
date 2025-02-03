package it.cgmconsulting.capriello.service;

import it.cgmconsulting.capriello.dto.response.CustomerStoreResponse;
import it.cgmconsulting.capriello.dto.response.FilmRentResponse;
import it.cgmconsulting.capriello.entity.Film;
import it.cgmconsulting.capriello.entity.Inventory;
import it.cgmconsulting.capriello.entity.Store;
import it.cgmconsulting.capriello.exception.ResourceNotFoundException;
import it.cgmconsulting.capriello.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final FilmService filmService;
    private final InventoryService inventoryService;
    public String addFilmToStore(long storeId, long filmId){
        if(!filmService.existsById(filmId))
            return "No such language";
        if(!existsById(storeId))
            return "No such store";
        Film film = filmService.findById(filmId);
        Store store = findById(storeId);
        inventoryService.save(new Inventory(store, film));
        return "Film "+film.getTitle()+" added to store "+store.getStoreName();
    }

    public CustomerStoreResponse countCustomersByStoreName(String storeName){
        storeName = storeName.toUpperCase();
        Store store = findByStoreName(storeName);
        if(store == null)
            return null;
        return storeRepository.countCustomersByStoreName(storeName);
    }

    public long countRentalsInDateRangeByStore(long storeId, LocalDate start, LocalDate end){
        return storeRepository.countRentalsInDateRangeByStore(start, end, storeId);
    }

    protected Store findById(long storeId){
        return storeRepository.findById(storeId)
                .orElseThrow(() -> new ResourceNotFoundException("Store", "id", storeId));
    }
    private boolean existsById(long storeId){
        return storeRepository.existsById(storeId);
    }

    public Store findByStoreName(String storeName){
        return storeRepository.findByStoreName(storeName.toUpperCase())
                .orElseThrow(() -> new ResourceNotFoundException("Store", "name ", storeName));
    }
}
