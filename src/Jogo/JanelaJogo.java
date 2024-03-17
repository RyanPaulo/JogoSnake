package Jogo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class JanelaJogo extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final int LARGURA_TELA = 1000;
    private static final int ALTURA_TELA = 500;
    private static final int TAMANHO_BLOCO = 40;
    private static final int UNIDADES = LARGURA_TELA * ALTURA_TELA / (TAMANHO_BLOCO * TAMANHO_BLOCO);
    private int intervalo = 190;
    private static final String NOME_FONTE = "Ink Free";
    private final int[] eixoX = new int[UNIDADES];
    private final int[] eixoY = new int[UNIDADES];
    private int corpoCobra = 6;
    private int blocosComidos;
    private int blocoX;
    private int blocoY;
    private char direcao = 'D'; 
    private boolean estaRodando = false;
    private boolean telainicial = true;
    private boolean modoPreto = false;
    private JButton botaoVoltar ;
    private Color corCobra = Color.GREEN; 
    private Color corCopoCobra = new Color(45, 180, 0); 
    private Color corvClaro = new Color(153, 211, 47);
    private Color corvEscuro = new Color(28, 46, 23);
    Timer timer;
    Random random;
    
    JanelaJogo(FrameJogo frameJogo) {
        random = new Random();
        setPreferredSize(new Dimension(LARGURA_TELA, ALTURA_TELA));
        setBackground(Color.WHITE);
        setFocusable(true);
        addKeyListener(new LeitorDeTeclasAdapter());
        iniciarJogo();
        botaoVoltar();
    }
    public void botaoVoltar() {
    	botaoVoltar = new JButton("VOLTAR  AO INICIO");
        botaoVoltar.setName("botaoVoltar");
        botaoVoltar.setBounds(0, 470, 180, 30);
        add(botaoVoltar);
        setLayout(null);
        TratamentoMouse eventosMouse = new TratamentoMouse(this);
        botaoVoltar.addMouseListener(eventosMouse);
    }

    public void iniciarJogo() {
        criarBloco(Color.black);
        timer = new Timer(intervalo, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        desenharTela(g);
    }

	public void atualizarApariencia(Graphics g) {
		if (modoPreto) {
            setBackground(Color.BLACK);
            corCobra = new Color(0, 0, 238);
            corCopoCobra = new Color(0, 154, 205);
            corvClaro = Color.white;
            botaoVoltar.setBackground(Color.white);
            botaoVoltar.setForeground(Color.BLACK);
            botaoVoltar.setFont(new Font(NOME_FONTE, Font.BOLD, 12));
        } else {
            setBackground(corvEscuro);
            corCobra = Color.GREEN;
            corCopoCobra = new Color(45, 180, 0);
            corvClaro = new Color(153, 211, 47);
            botaoVoltar.setBackground(corvClaro);
            botaoVoltar.setForeground(corvEscuro);
            botaoVoltar.setFont(new Font(NOME_FONTE, Font.BOLD, 12));
        }  
	}
	
    public void desenharTela(Graphics g) {
    	atualizarApariencia(g);
    	if (estaRodando) {
	            g.setColor(Color.red);
	            g.fillOval(blocoX, blocoY, TAMANHO_BLOCO, TAMANHO_BLOCO);

	            for (int i = 0; i < corpoCobra; i++) {
	                if (i == 0) {
	                    g.setColor(corCobra);
	                    g.fillRect(eixoX[0], eixoY[0], TAMANHO_BLOCO, TAMANHO_BLOCO);
	                } else {
	                    g.setColor(corCopoCobra);
	                    g.fillRect(eixoX[i], eixoY[i], TAMANHO_BLOCO, TAMANHO_BLOCO);
	                }
	            }
	            g.setColor(corvClaro);
	            g.setFont(new Font(NOME_FONTE, Font.BOLD, 40));
	            FontMetrics metrics = getFontMetrics(g.getFont());
	            g.drawString("Pontos: " + blocosComidos, (LARGURA_TELA - metrics.stringWidth("Pontos: " + blocosComidos)) / 2, g.getFont().getSize());
	        } else {
	        	if (telainicial) {
	        		InicioDeJogo(g);
	        	} else {
	        		fimDeJogo(g);
	        	}
	        }
    	}

    private void criarBloco(Color black) {
        blocoX = random.nextInt(LARGURA_TELA / TAMANHO_BLOCO) * TAMANHO_BLOCO;
        blocoY = random.nextInt(ALTURA_TELA / TAMANHO_BLOCO) * TAMANHO_BLOCO;
    }
    
    public void fimDeJogo(Graphics g) {
    	g.setColor(corvClaro);
        g.setFont(new Font(NOME_FONTE, Font.BOLD, 40));
        FontMetrics fontePontuacao = getFontMetrics(g.getFont());
        g.drawString("Pontos: " + blocosComidos, (LARGURA_TELA - fontePontuacao.stringWidth("Pontos: " + blocosComidos)) / 2, g.getFont().getSize());
        g.setColor(corvClaro);
        g.setFont(new Font(NOME_FONTE, Font.BOLD, 75));
        FontMetrics fonteFinal = getFontMetrics(g.getFont());
        g.drawString(" Fim do Jogo.", (LARGURA_TELA - fonteFinal.stringWidth("Fim do Jogo")) / 2, ALTURA_TELA / 2);
        g.setColor(corvClaro);
        g.setFont(new Font(NOME_FONTE, Font.BOLD, 30));
        FontMetrics fonteReiniciar = getFontMetrics(g.getFont());
        g.drawString("Clique ESPAÇO para reiniciar!", (LARGURA_TELA - fonteReiniciar.stringWidth("\"Clique ESPAÇO para reiniciar!\"")) / 2, ALTURA_TELA - 20);
    }
    
    public void InicioDeJogo(Graphics g) {
        g.setColor(corvClaro);
        g.setFont(new Font(NOME_FONTE, Font.BOLD, 60));
        FontMetrics fonteInicial = getFontMetrics(g.getFont());
        g.drawString("Clique ESPAÇO para jogar!", (LARGURA_TELA - fonteInicial.stringWidth("Clique ESPAÇO para jogar!")) / 2, ALTURA_TELA / 2);
    }
    
    public void reiniciarJogo() {
    	corpoCobra = 6;
    	blocosComidos = 0;
    	direcao = 'D';
    	criarBloco(Color.black);
    	for(int i = 0; i < corpoCobra; i++ ) {
    		eixoX[i] = 0;
    		eixoY[i] = 0;
    	}
    	estaRodando = true;
    	timer.start();
    } 
    
    public void actionPerformed(ActionEvent e) {
        if (estaRodando) {
            andar();
            alcancarBloco();
            validarLimites();
        }
        repaint();
    }
    
    private void andar() {
        for (int i = corpoCobra; i > 0; i--) {
            eixoX[i] = eixoX[i - 1];
            eixoY[i] = eixoY[i - 1];
        }

        switch (direcao) {
            case 'C':
                eixoY[0] = eixoY[0] - TAMANHO_BLOCO;
                break;
            case 'B':
                eixoY[0] = eixoY[0] + TAMANHO_BLOCO;
                break;
            case 'E':
                eixoX[0] = eixoX[0] - TAMANHO_BLOCO;
                break;
            case 'D':
                eixoX[0] = eixoX[0] + TAMANHO_BLOCO;
                break;
            default:
                break;
        }
    }
    
    private void alcancarBloco() {
        if (eixoX[0] == blocoX && eixoY[0] == blocoY) {
            corpoCobra++;
            blocosComidos++;
            intervalo = intervalo - 5;
            criarBloco(Color.black); 
        }
    }
    
    private void validarLimites() {
        for (int i = corpoCobra; i > 0; i--) {
            if (eixoX[0] == eixoX[i] && eixoY[0] == eixoY[i]) {
                estaRodando = false;
                break;
            }
        }
        if (eixoX[0] < 0 || eixoX[0] > LARGURA_TELA) {
            estaRodando = false;
        }
        if (eixoY[0] < 0 || eixoY[0] > ALTURA_TELA) {
            estaRodando = false;
        }
        if (!estaRodando) {
            timer.stop();
            
        }
    }
    
    public void alternarModoPreto() {
        modoPreto = !modoPreto;
        atualizarApariencia(getGraphics());
    }
 
    public void voltar() {
    	Janela janela = new Janela();
    	janela.setVisible(true);
    	((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
    }
   
    public class LeitorDeTeclasAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
        	int key = e.getKeyCode();
        	
            switch (key) {
	            case KeyEvent.VK_LEFT:
	                if (direcao != 'D') {
	                    direcao = 'E';
	                }
	                break;
	            case KeyEvent.VK_A:
	                if (direcao != 'D') {
	                    direcao = 'E';
	                }
	                break;
	            case KeyEvent.VK_RIGHT:
	                if (direcao != 'E') {
	                    direcao = 'D';
	                }
	                break;
	            case KeyEvent.VK_D:
	                if (direcao != 'E') {
	                    direcao = 'D';
	                }
	                break;
	            case KeyEvent.VK_UP:
	                if (direcao != 'B') {
	                    direcao = 'C';
	                }
	                break;
	            case KeyEvent.VK_W:
	                if (direcao != 'B') {
	                    direcao = 'C';
	                }
	                break;
	            case KeyEvent.VK_DOWN:
	                if (direcao != 'C') {
	                    direcao = 'B';
	                }
	                break;
	            case KeyEvent.VK_S:
	                if (direcao != 'C') {
	                    direcao = 'B';
	                }
	                break;
	            default:
	                break;
            	}
            
            if (key == KeyEvent.VK_F3) { 
            	alternarModoPreto();
            }
            
            if (key == KeyEvent.VK_SPACE) {
            	if (estaRodando == false){
            		telainicial = false;
            		reiniciarJogo();
            	}
            }
        }
    }
}
	

