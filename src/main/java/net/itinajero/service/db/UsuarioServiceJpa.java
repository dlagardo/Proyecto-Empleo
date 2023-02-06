package net.itinajero.service.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import net.itinajero.model.Usuario;
import net.itinajero.repository.CategoriaRepositorio;
import net.itinajero.repository.UsuarioRepositorio;
import net.itinajero.service.IUsuariosService;

@Service
@Primary
public class UsuarioServiceJpa implements IUsuariosService {
	@Autowired
	private UsuarioRepositorio usuariosRepo;


	public void guardar(Usuario usuario) {
		usuariosRepo.save(usuario);
	}

	public void eliminar(int idUsuario) {
		usuariosRepo.deleteById(idUsuario);
	}

	public List<Usuario> buscarTodos() {
		return usuariosRepo.findAll();
	}

	@Override
	public Usuario buscarPorUsername(String username) {
		
		return usuariosRepo.findByUsername(username);
	}

	

	
	

}
