package it.cgmconsulting.capriello.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilmResponse{
    private long film_id;
    private String title;
    private String description;
    private short release_year;
    private String language_name;
}
