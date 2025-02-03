package it.cgmconsulting.capriello.dto.request;

import it.cgmconsulting.capriello.entity.Genre;
import it.cgmconsulting.capriello.entity.Language;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record FilmRequest(
        @NotBlank String title,
        @Size(min = 5, max = 255) String description,
        @NotNull short releaseYear,
        @NotNull Language language,
        @NotNull Genre genre
        ) {
}
