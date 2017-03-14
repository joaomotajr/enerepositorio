package br.com.eneeyes.archetype.model.validator;

import javax.persistence.EntityManager;

public interface EntityManagerAwareValidator {  
    void setEntityManager(EntityManager entityManager); 
}