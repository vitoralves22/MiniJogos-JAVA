package jogo21;

import javax.swing.JOptionPane;


public class Jogo21 {
	
	int contador, valorCarta, opcao;
	String[] nipes = {"Ouros", "Copas", "Espadas", "Paus"};
	String[] faces = {"Az", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Valete", "Dama", "Rei"};
	String [] faceRepetida;
	String nipe, face;
	
	
	void sortearCarta() {
		
		nipe = nipes[(int) (Math.random() * 4)];
		face = faces[(int) (Math.random() * 13)];
		
		System.out.println(face + " de " + nipe);
	
	}
	
	void verificarValor() {
		if (face == "Az") {
			valorCarta = 1;
		}else if(face == "Valete") {
			valorCarta = 11;	
		}else if(face == "Dama") {
			valorCarta = 12;	
		}else if(face == "Rei") {
			valorCarta = 13;
		}else {
			int n = Integer.parseInt(face);
			valorCarta = n;
		}
	}
	
	
	void contador21() {
		do {
		String termostring = JOptionPane.showInputDialog(null, "Escolha 1 para continuar ou 2 para parar");
		opcao = Integer.parseInt(termostring);
		switch (opcao) {
		default:
			System.out.println("essa opção não existe");
		break;
		
		case 1:
			sortearCarta();
			verificarValor();
			contador += valorCarta;
			System.out.println(contador);
		break;
	
		case 2:
		break;
		}
		}while(opcao != 2);
		
		if(contador <21) {
		System.out.println("Você perdeu");
		}else if(contador >21) {
		System.out.println("Você perdeu");	
		}else{
		System.out.println("Você ganhou");	
		}

	
	}
}


