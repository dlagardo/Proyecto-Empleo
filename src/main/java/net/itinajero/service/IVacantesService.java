package net.itinajero.service;
import java.util.List;

import org.springframework.data.domain.Example;

import net.itinajero.model.Vacante;

public interface IVacantesService {
	
	List<Vacante>buscarTodas();
	Vacante buscaporId(Integer idVacante);
	void guardar(Vacante vacante);
	List<Vacante>buscarDestacadas();
	void eliminar(int idVacante);
	List<Vacante>buscarByExample(Example<Vacante>example);
}
