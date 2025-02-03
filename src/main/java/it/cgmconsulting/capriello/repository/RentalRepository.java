package it.cgmconsulting.capriello.repository;

import it.cgmconsulting.capriello.entity.Customer;
import it.cgmconsulting.capriello.entity.Inventory;
import it.cgmconsulting.capriello.entity.Rental;
import it.cgmconsulting.capriello.entity.RentalId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RentalRepository extends JpaRepository<Rental, RentalId> {

    boolean existsByRentalIdCustomerIdAndRentalIdInventoryIdAndRentalReturnIsNull(Customer customer, Inventory inventory);

    Optional<Rental> findByRentalIdCustomerIdAndRentalIdInventoryIdAndRentalReturnIsNull(Customer customer, Inventory inventory);
}
