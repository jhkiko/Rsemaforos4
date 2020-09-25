package view;

import java.util.Random;
import java.util.concurrent.Semaphore;

import controller.BancoThread;

public class Main {

	public static void main(String[] args) {
		Semaphore [] typeT = new Semaphore [2];
		for (int i = 0; i < typeT.length; i++) {
			typeT[i] = new Semaphore(2);
		}
		Random random = new Random();
		for(int i = 1; i <= 20; i++) {
			int aux = random.nextInt(5000);
			double saldo = aux + random.nextDouble();
			double valor = (aux - 1) + random.nextDouble();
			int type = random.nextInt(2);
			Thread t = new BancoThread(i, saldo, valor, type, typeT);
			t.start();
		}
	}

}
