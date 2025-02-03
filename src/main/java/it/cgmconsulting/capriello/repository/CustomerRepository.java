package it.cgmconsulting.capriello.repository;

import it.cgmconsulting.capriello.dto.response.FilmRentResponse;
import it.cgmconsulting.capriello.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT DISTINCT new it.cgmconsulting.capriello.dto.response.FilmRentResponse(" +
            "f.filmId, " +
            "f.title, " +
            "s.storeName) FROM Customer c, Rental r, Inventory i, Film f, Store s " +
            "WHERE c.customerId = :customerId AND r.rentalId.customerId.customerId = c.customerId AND " +
            "i.inventoryId = r.rentalId.inventoryId.inventoryId AND i.storeId.storeId = s.storeId and i.filmId.filmId = f.filmId")
    List<FilmRentResponse> findAllFilmsRentByOneCustomer(long customerId);
}
