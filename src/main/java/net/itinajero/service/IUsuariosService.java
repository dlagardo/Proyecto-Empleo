package net.itinajero.service;

import java.util.List;

import net.itinajero.model.Usuario;





public interface IUsuariosService{
	
	void guardar(Usuario usuario);
	void eliminar(int idUsuario);
	List<Usuario>buscarTodos();
	

}
