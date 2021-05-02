package jogoDaCobrinha;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class CobraFrame extends JFrame {
	
		CobraFrame(){
			this.add(new CobraPanel());
			this.setTitle("Cobra");
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setResizable(false);
			this.pack();
			this.setVisible(true);
			this.setLocationRelativeTo(null);
			ImageIcon icone = new ImageIcon("cobraIcon.png");
			this.setIconImage(icone.getImage());			
		}
}

