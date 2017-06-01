package br.com.eneeyes.archetype.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.eneeyes.archetype.model.User;;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("select u from User u where u.login = ?1")
	public User findByLogin(String login);
	
	public User findById(Long id);

}