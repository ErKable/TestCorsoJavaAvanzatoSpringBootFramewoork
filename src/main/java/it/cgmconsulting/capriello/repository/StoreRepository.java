package it.cgmconsulting.capriello.repository;

import it.cgmconsulting.capriello.dto.response.CustomerStoreResponse;
import it.cgmconsulting.capriello.dto.response.FilmRentResponse;
import it.cgmconsulting.capriello.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    @Query("SELECT new it.cgmconsulting.capriello.dto.response.CustomerStoreResponse(" +
            "s.storeName, " +
            "COUNT(DISTINCT(r.rentalId.customerId)) as customers) FROM Rental r, Inventory i, Store s " +
            "WHERE s.storeId = i.storeId.storeId AND i.inventoryId = r.rentalId.inventoryId.inventoryId AND " +
            "r.rentalId.rentalDate IS NOT NULL " +
            "AND s.storeName LIKE UPPER(:storeName) " +
            "GROUP BY s.storeName")
    CustomerStoreResponse countCustomersByStoreName(String storeName);

    @Query(value = "SELECT COUNT(r.rental_date) " +
            "FROM inventory i, rental r " +
            "WHERE i.store_id = :storeId AND i.inventory_id = r.inventory_id AND " +
            "r.rental_date between :start AND :end", nativeQuery = true)
    long countRentalsInDateRangeByStore(LocalDate start, LocalDate end, long storeId);

    boolean existsByStoreName(String storeName);

    Optional<Store> findByStoreName(String storeName);
}
