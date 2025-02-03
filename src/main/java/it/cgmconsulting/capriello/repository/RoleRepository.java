package it.cgmconsulting.capriello.repository;

import it.cgmconsulting.capriello.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
