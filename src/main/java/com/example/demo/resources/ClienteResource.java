package com.example.demo.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.domain.Cliente;
import com.example.demo.services.ClienteService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value="/cliente")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@RequestMapping(value= "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) throws ObjectNotFoundException{
		Cliente obj = service.find(id);
		return ResponseEntity.ok(obj);
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Cliente obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getIdCliente()).toUri();
		return ResponseEntity.created(uri).build();
	
}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Cliente obj, @PathVariable Integer id) throws ObjectNotFoundException{
		obj.setIdCliente(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	
//	@RequestMapping(value= "/{id}", method = RequestMethod.DELETE)
//	public ResponseEntity<Void> delete(@PathVariable Integer id){
//		service.delete(id);
//		return ResponseEntity.noContent().build();
//	}
	
	
//	@RequestMapping(method = RequestMethod.GET)
//	public ResponseEntity<List <CategoriaDTO>> findAll(){
//		List<Categoria> list = service.findAll();
//		List<CategoriaDTO> listDTO = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
//		return ResponseEntity.ok().body(listDTO);
//	}
	
	
//	@RequestMapping(value = "/page", method = RequestMethod.GET)
//	public ResponseEntity<Page <CategoriaDTO>> findPage(@RequestParam(value="page", defaultValue="0") Integer page, 
//			@RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage, 
//			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
//			@RequestParam(value="direction", defaultValue="ASC") String direction){
//		
//		Page<Categoria> list = service.findPage(page, linesPerPage, orderBy, direction);
//		Page<CategoriaDTO> listDTO = list.map(obj -> new CategoriaDTO(obj));
//		return ResponseEntity.ok().body(listDTO);
//	}

}
