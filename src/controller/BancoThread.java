package controller;

import java.util.concurrent.Semaphore;

public class BancoThread extends Thread{
	private Semaphore [] typeT;
	private int codConta;
	private double saldo;
	private double valor;
	private int type;
	
	public BancoThread(int codConta, double saldo, double valor, int type, Semaphore [] typeT) {
		this.codConta = codConta;
		this.saldo = saldo;
		this.valor = valor;
		this.type = type;
		this.typeT = typeT;
	}
	
	@Override
	public void run() {
		try {
			this.typeT[type].acquire();
			transacao();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			this.typeT[type].release();
		}
	}
	
	private void transacao() {
		if(this.type == 0) {
			System.out.println("A conta " + this.codConta + " foi retirado R$" + valor +
					" ficando com R$" + (saldo - valor));
		}else{
			System.out.println("A conta " + this.codConta + " foi depositado R$" + valor +
					" ficando com R$" + (saldo + valor));
		}
		try {
			sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
