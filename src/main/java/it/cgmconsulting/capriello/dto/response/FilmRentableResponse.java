package it.cgmconsulting.capriello.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilmRentableResponse {
    private String title;
    private String storeName;
    private long totalStoreCopy;
    private long rentableCopy;

    public FilmRentableResponse(String title, String storeName, long totalStoreCopy) {
        this.title = title;
        this.storeName = storeName;
        this.totalStoreCopy = totalStoreCopy;
    }
}
