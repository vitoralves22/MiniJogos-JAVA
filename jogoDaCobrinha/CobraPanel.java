package jogoDaCobrinha;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import javax.swing.JPanel;

public class CobraPanel  extends JPanel implements ActionListener{

	static final int LARGURA_TELA = 600;
	static final int ALTURA_TELA = 600;
	static final int TAMANHO_PIXEL = 25;
	static final int QNTD_PIXELS_TELA = ((LARGURA_TELA*ALTURA_TELA)/TAMANHO_PIXEL);                       
	static final int DELAY = 75;
	
	final int x[] = new int [QNTD_PIXELS_TELA];
	final int y[] = new int [QNTD_PIXELS_TELA];
	
	int partesDoCorpo = 5;
	int macasComidas;
	int macaX;
	int macaY;
	char direcao = 'D';
	int estadoDeJogo = 0;
	
	Timer timer;
	Random random;
	
	CobraPanel(){
		random = new Random();
		this.setPreferredSize(new Dimension(LARGURA_TELA, ALTURA_TELA));
		this.setBackground(Color.DARK_GRAY);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
	}
	
	public void comecarJogo() {
		novaMaca();
		estadoDeJogo = 1;
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Desenho(g);
	}
	
	public void Desenho(Graphics g) {
		placar(g);
		
		if(estadoDeJogo == 1) {
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
				}else{
					g.setColor(new Color(45,180,0));
					g.fillRect(x[i], y[i], TAMANHO_PIXEL, TAMANHO_PIXEL);
				}
			}
		}else if (estadoDeJogo == 0){
			telaInicial(g);
		}else{
			fimDeJogo(g);
		}
	}
	
	public void placar(Graphics g) {
		g.setColor(Color.GREEN);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("PONTUACAO: " + macasComidas, (LARGURA_TELA - metrics.stringWidth("PONTUACAO: " + macasComidas))/2, g.getFont().getSize());
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
				estadoDeJogo = 3;
			}
		}
		
		//Checando Colisão nas bordas
		if (x[0] < 0) {
			estadoDeJogo = 3;
		}
	
		if (x[0] > LARGURA_TELA) {
			estadoDeJogo = 3;
		}
		
		if (y[0] < 0) {
			estadoDeJogo = 3;
		}
		
		if (y[0] > ALTURA_TELA) {
			estadoDeJogo = 3;
		}
		
		if(estadoDeJogo == 3) {
			timer.stop();
		}
	}
	
	public void telaInicial(Graphics g) {
		placar(g);
		g.setColor(Color.GREEN);
		g.setFont(new Font("Ink Free", Font.BOLD, 40));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("Iniciar <<Enter>>", (LARGURA_TELA - metrics.stringWidth("Iniciar <<Enter>>"))/2, ALTURA_TELA/2);
	}
	
	public void fimDeJogo(Graphics g) {
		placar(g);
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free", Font.BOLD, 40));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("Game Over <<delete>>", (LARGURA_TELA - metrics.stringWidth("Game Over <<delete>>"))/2, ALTURA_TELA/2);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(estadoDeJogo == 1) {
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
			
				case KeyEvent.VK_DELETE:
					new CobraFrame();
				break;
				
				case KeyEvent.VK_ENTER:
					comecarJogo();
				break;
				
				case KeyEvent.VK_SPACE:
					if(timer.isRunning()) {
						timer.stop();
					}else if (!(timer.isRunning())) {
						timer.start();
					}		
				break;
			
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
