package br.com.serratec.Banco.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.Banco.exceptions.ContaExistenteException;
import br.com.serratec.Banco.exceptions.ContaNaoExistenteException;
import br.com.serratec.Banco.models.Conta;
import br.com.serratec.Banco.services.ContaService;


@RestController
@RequestMapping("/conta")
public class ContaController {
	
	@Autowired
	ContaService servicoConta;
	
	
	@PostMapping
	public void createConta(@RequestBody Conta conta) throws ContaExistenteException {
		servicoConta.adicionarConta(conta);
	}
	
	@GetMapping
	public List<Conta> readContas() {
		return servicoConta.listaContas();
	}
	
	@GetMapping("/{numero}")
	public Conta readConta(@PathVariable String numero) throws ContaNaoExistenteException {
		return servicoConta.retornaConta(numero);
	}
	
	@PutMapping("/{numero}")
	public void updateConta(@PathVariable String numero, @RequestBody Conta atualizacao) throws ContaNaoExistenteException {
		servicoConta.atualizaConta(numero, atualizacao);
	}
	
	@DeleteMapping("/{numero}")
	public void deleteConta(@PathVariable String numero) {
		servicoConta.deletaConta(numero);
	}
}
