package it.cgmconsulting.capriello.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record RentalRequest(
        @NotBlank String filmTitle,
        @NotBlank String storeName,
        @Min(1) long customerId,
        @NotNull LocalDateTime startDate

) {}
