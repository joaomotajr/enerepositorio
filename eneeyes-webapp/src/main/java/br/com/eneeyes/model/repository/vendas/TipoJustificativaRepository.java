package br.com.eneeyes.model.repository.vendas;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eneeyes.archetype.model.TipoJustificativa;

/**
 * Created by Bruno on 02/03/15.
 */
public interface TipoJustificativaRepository  extends JpaRepository<TipoJustificativa, Long> {
    
}
