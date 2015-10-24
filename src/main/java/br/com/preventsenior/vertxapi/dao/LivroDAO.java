package br.com.preventsenior.vertxapi.dao;

import java.util.List;

import br.com.preventsenior.vertxapi.model.Livro;

public interface LivroDAO {

	void insert(Livro livro);
	
	Livro get(Long id);
	
	List<Livro> getAll();
	
	void update(Livro livro);
	
	void delete(Long id);
}
