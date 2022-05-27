package br.com.serratec.Banco.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.serratec.Banco.exceptions.ContaExistenteException;
import br.com.serratec.Banco.exceptions.ContaNaoExistenteException;
import br.com.serratec.Banco.exceptions.SaldoInsuficienteException;
import br.com.serratec.Banco.models.Conta;

@Service
public class ContaService {
	
	private List<Conta> contas = new ArrayList<>();
	
	
	public void adicionarConta(Conta conta) throws ContaExistenteException {
		
		for(Conta contaAtual : contas) {
			if(contaAtual.getNumero().equals(conta.getNumero())) {
				throw new ContaExistenteException();
			}
		}
		contas.add(conta);
	}
	
	
	public List<Conta> listaContas() {
		return contas;
	}
	
	
	public Conta retornaConta(String numero) throws ContaNaoExistenteException {
		for(Conta conta : contas) {
			if(conta.getNumero().equals(numero)) {
				return conta;
			}
		}
		throw new ContaNaoExistenteException();
	}
	
	
	public void atualizaConta(String numero, Conta atualizacao) throws ContaNaoExistenteException {
		for(Conta conta : contas) {
			if(conta.getNumero().equals(numero)) {
				if(atualizacao.getTitular() != null) {
					conta.setTitular(atualizacao.getTitular());
				}
				if(atualizacao.getNumero() != null) {
					conta.setNumero(atualizacao.getNumero());
				}
				return;
			}
		}
		throw new ContaNaoExistenteException();
	}
	
	
	public void deletaConta(String numero) {
		Conta contaAux = null;
		
		for(Conta conta : contas) {
			if(conta.getNumero().equals(numero)) {
				contaAux = conta;
				break;
			}
		}
		contas.remove(contaAux);
	}
	
	
	public void atualizaSaldo(String numero, String operacao, Double valor) throws ContaNaoExistenteException, SaldoInsuficienteException {
		for(Conta conta : contas) {
			if(conta.getNumero().equals(numero)) {
				if(operacao.equals("credito")) {
					conta.credito(valor);
				}
				
				if(operacao.equals("debito")) {
					if(valor > conta.getSaldo()) {
						throw new SaldoInsuficienteException();
					}
					conta.debito(valor);
				}
			}
		}
		
		throw new ContaNaoExistenteException();
	}
}
