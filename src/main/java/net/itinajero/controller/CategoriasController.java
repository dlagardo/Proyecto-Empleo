package net.itinajero.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.itinajero.model.Categoria;
import net.itinajero.model.Vacante;
import net.itinajero.service.ICategoriasService;


@Controller
@RequestMapping(value="/categorias")
public class CategoriasController {

	@Autowired
	private ICategoriasService serviceCategorias;
	
	
	@GetMapping("/index")
	private String mostrarIndex(Model model) {
		// 1. Obtener todas las CATEGORIAS
		List <Categoria>categorias=serviceCategorias.buscarTodas();
		model.addAttribute("categorias",categorias);
	
		return "categorias/listCategorias";
		
	}
	
	@GetMapping("/create")
	public String crear(Categoria categoria) {
		
		return "categorias/formCategoria";
	}
	
	@PostMapping("/save")
	public String guardar(Categoria categoria,BindingResult result,Model model,RedirectAttributes attributes){
		
		if(result.hasErrors()) {
			for (ObjectError error: result.getAllErrors()){
				System.out.println("Ocurrio un error: " + error.getDefaultMessage());
				}
			return "categorias/formCategoria";
		}
		
		serviceCategorias.guardar(categoria);
		
		System.out.println("Categoria: " + categoria);
	
		attributes.addFlashAttribute("msg","Registro Guardado");
		return "redirect:/categorias/index";
	
		
	}
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int idCategoria,Model model) {
		Categoria categorias=serviceCategorias.buscarPorId(idCategoria);
		
		model.addAttribute("categoria",categorias);
		return "categorias/formCategoria";
	}
	
}
