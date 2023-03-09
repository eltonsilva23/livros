package br.com.eltoncorp.livros.repo;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import br.com.eltoncorp.livros.model.Livros;

public interface LivrosRepo extends CrudRepository<Livros, Integer>{

	public ArrayList<Livros> findByTituloContaining(String titulo);
	
}
