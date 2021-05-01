package jogoDaCobrinha;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import javax.swing.JPanel;

public class PainelDoJogo  extends JPanel implements ActionListener{

	static final int LARGURA_TELA = 600;
	static final int ALTURA_TELA = 600;
	static final int TAMANHO_PIXEL = 25;
	static final int QNTD_PIXELS_TELA = ((LARGURA_TELA*ALTURA_TELA)/TAMANHO_PIXEL);                       
	static final int DELAY = 75;
	
	final int x[] = new int [QNTD_PIXELS_TELA];
	final int y[] = new int [QNTD_PIXELS_TELA];
	
	int partesDoCorpo = 6;
	int macasComidas;
	int macaX;
	int macaY;
	char direcao = 'D';
	boolean jogando = false;
	
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
		jogando = true;
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Desenho(g);
	}
	
	public void Desenho(Graphics g) {
		if(jogando) {
			//Grades no frame
			/*for(int i = 0; i < ALTURA_TELA/TAMANHO_PIXEL; i++) {
				g.drawLine(i*TAMANHO_PIXEL, 0, i*TAMANHO_PIXEL, ALTURA_TELA);
				g.drawLine(0, i*TAMANHO_PIXEL, LARGURA_TELA, i*TAMANHO_PIXEL);
			}*/
			
			g.setColor(Color.red);
			g.fillOval(macaX, macaY, TAMANHO_PIXEL, TAMANHO_PIXEL);
			
			
			for(int i = 0; i < partesDoCorpo; i++) {
				if (i == 0) {
					g.setColor(Color.green);
					g.fillRect(x[i], y[i], TAMANHO_PIXEL, TAMANHO_PIXEL);
				}
				else {
					g.setColor(new Color(45,180,0));
					g.fillRect(x[i], y[i], TAMANHO_PIXEL, TAMANHO_PIXEL);
				}
			}
		}
		else {
			fimDeJogo(g);
		}
		
		placar(g);
		
		
	}
	
	public void placar(Graphics g) {
		g.setColor(Color.blue);
		g.setFont(new Font("Ink Free", Font.BOLD, 15));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("PONTUAÇÃO: " + macasComidas, (LARGURA_TELA - metrics.stringWidth("PONTUAÇÃO: " + macasComidas))/2, g.getFont().getSize());
	}
	
	public void novaMaca() {
		macaX = random.nextInt((int)(LARGURA_TELA/TAMANHO_PIXEL))*TAMANHO_PIXEL;
		macaY = random.nextInt((int)(ALTURA_TELA/TAMANHO_PIXEL))*TAMANHO_PIXEL;
	}
	
	public void movimento() {
		
		for(int i = partesDoCorpo; i > 0; i--) {
			x[i] = x [i-1];
			y[i] = y [i-1];
		}
		
		switch(direcao){
		case 'C':
			y[0] = y[0] - TAMANHO_PIXEL;
		break;
		
		case 'B':
			y[0] = y[0] + TAMANHO_PIXEL;
		break;
		
		case 'E':
			x[0] = x[0] - TAMANHO_PIXEL;
		break;
		
		case 'D':
			x[0] = x[0] + TAMANHO_PIXEL;
		break;
		
		}
	}
	
	public void checarMaca() {
		if ((x[0] == macaX) && (y[0] == macaY)) {
			partesDoCorpo++;
			macasComidas++;
		novaMaca();
		}
	
	}
	
	public void checarColisoes() {
		//Checando Colisão no Corpo
		for(int i = partesDoCorpo; i > 0; i--) {
			if((x[0] == x[i]) && (y[0] == y[i])) {
				jogando = false;
			}
		}
		
		//Checando Colisão nas bordas
		if (x[0] < 0) {
			jogando = false;
		}
	
		if (x[0] > LARGURA_TELA) {
			jogando = false;
		}
		
		if (y[0] < 0) {
			jogando = false;
		}
		
		if (y[0] > ALTURA_TELA) {
			jogando = false;
		}
		
		if(!jogando) {
			timer.stop();
		}
	}
	
	public void fimDeJogo(Graphics g) {
		placar(g);
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free", Font.BOLD, 75));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("Game Over", (LARGURA_TELA - metrics.stringWidth("Game Over"))/2, ALTURA_TELA/2);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(jogando) {
			movimento();
			checarMaca();
			checarColisoes();
		}
		repaint();
		
	}    
	
	public class MyKeyAdapter extends KeyAdapter{
		
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			
				case KeyEvent.VK_LEFT:
					if(direcao != 'D') {
						direcao = 'E';
					}
				break;
				
				case KeyEvent.VK_RIGHT:
					if(direcao != 'E') {
						direcao = 'D';
					}
				break;
				
				case KeyEvent.VK_UP:
					if(direcao != 'B') {
						direcao = 'C';
					}
				break;
				
				case KeyEvent.VK_DOWN:
					if(direcao != 'C') {
						direcao = 'B';
					}
				break;
			}
			
		}
	}

}
