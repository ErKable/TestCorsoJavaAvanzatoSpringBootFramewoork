package it.cgmconsulting.capriello.repository;

import it.cgmconsulting.capriello.entity.FilmStaff;
import it.cgmconsulting.capriello.entity.FilmStaffId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmStaffRepository extends JpaRepository<FilmStaff, FilmStaffId> {
}
