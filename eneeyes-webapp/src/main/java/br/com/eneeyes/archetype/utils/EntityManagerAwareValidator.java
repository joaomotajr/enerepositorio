package br.com.eneeyes.archetype.utils;

import javax.persistence.EntityManager;

public interface EntityManagerAwareValidator {  
    void setEntityManager(EntityManager entityManager); 
}