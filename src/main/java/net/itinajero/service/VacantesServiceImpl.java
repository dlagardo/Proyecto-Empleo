package net.itinajero.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.itinajero.model.Vacante;

@Service
public class VacantesServiceImpl implements IVacantesService{
	private List<Vacante>lista=null;
	
	public VacantesServiceImpl() {
		SimpleDateFormat sdf= new SimpleDateFormat("dd-mm-yyyy");
		lista= new LinkedList<Vacante>();
		try {
			Vacante vacante1 = new Vacante();
			vacante1.setId(1);
			vacante1.setNombre("Ingeniero");
			vacante1.setDescripcion("Se necesita Gerente");
			vacante1.setFecha(sdf.parse("08-10-2022"));
			vacante1.setSalario(6000.0);
		    vacante1.setDestacado(1);
		    vacante1.setImagen("empresa1.png");
		    
			Vacante vacante2 = new Vacante();
			vacante2.setId(2);
			vacante2.setNombre("Licenciado");
			vacante2.setDescripcion("Se necesita Lic");
			vacante2.setFecha(sdf.parse("10-10-2022"));
			vacante2.setSalario(9000.0);
			vacante2.setDestacado(0);
			vacante2.setImagen("empresa2.png");
			
			Vacante vacante3 = new Vacante();
			vacante3.setId(3);
			vacante3.setNombre("Diseñador Grafico");
			vacante3.setDescripcion("Se necesita Diseñador");
			vacante3.setFecha(sdf.parse("09-10-2022"));
			vacante3.setSalario(9000.0);
			vacante3.setDestacado(0);
			vacante3.setImagen("empresa3.png");
			
			Vacante vacante4 = new Vacante();
			vacante4.setId(4);
			vacante4.setNombre("Programador");
			vacante4.setDescripcion("Se necesita Programador");
			vacante4.setFecha(sdf.parse("15-10-2022"));
			vacante4.setSalario(9000.0);
			vacante4.setDestacado(1);
			
			
			lista.add(vacante1);
			lista.add(vacante2);
			lista.add(vacante3);
			lista.add(vacante4);
			
		}catch(ParseException e){
			System.out.println("Error : "+e.getMessage());
		}finally {
			
		}
		
	
	}
	
	@Override
	public List<Vacante> buscarTodas() {
		// TODO Auto-generated method stub
		return lista;
	}
	
	@Override
	public Vacante buscaporId(Integer idVacante) {
		for(Vacante v : lista) {
			if(v.getId()==idVacante) {
				return v;
			}
		}
		return null;
	}
	
	@Override
	public void guardar(Vacante vacante) {
		// TODO Auto-generated method stub
		
		
			lista.add(vacante);
		
	}

	@Override
	public List<Vacante> buscarDestacadas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminar(int idVacante) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Vacante> buscarByExample(Example<Vacante> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Vacante> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}
 
}
