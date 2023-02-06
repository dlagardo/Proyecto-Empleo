package net.itinajero.repository;

import java.util.List;


import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import net.itinajero.model.Usuario;


public interface UsuarioRepositorio extends JpaRepositoryImplementation<Usuario, Integer> {

	Usuario findByUsername(String username);
}
