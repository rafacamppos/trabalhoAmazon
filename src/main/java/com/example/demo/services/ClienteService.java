package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Cliente;
import com.example.demo.repository.ClienteRepository;

import javassist.tools.rmi.ObjectNotFoundException;



@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) throws ObjectNotFoundException {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	
	public List<Cliente> findAll(){
		return repo.findAll();
	}
	
	
	public Cliente insert(Cliente obj) {
		obj.setIdCliente(null);
		return repo.save(obj);
	}
	
	
	public Cliente update(Cliente obj) throws ObjectNotFoundException {
		find(obj.getIdCliente());
		return repo.save(obj);
	}
	
	
//	public void delete(Integer id) {
//		find(id);
//		try {
//			repo.deleteById(id);
//		} catch (DataIntegrityViolationException e) {
//			throw new DataIntegrityException("Não é possivel excluir uma categoria que possi produtos");
//		}
//	}
	
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest =  PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	

}
