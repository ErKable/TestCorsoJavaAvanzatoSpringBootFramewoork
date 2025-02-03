package it.cgmconsulting.capriello.service;

import it.cgmconsulting.capriello.entity.Customer;
import it.cgmconsulting.capriello.entity.Inventory;
import it.cgmconsulting.capriello.entity.RentalId;
import it.cgmconsulting.capriello.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import it.cgmconsulting.capriello.exception.ResourceNotFoundException;
import it.cgmconsulting.capriello.entity.Rental;

@Service
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepository;

    public boolean existsById(RentalId rentalId){
        return rentalRepository.existsById(rentalId);
    }

    public boolean existsByCustomerIdInventoryIdAndRentalReturnIsNull(Customer customer, Inventory inventory){
        return rentalRepository.existsByRentalIdCustomerIdAndRentalIdInventoryIdAndRentalReturnIsNull(customer, inventory);
    }
    public Rental findById(RentalId rentalId){
        return rentalRepository.findById(rentalId)
                .orElseThrow(() -> new ResourceNotFoundException("Rental", "id", rentalId));
    }

    public void save(Rental rental){
        rentalRepository.save(rental);
    }

    public Rental findByCustomerIdInventoryIdAndRentalReturnIsNull(Customer customer, Inventory inventory) {
        return rentalRepository.findByRentalIdCustomerIdAndRentalIdInventoryIdAndRentalReturnIsNull(customer, inventory)
                .orElseThrow();
    }
}
