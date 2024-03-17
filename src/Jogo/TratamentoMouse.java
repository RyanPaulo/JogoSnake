package Jogo;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;


public class TratamentoMouse implements MouseListener {

	private Janela janela;
	private JanelaJogo janelaJogo;

	public TratamentoMouse(Janela janela) {
		this.janela = janela;
	}
	
	public TratamentoMouse(JanelaJogo janelaJogo) {
		this.janelaJogo = janelaJogo;
	}
	public void mouseClicked(MouseEvent e) {
		JButton botao = (JButton) e.getSource();
	
		if(botao.getName().equals("bIniciar")) {
			janela.abrirTelaDoJogo();
			janela.setVisible(false);
		} else if(botao.getName().equals("bSair")) {
			janela.Sair();
		} else if(botao.getName().equals("bFerr")) {
			janela.Ferramenta();
		} else if(botao.getName().equals("botaoVoltar")) {
			janelaJogo.voltar();
			janelaJogo.setVisible(false);
		}
	}
	public void mousePressed(MouseEvent e) {

	}
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
}


