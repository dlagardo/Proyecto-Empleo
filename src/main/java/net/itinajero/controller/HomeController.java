package net.itinajero.controller;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import net.itinajero.model.Perfil;
import net.itinajero.model.Usuario;
import net.itinajero.model.Vacante;
import net.itinajero.service.ICategoriasService;
import net.itinajero.service.IUsuariosService;
import net.itinajero.service.IVacantesService;

@Controller
public class HomeController {
	
	@Autowired
	private IVacantesService serviceVacantes;
	@Autowired
	private ICategoriasService serviceCategorias;
	
	@Autowired
   	private IUsuariosService serviceUsuarios;
	
	@GetMapping("/")
	public String mostrarHome(Model model) {
		return "home";
	}
	
	@GetMapping("/signup")
	public String registrarse(Usuario usuario) {
		return "formRegistro";
	}
	
	@PostMapping("/signup")
	public String guardarRegistro(Usuario usuario, BindingResult result,RedirectAttributes attributes) {
		try {
			
			if (result.hasErrors()) {
				return "usuarios/formRegistro";
			}		
			
			// la logica restante
	 
			
			usuario.setEstatus(1); // Activado por defecto
			usuario.setFechaRegistro(new Date()); // Fecha de Registro, la fecha actual del servidor
			
			// Creamos el Perfil que le asignaremos al usuario nuevo
			Perfil perfil = new Perfil();
			perfil.setId(3); // Perfil USUARIO
			usuario.agregar(perfil);
			
			/**
			 * Guardamos el usuario en la base de datos. El Perfil se guarda automaticamente
			 */
			serviceUsuarios.guardar(usuario);
					
			attributes.addFlashAttribute("msg", "El registro fue guardado correctamente!");
			
			return "redirect:/usuarios/index";
			
			// 							
			
			
		}catch(Exception ex) {
			attributes.addFlashAttribute("msg", "Ocurrio un error durante la operación. " + ex.getMessage());
		}
		return "redirect:/usuarios/index";
	}
		
		
		
	
	
	@GetMapping("/tabla")
	public String mostrarTabla(Model model) {
		List<Vacante> lista = serviceVacantes.buscarTodas();
		model.addAttribute("vacantes", lista);
		
		return "tabla";
	}
	
	@GetMapping("/detalle")
	public String mostrarDetalle(Model model) {
		Vacante vacante = new Vacante();
		vacante.setNombre("Ingeniero de comunicaciones");
		vacante.setDescripcion("Se solicita ingeniero para dar soporte a intranet");
		vacante.setFecha(new Date());
		vacante.setSalario(9700.0);
		model.addAttribute("vacante", vacante);
		return "detalle";
	}
	
	@GetMapping("/listado")
	public String mostrarListado(Model model) {
		List<String> lista = new LinkedList<String>();
		lista.add("Ingeniero  de Sistemas");
		lista.add("Auxiliar de Contabilidad");
		lista.add("Vendedor");
		lista.add("Arquitecto");
		
		model.addAttribute("empleos", lista);
		
		return "listado";
	}
	@GetMapping("/search")
	public String buscar(@ModelAttribute("search") Vacante vacante,Model model) {
		
		System.out.println("Buscando por : "+vacante);
		//where descripcion like ´´
		ExampleMatcher matcher= ExampleMatcher.matching().withMatcher("descripcion",
				ExampleMatcher.GenericPropertyMatchers.contains());
		
		Example<Vacante>example=Example.of(vacante,matcher);
		List<Vacante>lista=serviceVacantes.buscarByExample(example);
		model.addAttribute("vacantes",lista);
		return "home";
	}
	
	@InitBinder //para string si lo detecta vacio en el data binding lo setea a null
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class,new StringTrimmerEditor(true));
		
	}
	@ModelAttribute
	public void setGenericos(Model model) {
		Vacante vacanteSearch=new Vacante();
		vacanteSearch.reset();
		model.addAttribute("vacantes", serviceVacantes.buscarDestacadas());
		model.addAttribute("categorias", serviceCategorias.buscarTodas());
		model.addAttribute("search",vacanteSearch);
	}
	

	
}
