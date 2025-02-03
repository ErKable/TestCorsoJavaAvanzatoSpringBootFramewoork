package it.cgmconsulting.capriello.service;

import it.cgmconsulting.capriello.dto.request.FilmRequest;
import it.cgmconsulting.capriello.dto.response.FilmMaxRentResponse;
import it.cgmconsulting.capriello.dto.response.FilmRentableResponse;
import it.cgmconsulting.capriello.dto.response.FilmResponse;
import it.cgmconsulting.capriello.entity.Film;
import it.cgmconsulting.capriello.exception.ResourceNotFoundException;
import it.cgmconsulting.capriello.repository.FilmRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FilmService {
    private final FilmRepository filmRepository;
    private final GenreService genreService;
    private final LanguageService languageService;
    public String updateFilm(long filmId, FilmRequest request) {
        if(!genreService.existsBy(request.genre()))
            return "No such genre";
        if(!languageService.existsBy(request.language()))
            return "No such language";
        Film film = findById(filmId);
        film.setTitle(request.title().toUpperCase());
        film.setDescription(request.description());
        film.setReleaseYear(request.releaseYear());
        film.setGenreId(request.genre());
        film.setLanguageId(request.language());
        filmRepository.save(film);
        return "Film information updated sucessfully";
    }

    public List<FilmResponse> findFilmsByLanguage(long languageId){
        return filmRepository.findFilmsByLanguage(languageId);
    }

    public List<FilmMaxRentResponse> findFilmWithMaxNumberOfRent(){
        return filmRepository.findFilmWithMaxNumberOfRent();
    }

    public List<FilmRentableResponse> findRentableFilms(String title){
        title = title.toUpperCase();
        //la creo senza rentable copies
        return  filmRepository.getStoreCopies(title);
        //aggiungo rentable copies
        /*for(FilmRentableResponse r : response){
            r.setRentableCopy(
                    r.getTotalStoreCopy() - filmRepository.getRentedCopies(title, r.getStoreName())
            );
        }*/
        //return response;
    }

    public FilmRentableResponse findRentableCopies(String title, String storeName){
        title = title.toUpperCase();
        storeName = storeName.toUpperCase();
       
       
        return filmRepository.findRentableCopies(title, storeName);
    }

    public List<FilmResponse> findFilmsByActors(Set<Long> actorIds){
        return filmRepository.findFilmsByActors(actorIds);
    }

    protected Film findById(long filmId){
        return filmRepository.findById(filmId)
                .orElseThrow(() -> new ResourceNotFoundException("Film", "id", filmId));
    }

    public Film findByName(String title){
        return filmRepository.findByTitle(title)
                .orElseThrow(() -> new ResourceNotFoundException("Film", "title", title));
    }

    public boolean existsById(long filmId){
        return filmRepository.existsById(filmId);
    }
}
