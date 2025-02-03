package it.cgmconsulting.capriello.service;

import it.cgmconsulting.capriello.dto.request.RentalRequest;
import it.cgmconsulting.capriello.dto.response.FilmRentResponse;
import it.cgmconsulting.capriello.dto.response.FilmRentableResponse;
import it.cgmconsulting.capriello.entity.*;
import it.cgmconsulting.capriello.exception.ResourceNotFoundException;
import it.cgmconsulting.capriello.repository.CustomerRepository;
import it.cgmconsulting.capriello.repository.FilmRepository;
import it.cgmconsulting.capriello.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final RentalService rentalService;
    private final FilmService filmService;
    private final StoreService storeService;
    private final InventoryService inventoryService;
    public List<FilmRentResponse> findAllFilmsRentByOneCustomer(long storeId){
        return customerRepository.findAllFilmsRentByOneCustomer(storeId);
    }


    public String addUpdateRental(RentalRequest rentalRequest) {
        String msg = "";
        Customer customer = findById(rentalRequest.customerId());
        Film film = filmService.findByName(rentalRequest.filmTitle().toUpperCase());
        Store store = storeService.findByStoreName(rentalRequest.storeName().toUpperCase());
        Inventory inventory = inventoryService.findByFilmIdAndStoreId(film, store).getFirst();
        //controllo se esiste una rental dello stesso film con l'utente e data a null (non ha consegnato)
        if(rentalService.existsByCustomerIdInventoryIdAndRentalReturnIsNull(customer, inventory)) {
            //se esiste già vuol dire che devo upparlo
            Rental rental = rentalService.findByCustomerIdInventoryIdAndRentalReturnIsNull(customer, inventory);
            if (!rental.getRentalId().getRentalDate().equals(rentalRequest.startDate())) {
                //useless??
                rental.setRentalReturn(rentalRequest.startDate());
                rental.getRentalId().setRentalDate(rentalRequest.startDate());
                rentalService.save(rental);
                msg = "Rental for film " + rentalRequest.filmTitle() + " updated";
            } else
                //eccezione db duplicate entry
                msg = "Old rental date and new Rental date are equals";
        }else{
            //non esiste quindi è un rental nuovo
            //verifico se il film è disponibile
            FilmRentableResponse response = filmService.findRentableCopies(rentalRequest.filmTitle(), rentalRequest.storeName());
            if(response.getRentableCopy() > 0){
                //è disponibile
                RentalId rentalId = new RentalId(customer, inventory, rentalRequest.startDate());
                Rental rental = new Rental(rentalId);
                rentalService.save(rental);
                msg = "Rented film "+rentalRequest.filmTitle()+" from "+rentalRequest.startDate();
            } else
                msg =  "No copies for rent";
        }
        return msg;
    }

    public Customer findById(long customerId){
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));
    }
}
