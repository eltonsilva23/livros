package br.com.eltoncorp.livros.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.eltoncorp.livros.model.Livros;
import br.com.eltoncorp.livros.repo.LivrosRepo;

@RestController
public class LivrosController {
	@Autowired
	private LivrosRepo repo;
	
	@GetMapping("/livros")
	public ArrayList<Livros> recuperarTodos(){
		return (ArrayList<Livros>) repo.findAll();
	}
	
	@GetMapping("/livros/{codigo}")
	public ResponseEntity<Livros> recuperarPeloCodigo(@PathVariable int codigo){
		Livros p = repo.findById(codigo).orElse(null);
		if (p != null) {
			return ResponseEntity.ok(p);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/livros")
	public ResponseEntity<?> adicionarLivros(@RequestBody Livros novo){
		Livros res = repo.save(novo);
		if (res != null) {
			return ResponseEntity.ok(res);
		}
		return ResponseEntity.badRequest().body(null);//new MensagemErro(9876,"Erro ao cadastrar Livro"));
	}
	
	@GetMapping("/livros/busca")
	public ArrayList<Livros> buscarPorPalavra(@RequestParam(name = "palavra") String palavra){
		return repo.findByTituloContaining(palavra);
	}
	
	
}
