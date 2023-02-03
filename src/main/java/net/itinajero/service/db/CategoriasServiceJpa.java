package net.itinajero.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.itinajero.model.Categoria;
import net.itinajero.model.Vacante;
import net.itinajero.repository.CategoriaRepositorio;
import net.itinajero.service.ICategoriasService;

@Service
@Primary
public class CategoriasServiceJpa implements ICategoriasService {

	@Autowired
	private CategoriaRepositorio categoriaRepo;
	
	@Override
	public void guardar(Categoria categoria) {
		categoriaRepo.save(categoria);

	}
	

	@Override
	public List<Categoria> buscarTodas() {
		// TODO Auto-generated method stub
		return categoriaRepo.findAll();
	}

	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		Optional<Categoria>optional=categoriaRepo.findById(idCategoria);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void eliminar(Integer idCategoria) {
		categoriaRepo.deleteById(idCategoria);
		
	}


	@Override
	public Page<Categoria> buscarTodas(Pageable page) {
		return categoriaRepo.findAll(page);
	}

	
}
