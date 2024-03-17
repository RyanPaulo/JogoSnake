package Jogo;

import javax.swing.*;


public class FrameJogo extends JFrame {

	private static final long serialVersionUID = 1L;
	
	FrameJogo() {
		  	JanelaJogo janelaJogo = new JanelaJogo(this);
	        add(janelaJogo);
	        setTitle("Jogo da Cobrinha - Snake game");
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        setResizable(false);
	        pack();
	        setVisible(true);
	        setLocationRelativeTo(null);
	    }
}
