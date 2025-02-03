package it.cgmconsulting.capriello.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilmMaxRentResponse {
    private long filmId;
    private String title;
    private long rented;
}
