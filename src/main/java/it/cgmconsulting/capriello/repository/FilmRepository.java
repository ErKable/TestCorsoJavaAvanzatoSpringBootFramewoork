package it.cgmconsulting.capriello.repository;

import it.cgmconsulting.capriello.dto.response.FilmMaxRentResponse;
import it.cgmconsulting.capriello.dto.response.FilmRentableResponse;
import it.cgmconsulting.capriello.dto.response.FilmResponse;
import it.cgmconsulting.capriello.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface FilmRepository extends JpaRepository<Film, Long> {
    @Query("SELECT new it.cgmconsulting.capriello.dto.response.FilmResponse(" +
            "f.filmId, " +
            "f.title, " +
            "f.description, " +
            "f.releaseYear, " +
            "l.languageName) FROM Film f, Language l " +
            "WHERE l.languageId = :languageId and f.languageId.languageId = l.languageId")
    List<FilmResponse> findFilmsByLanguage(long languageId);

    @Query("SELECT new it.cgmconsulting.capriello.dto.response.FilmMaxRentResponse(" +
            "f.filmId, " +
            "f.title, " +
            "COUNT(r.rentalId.rentalDate) as rented) " +
            "FROM Film f, Inventory i, Rental r " +
            "WHERE f.filmId = i.filmId.filmId AND i.inventoryId = r.rentalId.inventoryId.inventoryId " +
            "GROUP BY f.title, f.filmId " +
            "HAVING COUNT(r.rentalId.rentalDate) = (" +
                            "SELECT COUNT(r.rentalId.rentalDate) as rented " +
                            "FROM Film f, Inventory i, Rental r " +
                            "WHERE f.filmId = i.filmId.filmId AND i.inventoryId = r.rentalId.inventoryId.inventoryId " +
                              "GROUP BY f.title, f.filmId " +
                            "ORDER BY rented DESC " +
                            "LIMIT 1 " +
            ") ORDER BY rented DESC"   )
    List<FilmMaxRentResponse> findFilmWithMaxNumberOfRent();
    @Query("SELECT new it.cgmconsulting.capriello.dto.response.FilmRentableResponse(" +
            "f.title, " +
            "s.storeName, " +
            "COUNT(f.title) as totalStoreCopy, " +
            "COUNT(CASE WHEN r.rentalReturn IS NOT NULL THEN 1 END) as rentableCopy" +
            ") FROM Rental r, Inventory i, Film f, Store s " +
            "WHERE f.title = :title AND f.filmId = i.filmId.filmId AND " +
            "i.inventoryId = r.rentalId.inventoryId.inventoryId AND i.storeId.storeId = s.storeId " +
            "GROUP BY f.title, s.storeName")
    List<FilmRentableResponse> findRentableFilms(String title);

  /*  @Query("SELECT new it.cgmconsulting.capriello.dto.response.FilmRentableResponse(" +
            "f.title, " +
            "s.storeName, " +
            "COUNT(i.filmId) as totalStoreCopy) FROM Inventory i, Store s, Film f " +
            "WHERE f.title = :title and f.filmId = i.filmId.filmId and i.storeId.storeId = s.storeId " +
            "GROUP BY s.storeName, f.title")*/
    @Query("SELECT new it.cgmconsulting.capriello.dto.response.FilmRentableResponse(" +
            "f.title, " +
            "s.storeName, " +
            "COUNT(DISTINCT i.inventoryId) as totalStoreCopy, " +
            "(COUNT(DISTINCT i.inventoryId) - COUNT(CASE WHEN r.rentalReturn IS NULL THEN 1 END)) as rentableCopy " +
            ") FROM Film f, Inventory i, Store s, Rental r " +
            "WHERE f.title = :title and f.filmId = i.filmId.filmId and i.storeId.storeId = s.storeId and i.inventoryId = r.rentalId.inventoryId.inventoryId " +
            "GROUP BY f.title, s.storeName")
    List<FilmRentableResponse> getStoreCopies(String title);

    @Query("SELECT new it.cgmconsulting.capriello.dto.response.FilmRentableResponse(" +
            "f.title, " +
            "s.storeName, " +
            "COUNT(i.filmId) as totalStoreCopy) FROM Inventory i, Store s, Film f " +
            "WHERE f.title = :title and f.filmId = i.filmId.filmId and i.storeId.storeId = s.storeId " +
            "AND s.storeName = :storeName " +
            "GROUP BY s.storeName, f.title")
    FilmRentableResponse getStoreCopies(String title, String storeName);
    @Query("SELECT COUNT(CASE WHEN r.rentalReturn IS NULL THEN 1 END) as rentedCopies " +
            "FROM Inventory i, Store s, Film f, Rental r " +
            "WHERE f.title = :title AND f.filmId = i.filmId.filmId and i.storeId.storeId = s.storeId AND " +
            "i.inventoryId = r.rentalId.inventoryId.inventoryId and s.storeName = :storeName")
    long getRentedCopies(String title, String storeName);

  @Query("SELECT new it.cgmconsulting.capriello.dto.response.FilmRentableResponse(" +
          "f.title, " +
          "s.storeName, " +
          "COUNT(DISTINCT i.inventoryId) as totalStoreCopy, " +
          "(COUNT(DISTINCT i.inventoryId) - COUNT(CASE WHEN r.rentalReturn IS NULL THEN 1 END)) as rentableCopy " +
          ") FROM Film f, Inventory i, Store s, Rental r " +
          "WHERE f.title = :title and f.filmId = i.filmId.filmId and i.storeId.storeId = s.storeId " +
          "and i.inventoryId = r.rentalId.inventoryId.inventoryId and s.storeName = :storeName " +
          "GROUP BY f.title, s.storeName")
    FilmRentableResponse findRentableCopies(String title, String storeName);


    @Query("SELECT new it.cgmconsulting.capriello.dto.response.FilmResponse(" +
            "f.filmId, " +
            "f.title, " +
            "f.description, " +
            "f.releaseYear, " +
            "l.languageName) FROM Film f, FilmStaff fs, Language l " +
            "WHERE f.filmId = fs.filmStaffId.filmId.filmId AND f.languageId.languageId = l.languageId AND " +
                    "fs.filmStaffId.roleId.roleId IN (SELECT r.roleId " +
                                  "FROM Role r " +
                                  "WHERE r.roleName = 'ACTOR')" +
            "AND fs.filmStaffId.staffId.staffId IN(:staffIds) " +
            "GROUP BY f.filmId, f.title, f.description, f.releaseYear, l.languageName " +
            "HAVING COUNT(DISTINCT fs.filmStaffId.staffId.staffId) = (SELECT COUNT(DISTINCT fs2.filmStaffId.staffId.staffId) " +
                                                  "FROM FilmStaff fs2 " +
                                                  "WHERE fs2.filmStaffId.staffId.staffId IN (:staffIds)) " +
            "ORDER BY f.title ")
    List<FilmResponse> findFilmsByActors(Set<Long> staffIds);



    Optional<Film> findByTitle(String title);
}
