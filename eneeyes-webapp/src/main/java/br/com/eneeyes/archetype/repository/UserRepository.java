package br.com.eneeyes.archetype.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.eneeyes.archetype.model.User;;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("select u from User u where u.login = ?1")
	public User findByLogin(String login);
	
	@Query("select u from User u where u.company.uid = ?1")
	public List<User> findByCompanyId(Long companyId);

}