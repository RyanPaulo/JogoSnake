package Jogo;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Janela extends JFrame{
	
	
	private static final long serialVersionUID = 1L;
	private static final int LARGURA_TELA = 350;
	private static final int ALTURA_TELA = 450;
	private ImageIcon iSnake, iFerramenta;
	private JLabel lInicio;
	private JButton bIniciar, bFerr, bSair;
	private FrameJogo frameJogo;
	
	Janela (){
		setTitle("Joga Snake");
		setSize(LARGURA_TELA, ALTURA_TELA);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		iSnake = new ImageIcon(getClass().getResource("/Imagens/snake.png"));
		lInicio = new JLabel(iSnake);
		lInicio.setBounds(0, 0, 350, 450);
		
		Font fonte = new Font("Ink Free", Font.BOLD, 20);
		Color corvEscuro = new Color(28, 46, 23);
		Color corvClaro = new Color(153, 211, 47);
		
		iFerramenta = new ImageIcon(getClass().getResource("/Imagens/ferramenta.png"));
		
		bIniciar = new JButton("INCIAR");
		bIniciar.setName("bIniciar");
		bIniciar.setBackground(corvEscuro);
		bIniciar.setForeground(corvClaro);
		bIniciar.setFont(fonte);
		
		bFerr = new JButton(iFerramenta);
		bFerr.setName("bFerr");
		bFerr.setBackground(corvEscuro);
		bFerr.setForeground(corvClaro);
		
		bSair = new JButton("SAIR");
		bSair.setName("bSair");
		bSair.setBackground(corvEscuro);
		bSair.setForeground(corvClaro);
		bSair.setFont(fonte);

		TratamentoMouse eventosMouse = new TratamentoMouse(this);
		
		bIniciar.addMouseListener(eventosMouse);
		bFerr.addMouseListener(eventosMouse);
		bSair.addMouseListener(eventosMouse);
	
		bIniciar.setBounds(10, 350, 150, 50);
		bFerr.setBounds(170, 350, 60, 50);
		bSair.setBounds(240, 350, 90, 50);
		
		setResizable(false);
		setLayout(null);
		add(lInicio);
		add(bIniciar);
		add(bFerr);
		add(bSair);
		setVisible(true);
		setLocationRelativeTo(null);
	}

	public void abrirTelaDoJogo() {
		this.frameJogo = new FrameJogo();
		this.frameJogo.setVisible(true);
	}
	
	public void Sair() {
		this.setVisible(false);
	}
	
	public void Ferramenta() {
		JOptionPane.showMessageDialog( null, "COMO JOGAR !\n" +
				"- AO APERTA  'INICIAR'  CLIQUE  'ESPAÇO'  PARA DA INICIO O JOGO.\n\n" +
				"- SE PREFERIR, AO INICIAR CLIQUE 'F3' PARA MUDAR PARA O MODO NOTURNO;\n" +
				"   CLIQUE 'F3' PARA RETORNAR AO MODO NORMAL.\n\n" +
				"- PARA MOVER A SNAKE USE : 'A' 'W' 'S' 'D' \n" +
				 "OU SE PREFERIR OS DIRECIONAIS DO TECLADO.\n\n");
	}

}
