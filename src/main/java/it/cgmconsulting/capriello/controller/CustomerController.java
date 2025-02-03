package it.cgmconsulting.capriello.controller;

import it.cgmconsulting.capriello.dto.ResponseHandler;
import it.cgmconsulting.capriello.dto.request.RentalRequest;
import it.cgmconsulting.capriello.dto.response.FilmRentResponse;
import it.cgmconsulting.capriello.entity.Rental;
import it.cgmconsulting.capriello.service.CustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
@Validated
public class CustomerController {
    private final CustomerService customerService;
    @GetMapping("/find-all-films-rent-by-one-customer/{customerId}")
    public ResponseEntity<?> findAllFilmsRentByOneCustomer(@PathVariable @Min(1) long customerId){
        List<FilmRentResponse> films = customerService.findAllFilmsRentByOneCustomer(customerId);
        if(films.isEmpty())
            return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, "No films to display for users id "+customerId);
        return ResponseHandler.generateResponse(HttpStatus.OK, films);
    }

    @PutMapping("/add-update-rental")
    public ResponseEntity<?> addUpdateRental(@RequestBody @Valid RentalRequest rentalRequest){
        return ResponseHandler.generateResponse(HttpStatus.OK, customerService.addUpdateRental(rentalRequest));
    }
}
