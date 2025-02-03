package it.cgmconsulting.capriello.repository;

import it.cgmconsulting.capriello.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    boolean existsByGenreIdAndGenreName(long genreId, String genreName);
}
