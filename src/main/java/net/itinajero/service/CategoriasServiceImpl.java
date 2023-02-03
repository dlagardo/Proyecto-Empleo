package net.itinajero.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



import java.util.LinkedList;
import java.util.List;

import net.itinajero.model.Categoria;



@Service
public class CategoriasServiceImpl implements ICategoriasService {

	private List<Categoria>lista=null;
	
	 public CategoriasServiceImpl() {
		// TODO Auto-generated constructor stub

		lista= new LinkedList<Categoria>();
		try {
			Categoria categoria1 = new Categoria();
			categoria1.setId(1);
			categoria1.setNombre("Contabilidad");
			categoria1.setDescripcion("Descripcion de la Categoria Contabilidad");
			
		    
			Categoria categoria2 = new Categoria();
			categoria2.setId(2);
			categoria2.setNombre("Ventas");
			categoria2.setDescripcion("Trabajos Relacionados con ventas");
		
			Categoria categoria3 = new Categoria();
			categoria3.setId(3);
			categoria3.setNombre("Comunicaciones");
			categoria3.setDescripcion("Trabajos Relacionados Prensa");
		
			
			lista.add(categoria1);
			lista.add(categoria2);
			lista.add(categoria3);
		
			
		}catch(Exception e){
			System.out.println("Error : "+e.getMessage());
		}finally {
			
		}
		
	}
	@Override
	public List<Categoria> buscarTodas() {
		// TODO Auto-generated method stub
		return lista;
	}
	
	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		for(Categoria v : lista) {
			if(v.getId()==idCategoria) {
				return v;
			}
		}
		return null;
	}
	
	@Override
	public void guardar(Categoria categoria) {
		
		lista.add(categoria);
	}
	@Override
	public void eliminar(Integer idCategoria) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Page<Categoria> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}
	
 
}
