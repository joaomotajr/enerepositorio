package br.com.eneeyes.archetype.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.eneeyes.archetype.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	

}