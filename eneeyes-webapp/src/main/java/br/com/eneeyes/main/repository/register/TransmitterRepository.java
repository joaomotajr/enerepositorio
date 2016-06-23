package br.com.eneeyes.main.repository.register;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eneeyes.main.model.register.Transmitter;

public interface TransmitterRepository  extends JpaRepository<Transmitter, Long> {

}
