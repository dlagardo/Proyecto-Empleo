package net.itinajero.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.itinajero.model.Vacante;
import net.itinajero.service.ICategoriasService;
import net.itinajero.service.IVacantesService;
import net.itinajeroutil.Utileria;

@Controller
@RequestMapping("/vacantes")
public class VacantesController {

	@Value("${empleosapp.ruta.imagenes}")
	private String ruta;
	
	@Autowired
	private IVacantesService serviceVacantes;
	
	@Autowired
	private ICategoriasService serviceCategorias;
	
	@GetMapping("/index")
	private String mostrarIndex(Model model) {
		// 1. Obtener todas las vacantes
		List <Vacante>vacantes=serviceVacantes.buscarTodas();
		model.addAttribute("vacantes",vacantes);
		return "vacantes/listVacantes";
		
		
	}
	
	@GetMapping(value = "/indexPaginate")
	public String mostrarIndexPaginado(Model model, Pageable page) {
	Page<Vacante> lista = serviceVacantes.buscarTodas(page);
	model.addAttribute("vacantes", lista);
	return "vacantes/listVacantes";
	}
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int idVacante,Model model) {
		Vacante vacantes=serviceVacantes.buscaporId(idVacante);
		
		model.addAttribute("vacante",vacantes);
		return "vacantes/formVacante";
	}
	@ModelAttribute
	public void setGenericos (Model model) {
		model.addAttribute("categorias",serviceCategorias.buscarTodas());
	}
	@GetMapping("/create")
	public String crear(Vacante vacante,Model model) {
		
		return "vacantes/formVacante";
	}
	
	@PostMapping("/save")
	public String guardar(Vacante vacante,BindingResult result,Model model,RedirectAttributes attributes,@RequestParam("archivoImagen") MultipartFile multiPart){
		
		if(result.hasErrors()) {
			for (ObjectError error: result.getAllErrors()){
				System.out.println("Ocurrio un error: " + error.getDefaultMessage());
				}
			return "vacantes/formVacante";
		}
		
		if (!multiPart.isEmpty()) {
			//String ruta = "/empleos/img-vacantes/"; // Linux/MAC
			//String ruta = "d:/empleos/img-vacantes/"; // Windows
			String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
			if (nombreImagen != null){ // La imagen si se subio
			// Procesamos la variable nombreImagen
			vacante.setImagen(nombreImagen);
			}
			
			}

		
		serviceVacantes.guardar(vacante);
		System.out.println("Vacante: " + vacante);
		System.out.println("Vacante : "+ vacante);
		System.out.println("Vacante: ");
		attributes.addFlashAttribute("msg","Registro Guardado");
		return "redirect:/vacantes/index";
	
		
	}
	
	/*
	 @PostMapping("/save")
	public String guardar(@RequestParam("nombre") String nombre, 
			@RequestParam("descripcion") String descripcion, @RequestParam("estatus") String estatus,
			@RequestParam("fecha") String fecha,@RequestParam("destacado") int destacado,
			@RequestParam("salario") double salario,@RequestParam("detalles") String detalles){
		
		
		System.out.println("Nombre Vacante: " + nombre);
		System.out.println("Descripcion Vacante: " + descripcion);
		System.out.println("Estatus Vacante: " + estatus);
		System.out.println("Fecha: " + fecha);
		System.out.println("Destacado: " + destacado);
		System.out.println("Salario: " + salario);
		System.out.println("Detalles: " + detalles);
		return "vacantes/listVacantes";
	}
	
	*/
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idVacante,Model model,RedirectAttributes attributes) {
		System.out.println("Borrando vacante con id: " + idVacante);
		Vacante vacante=serviceVacantes.buscaporId(idVacante);
		 Utileria.eliminarArchivo(ruta.concat(vacante.getImagen()));
		 System.out.println(ruta.concat(vacante.getImagen()));
		 serviceVacantes.eliminar(idVacante);
	     attributes.addFlashAttribute("msg","La vacante fue eliminada");
		return "redirect:/vacantes/index";
	}
	
	@GetMapping("/view/{id}")
	public String verDetalle(@PathVariable("id") int idVacante,Model model) {
		
		Vacante vacante= serviceVacantes.buscaporId(idVacante);
		
		System.out.println("Vacante : "+ vacante);
		model.addAttribute("vacante", vacante);
		
	//Buscar los detalles de las Vacantes en la BD
		return "detalle";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}
