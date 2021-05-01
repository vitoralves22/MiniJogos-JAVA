package jogoDaCobrinha;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import javax.swing.JPanel;

public class PainelDoJogo  extends JPanel implements ActionListener{

	static final int LARGURA_TELA = 600;
	static final int ALTURA_TELA = 600;
	static final int TAMANHO_OBJETO = 25;
	static final int QNTD_OBJETOS_TELA = ((LARGURA_TELA*ALTURA_TELA)/TAMANHO_OBJETO);                       
	static final int DELAY = 75;
	
	final int x[] = new int [QNTD_OBJETOS_TELA];
	final int y[] = new int [QNTD_OBJETOS_TELA];
	
	int partesDoCorpo = 6;
	int macasComidas;
	int macaX;
	int macaY;
	char direcao = 'R';
	boolean correndo = false;
	
	Timer timer;
	Random random;
	
	
	PainelDoJogo(){
		random = new Random();
		this.setPreferredSize(new Dimension(LARGURA_TELA, ALTURA_TELA));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		comecarJogo();
	}
	
	public void comecarJogo() {
		novaMaca();
		correndo = true;
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Desenho(g);
	}
	
	public void Desenho(Graphics g) {
		for(int i = 0; i < ALTURA_TELA/TAMANHO_OBJETO; i++) {
			g.drawLine(i*TAMANHO_OBJETO, 0, i*TAMANHO_OBJETO, ALTURA_TELA);
			g.drawLine(0, i*TAMANHO_OBJETO, LARGURA_TELA, i*TAMANHO_OBJETO);
		}
		
		g.setColor(Color.red);
		g.fillOval(macaX, macaY, TAMANHO_OBJETO, TAMANHO_OBJETO);
	}
	
	public void novaMaca() {
		
		
	}
	
	public void Movimento() {
		
	}
	
	public void ChecarPonto() {
		
	}
	
	public void checarColisoes() {
		
	}
	
	public void fimDeJogo(ActionEvent g) {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}    
	
	public class MyKeyAdapter extends KeyAdapter{
		
		@Override
		public void keyPressed(KeyEvent e) {
			
		}
	}

}
