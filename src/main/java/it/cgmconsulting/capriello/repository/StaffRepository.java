package it.cgmconsulting.capriello.repository;

import it.cgmconsulting.capriello.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Long> {
}
