package it.cgmconsulting.capriello.controller;

import it.cgmconsulting.capriello.dto.ResponseHandler;
import it.cgmconsulting.capriello.dto.request.FilmRequest;
import it.cgmconsulting.capriello.dto.response.FilmResponse;
import it.cgmconsulting.capriello.entity.Film;
import it.cgmconsulting.capriello.repository.FilmRepository;
import it.cgmconsulting.capriello.service.FilmService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/film")
@RequiredArgsConstructor
@Validated
public class FilmController {

    private final FilmService filmService;

    @PutMapping("/update-film/{filmId}")
    public ResponseEntity<?> updateFilm(@PathVariable long filmId, @Valid @RequestBody FilmRequest request){
        String msg = filmService.updateFilm(filmId, request);
        if(msg.startsWith("No"))
            return ResponseHandler.generateErrorResponse(HttpStatus.BAD_REQUEST, msg);
        return ResponseHandler.generateResponse(HttpStatus.OK, filmService.updateFilm(filmId, request));
    }

    @GetMapping("/find-films-by-language/{languageId}")
    public ResponseEntity<?> findFilmsByLanguage(@PathVariable long languageId){
        List<FilmResponse> responses = filmService.findFilmsByLanguage(languageId);
        if(responses.isEmpty())
            return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND);
        return ResponseHandler.generateResponse(HttpStatus.OK, responses);
    }

    @GetMapping("/find-film-with-max-number-of-rent")
    public ResponseEntity<?> findFilmWithMaxNumberOfRent(){
        return ResponseHandler.generateResponse(HttpStatus.OK, filmService.findFilmWithMaxNumberOfRent());
    }

    @GetMapping("/find-rentable-films")
    public ResponseEntity<?> findRentableFilms(@RequestParam @NotBlank String title){
        return ResponseHandler.generateResponse(HttpStatus.OK, filmService.findRentableFilms(title));
    }

    @GetMapping("/find-films-by-actors")
    public ResponseEntity<?> findFilmsByActors(@RequestParam Set<Long> actorIds){
        List<FilmResponse> responses = filmService.findFilmsByActors(actorIds);
        if(responses.isEmpty())
            return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, "No films with given actors");
        return ResponseHandler.generateResponse(HttpStatus.OK, responses);
    }
}
