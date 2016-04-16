import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;


public class Principal extends JFrame implements ActionListener{
	JPanel panel = new JPanel();
	GridBagLayout gridbag = new GridBagLayout();
	GridBagConstraints cons = new GridBagConstraints();
	GridBagLayout gridbagSur = new GridBagLayout();
	BorderLayout border = new BorderLayout();
	Fondo fondo = new Fondo();
	Grafica grafica = new Grafica();
	String [] mensaje = {"Turno de Blancas","Turno de Negras",
						"Obligatorio comer", "Blancas ganan", 
						"Negras ganan", "Terminada en tablas",
						"Ha rechazado las tablas"};
	
	JLabel mensajeL = new JLabel();
	JLabel fichasblancasL = new JLabel();
	JLabel fichasnegrasL = new JLabel();
	JLabel contablancasL = new JLabel();
	JLabel contanegrasL = new JLabel();
	JPanel panelSur = new JPanel();
	JPanel panelNorte = new JPanel();
	JButton empezar = new JButton();
	JButton tablas = new JButton();
	JOptionPane dialogo = new JOptionPane();
	Font fuente = new Font("Arial",Font.BOLD,18);
	int contB=0, contN=0;
	Thread t = null;
	
	public Principal(){
		setBounds(300,100, 750,750);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(panel);
		panel.setLayout(border);
		panel.add(fondo, BorderLayout.CENTER);
		fondo.setLayout(gridbag);
//		getContentPane().setBackground(new java.awt.Color(119,0,3));
		fondo.add(grafica);
		panelSur();
		panelNorte();
		setResizable(false);
		setVisible(true);
		dialogo.showMessageDialog(this,"Come todas las fichas de tu oponente para ganar." +System.getProperty("line.separator")+
				"Si estais empatados y ambos teneis menos de 6 fichas puedes pedirle \"Tablas\" a tu oponente."); 
	}
	
