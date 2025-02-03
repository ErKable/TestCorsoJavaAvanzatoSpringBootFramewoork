package it.cgmconsulting.capriello.service;

import it.cgmconsulting.capriello.entity.Genre;
import it.cgmconsulting.capriello.exception.ResourceNotFoundException;
import it.cgmconsulting.capriello.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;

    public boolean existsBy(Genre genre){
        return genreRepository.existsByGenreIdAndGenreName(genre.getGenreId(), genre.getGenreName());
    }
}
