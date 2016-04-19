package br.com.eneeyes.model.repository.vendas;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eneeyes.archetype.model.vendas.DetalheVenda;
import br.com.eneeyes.archetype.model.vendas.DetalheVendaId;

/**
 * Created by Bruno on 02/03/15.
 */
public interface DetalheVendaRepository  extends JpaRepository<DetalheVenda, DetalheVendaId> {
    
}
