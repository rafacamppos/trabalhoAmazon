package com.example.demo.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Cliente;
import com.example.demo.repository.ClienteRepository;


@Service
public class DBService {

	

	@Autowired
	private ClienteRepository clienteRepo;

	public void instantiateTestDataBase() throws ParseException {
		Cliente cliente1 = new Cliente(null, "Rafael", "09720440", "36709295842");
		

		clienteRepo.saveAll(Arrays.asList(cliente1));
	}

}