	public void panelNorte(){
		panelNorte.add(empezar);
		panelNorte.add(tablas);
		empezar.addActionListener(this);
		tablas.addActionListener(this);
		panelNorte.setBackground(Color.DARK_GRAY);
		empezar.setIcon(new ImageIcon(getClass().getResource("images/empezar.png")));
		empezar.setRolloverIcon(new ImageIcon(getClass().getResource("images/empezar_pulsado.png")));
		empezar.setFocusPainted(false);
		empezar.setBorderPainted(false);
		empezar.setContentAreaFilled(false);
		tablas.setIcon(new ImageIcon(getClass().getResource("images/tablas.png")));
		tablas.setRolloverIcon(new ImageIcon(getClass().getResource("images/tablas_pulsado.png")));
		tablas.setFocusPainted(false);
		tablas.setBorderPainted(false);
		tablas.setContentAreaFilled(false);
		panel.add(panelNorte, BorderLayout.NORTH);
	}
	public void panelSur(){
		
		panelSur.setLayout(gridbagSur);
		//-Fichas blancas:
		cons.gridx=0; 
		cons.gridy=0; 
		cons.insets = new Insets(0,-70,0,10);
		gridbagSur.setConstraints(fichasblancasL, cons);
		panelSur.add(fichasblancasL);
		fichasblancasL.setIcon(new ImageIcon(getClass().getResource("images/Fichas_blancas.png")));
		//-contador blancas
		cons.gridx=1; 
		cons.gridy=0;
		cons.insets = new Insets(0,-90,0,90);
		gridbagSur.setConstraints(contablancasL, cons);
		contablancasL.setFont(fuente);
		contablancasL.setForeground(Color.WHITE);
		panelSur.add(contablancasL);
		//-Mensaje principal
		cons.gridx=2; 
		cons.gridy=0;
		cons.insets = new Insets(0,20,0,10);
		gridbagSur.setConstraints(mensajeL, cons);
		panelSur.add(mensajeL);
		mensajeL.setPreferredSize(new Dimension(250,40));
		mensajeL.setFont(fuente);
		mensajeL.setForeground(Color.WHITE);
		mensajeL.setText("Bienvenido. Pulsa Empezar.");
		//-Fichas negras:
		cons.gridx=0; 
		cons.gridy=1; 
		cons.insets = new Insets(0,-70,0,10);
		gridbagSur.setConstraints(fichasnegrasL, cons);
		panelSur.add(fichasnegrasL);
		fichasnegrasL.setIcon(new ImageIcon(getClass().getResource("images/Fichas_negras.png")));
		//-contador negras
		cons.gridx=1; 
		cons.gridy=1; 
		cons.insets = new Insets(0,-90,0,90);
		gridbagSur.setConstraints(contanegrasL, cons);
		panelSur.add(contanegrasL);
		contanegrasL.setFont(fuente);
		contanegrasL.setForeground(Color.WHITE);
		panelSur.setBackground(Color.DARK_GRAY);
		panel.add(panelSur, BorderLayout.SOUTH);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==empezar){
			grafica.partida.getTablero().reiniciar();
			repaint();
			mensajeL.setText(mensaje[0]);
		}
		else if(e.getSource()==tablas && contB==contN && contB<6
				&& mensajeL.getText()==mensaje[0]){
			mensajeL.setText(mensaje[5]);
		}
		else if(e.getSource()==tablas  && (contB==contN && contB>6
				&& mensajeL.getText()==mensaje[0]) || (contB!=contN))
			mensajeL.setText(mensaje[6]);	
	}
	


	public class Fondo extends JPanel{
		Image fondo = new ImageIcon(getClass().getResource("images/fondo.jpg")).getImage();
		
		public Fondo(){
			setPreferredSize(new Dimension(700,700));
			setVisible(true);
		}
		
		public void paintComponent(Graphics g){
			g.drawImage(fondo,0,0,this);
		}
	}
	
	public class Grafica extends JPanel implements MouseListener{
		
		Image tablero = new ImageIcon(getClass().getResource("images/tablero2.png")).getImage();
		Image blanca = new ImageIcon(getClass().getResource("images/fichablanca.png")).getImage();
		Image seleccionada = new ImageIcon(getClass().getResource("images/fichablanca_naranja.png")).getImage();
		Image seleccionadaD = new ImageIcon(getClass().getResource("images/damablanca_naranja.png")).getImage();
		Image negra = new ImageIcon(getClass().getResource("images/fichanegra.png")).getImage();
		Image negramuerta = new ImageIcon(getClass().getResource("images/fichanegra.png")).getImage();
		Image damablanca = new ImageIcon(getClass().getResource("images/damablanca.png")).getImage();
		Image damanegra = new ImageIcon(getClass().getResource("images/damanegra.png")).getImage();

		Partida partida = new Partida();
		
		int fi=0,ci=0,fm, cm, ff,cf;
		int auxfi, auxci;
		boolean naranja=false, naranjaD=false;
		boolean turnoJug=true, turnoMaq=false;
		boolean selected=false;
		boolean hamovido=false, hamovidoMaq=true, hacomido=false;
		String movimiento;
		Thread t = null;
		
		public Grafica(){
			setPreferredSize(new Dimension(540,540));
			setVisible(true);
			addMouseListener(this);
		}
		
		public boolean escogerFichaBlanca(int f, int c){
			try{
				if(partida.getTablero().getTablero()[f][c]=='B'){
					return true;
				}
			}catch (ArrayIndexOutOfBoundsException ex){}
			return false;
		}
		
		public boolean escogerDamaBlanca(int f, int c){
			try{
				if(partida.getTablero().getTablero()[f][c]=='D'){
					return true;
				}
			}catch (ArrayIndexOutOfBoundsException ex){}
			return false;
		}
		
		public boolean moverFichaBlanca(int fi, int ci, int ff, int cf){
			try{
				if(partida.getJugador().mover(fi, ci, ff, cf, partida.getTablero().getTablero())){
					return true;
				}
			}catch (ArrayIndexOutOfBoundsException ex){}
			return false;
		}
		
		public boolean moverDamaBlanca(int fi, int ci, int ff, int cf){
			try{
				if(partida.getJugador().moverDama(fi, ci, ff, cf, partida.getTablero().getTablero())){
					return true;
				}
			}catch (ArrayIndexOutOfBoundsException ex){}
			return false;
		}
		
		public boolean comerFichaBlanca(int fi, int ci, int fm, int cm, int ff, int cf){
			try{
				if(partida.getJugador().comer(fi, ci, fm, cm, ff, cf, partida.getTablero().getTablero()))
					return true;
			}catch (ArrayIndexOutOfBoundsException ex){}
			return false;
		}
		
		public boolean comerDamaBlanca(int fi, int ci, int fm, int cm, int ff, int cf){
			try{
				if(partida.getJugador().comerDama(fi, ci, fm, cm, ff, cf, partida.getTablero().getTablero()))
						return true;
			}catch (ArrayIndexOutOfBoundsException ex){}
			return false;
		}

		public boolean obligatorioComer(){
			for(int i=0; i<8; i++){
				for(int j=0; j<8; j++){
					try{
						if((comerFichaBlanca(i,j, i-1, j+1, i-2,j+2) 
								|| comerFichaBlanca(i,j,i-1,j-1,i-2,j-2)
								|| comerDamaBlanca(i,j, i-1, j+1, i-2,j+2) 
								|| comerDamaBlanca(i,j,i-1,j-1,i-2,j-2)
								|| comerDamaBlanca(i,j,i+1,j-1,i+2,j-2) 
								|| comerDamaBlanca(i,j,i+1,j+1,i+2,j+2)) && (!turnoMaq)){
							return true;
						}
					}catch (ArrayIndexOutOfBoundsException ex){}
				}
			}
			return false;
		}
		
		public boolean sigueComiendo(int i, int j){
			
					try{
						if((comerFichaBlanca(i,j, i-1, j+1, i-2,j+2) 
								|| comerFichaBlanca(i,j,i-1,j-1,i-2,j-2)
								|| comerDamaBlanca(i,j, i-1, j+1, i-2,j+2) 
								|| comerDamaBlanca(i,j,i-1,j-1,i-2,j-2)
								|| comerDamaBlanca(i,j,i+1,j-1,i+2,j-2) 
								|| comerDamaBlanca(i,j,i+1,j+1,i+2,j+2)) && (hacomido)){
							return true;
						}
					}catch (ArrayIndexOutOfBoundsException ex){}
				
			return false;
		}
		
		public boolean obligatorioMover(){
			if(!turnoMaq)
				return true;
			return false;
		}
		
		public void paintComponent(final Graphics g){
			g.drawImage(tablero,0,0,this);
			contB=0;
			contN=0;
			
			for(int i=0; i<8; i++){
				for(int j=0;j<8; j++){
					if(partida.getTablero().getTablero()[i][j]=='B'){
						g.drawImage(blanca, j*65+10,i*65+10, 65, 65, this);
						contB++;
					}
					else if(partida.getTablero().getTablero()[i][j]=='N'){
						g.drawImage(negra, j*65+10,i*65+10, 65, 65, this);	
						contN++;
					}
					else if(partida.getTablero().getTablero()[i][j]=='D'){
						g.drawImage(damablanca, j*65+10,i*65+10, 65, 65, this);
						contB++;
					}
					else if(partida.getTablero().getTablero()[i][j]=='X'){
						g.drawImage(damanegra, j*65+10,i*65+10, 65, 65, this);
						contN++;
					}
				}
			}
			
			if(naranja){
				g.drawImage(seleccionada, ci*65+10,fi*65+10, 65, 65, this);
			}
			else if(naranjaD){
				g.drawImage(seleccionadaD, ci*65+10,fi*65+10, 65, 65, this);
			}
			contablancasL.setText(""+contB);
			contanegrasL.setText(""+contN);
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent arg0) {}
		@Override
		public void mouseExited(MouseEvent arg0) {}
		@Override
		public void mousePressed(MouseEvent e) {
			
			//--COMPROBACION DE TURNO----------------------	
			if(obligatorioMover() && mensajeL.getText()==mensaje[0]){
				//--MOVER (FICHA O DAMA)-------------------
				if(!obligatorioComer() && selected){
					ff=e.getY()/65;
					cf=e.getX()/65;
					if(moverFichaBlanca(fi, ci, e.getY()/65, e.getX()/65)){
						partida.getTablero().setFicha(fi, ci, '.');
						partida.getTablero().setFicha(e.getY()/65, e.getX()/65, 'B');
						if(e.getY()/65==0)
							partida.getTablero().setFicha(e.getY()/65, e.getX()/65, 'D');
						turnoMaq=true;
					}
					else if(moverDamaBlanca(fi, ci, e.getY()/65, e.getX()/65)){
						partida.getTablero().setFicha(fi, ci, '.');
						partida.getTablero().setFicha(e.getY()/65, e.getX()/65, 'D');
						turnoMaq=true;
					}
					else
						movimiento=null;
				}
				//--COMER (FICHA O DAMA)-------------------
				else if(obligatorioComer() && selected){
					ff=e.getY()/65;
					cf=e.getX()/65;
					if(comerFichaBlanca(fi, ci, fi-1, ci-1, e.getY()/65, e.getX()/65)){
						if(e.getX()/65==ci-2){
							partida.getTablero().setFicha(fi, ci, '.');
							partida.getTablero().setFicha(fi-1, ci-1, '.');
							partida.getTablero().setFicha(e.getY()/65, e.getX()/65, 'B');
							if(e.getY()/65==0)
								partida.getTablero().setFicha(e.getY()/65, e.getX()/65, 'D');
							turnoMaq=true;
							hacomido=true;
						}
					}
					if(comerFichaBlanca(fi, ci, fi-1, ci+1, e.getY()/65, e.getX()/65)){
						if(e.getX()/65==ci+2){
							partida.getTablero().setFicha(fi, ci, '.');
							partida.getTablero().setFicha(fi-1, ci+1, '.');
							partida.getTablero().setFicha(e.getY()/65, e.getX()/65, 'B');
							if(e.getY()/65==0)
								partida.getTablero().setFicha(e.getY()/65, e.getX()/65, 'D');
							turnoMaq=true;
							hacomido=true;
						}
					}
					if(comerDamaBlanca(fi, ci, fi-1, ci-1, e.getY()/65, e.getX()/65)){
						if(e.getX()/65==ci-2){
							partida.getTablero().setFicha(fi, ci, '.');
							partida.getTablero().setFicha(fi-1, ci-1, '.');
							partida.getTablero().setFicha(e.getY()/65, e.getX()/65, 'D');
							turnoMaq=true;
							hacomido=true;
						}
					}
					if(comerDamaBlanca(fi, ci, fi-1, ci+1, e.getY()/65, e.getX()/65)){
						if(e.getX()/65==ci+2){
							partida.getTablero().setFicha(fi, ci, '.');
							partida.getTablero().setFicha(fi-1, ci+1, '.');
							partida.getTablero().setFicha(e.getY()/65, e.getX()/65, 'D');
							turnoMaq=true;
							hacomido=true;
						}
					}
					if(comerDamaBlanca(fi, ci, fi+1, ci+1, e.getY()/65, e.getX()/65)){
						if(e.getX()/65==ci+2){
							partida.getTablero().setFicha(fi, ci, '.');
							partida.getTablero().setFicha(fi+1, ci+1, '.');
							partida.getTablero().setFicha(e.getY()/65, e.getX()/65, 'D');
							turnoMaq=true;
							hacomido=true;
						}
					}
					if(comerDamaBlanca(fi, ci, fi+1, ci-1, e.getY()/65, e.getX()/65)){
						if(e.getX()/65==ci-2){
							partida.getTablero().setFicha(fi, ci, '.');
							partida.getTablero().setFicha(fi+1, ci-1, '.');
							partida.getTablero().setFicha(e.getY()/65, e.getX()/65, 'D');
							turnoMaq=true;
							hacomido=true;
						}
					}
				}
				else{
					movimiento=null;
				}
				//-- SELECCIONAR (FICHA O DAMA)---------------------------------
				if(escogerFichaBlanca(e.getY()/65,e.getX()/65) && !selected){
					fi = e.getY()/65;
					ci = e.getX()/65;
					naranja=true;
					selected=true;
				}
				else if(escogerDamaBlanca(e.getY()/65,e.getX()/65) && !selected){
					fi = e.getY()/65;
					ci = e.getX()/65;
					naranjaD=true;
					selected=true;
				}
				else{
					naranja=false;
					naranjaD=false;
					selected=false;
				}
			}
			
			//--SISTEMA DE TURNOS---------------------------------------------
			if(sigueComiendo(e.getY()/65,e.getX()/65))
				turnoMaq=false;
			
			if(!obligatorioMover()){ 
				partida.refrescarMaquina();
			}
			turnoMaq=false;
			hacomido=false;
			
			partida.getTablero().imprimir();
			
			
			repaint();
		}
		
		
		@Override
		public void mouseReleased(MouseEvent e) {

			if(mensajeL.getText()==mensaje[6]){
				mensajeL.setText(mensaje[0]);
			}
			else if(contB==0)
				mensajeL.setText(mensaje[4]);
			else if(contN==0)
				mensajeL.setText(mensaje[3]);
			else if(contB==0 && contN==0)
				mensajeL.setText(mensaje[5]);
		}
	
	}//Fin class Grafica
	
	public static void main(String[]args){
		new Principal();
	}
}//fin class Principal
