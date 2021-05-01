package jogoDaCobrinha;

import javax.swing.JFrame;

public class FrameDoJogo extends JFrame {
	
		FrameDoJogo(){
			PainelDoJogo painel = new PainelDoJogo();
			this.add(painel);
			this.setTitle("Cobra");
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setResizable(false);
			this.pack();
			this.setVisible(true);
			this.setLocationRelativeTo(null);
		}







}

