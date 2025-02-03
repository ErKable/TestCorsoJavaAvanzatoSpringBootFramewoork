package it.cgmconsulting.capriello.controller;

import it.cgmconsulting.capriello.dto.ResponseHandler;
import it.cgmconsulting.capriello.dto.response.CustomerStoreResponse;
import it.cgmconsulting.capriello.service.StoreService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;
    @PostMapping("/add-films-to-store/{storeId}/{filmId}")
    public ResponseEntity<?> addFilmToStore(@PathVariable @Min(1) long storeId,
                                            @PathVariable @Min(1) long filmId){
        String msg = storeService.addFilmToStore(storeId, filmId);
        if(msg.startsWith("No"))
            return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, msg);
        return ResponseHandler.generateResponse(HttpStatus.OK, msg);
    }

    @GetMapping("/count-customers-by-store/{storeName}")
    public ResponseEntity<?> countCustomersByStoreName(@PathVariable @NotBlank String storeName){
        CustomerStoreResponse response = storeService.countCustomersByStoreName(storeName);
        if(response == null)
            return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, "No such store");
        return ResponseHandler.generateResponse(HttpStatus.OK, response);
    }

    @GetMapping("/count-rentals-in-date-range-by-store/{storeId}")
    public ResponseEntity<?> countRentalsInDateRangeByStore(@RequestParam LocalDate start,
                                                            @RequestParam LocalDate end,
                                                            @PathVariable @Min(1) long storeId){
        return ResponseHandler.generateResponse(HttpStatus.OK, storeService.countRentalsInDateRangeByStore(storeId, start, end));
    }


}
