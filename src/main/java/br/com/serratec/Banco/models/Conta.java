package br.com.serratec.Banco.models;

public class Conta {
	private String numero;
	private String titular;
	private Double saldo;
	
	
	public Conta() {
		super();
	}
	
	public Conta(String numero, String titular, Double saldo) {
		super();
		this.numero = numero;
		this.titular = titular;
		this.saldo = saldo;
	}


	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}


	public String getTitular() {
		return titular;
	}


	public void setTitular(String titular) {
		this.titular = titular;
	}


	public Double getSaldo() {
		return saldo;
	}
	
	public void credito(Double valor) {
		this.saldo += valor;
	}
	
	public void debito(Double valor) {
		this.saldo -= valor;
	}
}
