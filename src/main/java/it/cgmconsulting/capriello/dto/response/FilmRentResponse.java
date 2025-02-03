package it.cgmconsulting.capriello.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilmRentResponse {
    private long filmId;
    private String title;
    private String storeName;
}
