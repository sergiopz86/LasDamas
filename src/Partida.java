
public class Partida{
	Tablero tablero = new Tablero();
	Jugador j = new Jugador();
	Maquina m = new Maquina();
	int cont=0;
	int fi,ci,fm,cm,ff,cf;
	boolean turnoMaq=false;

	
	public void refrescarMaquina(){ //La Inteligencia Artificial--------------------------------
		turnoMaq=true;
		try{
			while(obligatorioComerFicha() || obligatorioComerDama()){
				if(obligatorioComerFicha()){
					tablero.setFicha(fi, ci, '.');
					tablero.setFicha(fm, cm, '.');
					tablero.setFicha(ff, cf, 'N');
					if(ff==7)
						tablero.setFicha(ff, cf, 'X');
				}
				else if(obligatorioComerDama()){
					tablero.setFicha(fi, ci, '.');
					tablero.setFicha(fm, cm, '.');
					tablero.setFicha(ff, cf, 'X');
				}
				turnoMaq=false;
			}
				if(moverNegra() && turnoMaq){
					tablero.setFicha(fi, ci, '.');
					tablero.setFicha(ff, cf, 'N');
					if(ff==7)
						tablero.setFicha(ff, cf, 'X');	
				}
				else if(moverDamaNegra() && turnoMaq){
					tablero.setFicha(fi, ci, '.');
					tablero.setFicha(ff, cf, 'X');
				}
				turnoMaq=false;
		}catch (ArrayIndexOutOfBoundsException ex){}
	}
	
	public boolean obligatorioComerFicha(){
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				try{
					if(m.comer(i, j, i+1, j+1, i+2, j+2, tablero.getTablero())){
						fi=i;
						ci=j;
						fm=i+1;
						cm=j+1;
						ff=i+2;
						cf=j+2;
						return true;	
					}
				}catch (ArrayIndexOutOfBoundsException ex){}
				try{
					if(m.comer(i,j,i+1,j-1,i+2,j-2,tablero.getTablero())){
						fi=i;
						ci=j;
						fm=i+1;
						cm=j-1;
						ff=i+2;
						cf=j-2;
						return true;
					}
					
				}catch (ArrayIndexOutOfBoundsException ex){}
			}
		}
		return false;
	}
	
	
	public boolean obligatorioComerDama(){
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				try{
					if(m.comerDama(i,j,i-1,j+1,i-2,j+2,tablero.getTablero())){
						fi=i;
						ci=j;
						fm=i-1;
						cm=j+1;
						ff=i-2;
						cf=j+2;
						return true;
					}
				}catch (ArrayIndexOutOfBoundsException ex){}
				try{
					if(m.comerDama(i,j,i-1,j-1,i-2,j-2,tablero.getTablero())){
						fi=i;
						ci=j;
						fm=i-1;
						cm=j-1;
						ff=i-2;
						cf=j-2;
						return true;
					}
				}catch (ArrayIndexOutOfBoundsException ex){}
				try{
					if(m.comerDama(i,j,i+1,j-1,i+2,j-2,tablero.getTablero())){
						fi=i;
						ci=j;
						fm=i+1;
						cm=j-1;
						ff=i+2;
						cf=j-2;
						return true;
					}
				}catch (ArrayIndexOutOfBoundsException ex){}
				try{
					if(m.comerDama(i,j,i+1,j+1,i+2,j+2,tablero.getTablero())){
						fi=i;
						ci=j;
						fm=i+1;
						cm=j+1;
						ff=i+2;
						cf=j+2;
						return true;
					}
				}catch (ArrayIndexOutOfBoundsException ex){}
			}
		}
		return false;
	}
	
	public boolean moverNegra(){
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				try{
					if(m.mover(i, j, i+1, j+1, tablero.getTablero())){
						fi=i;
						ci=j;
						ff=i+1;
						cf=j+1;
						return true;	
					}
				}catch (ArrayIndexOutOfBoundsException ex){}
				try{
					if(m.mover(i,j,i+1,j-1,tablero.getTablero())){
						fi=i;
						ci=j;
						ff=i+1;
						cf=j-1;
						return true;
					}
				}catch (ArrayIndexOutOfBoundsException ex){}
			}
		}
		return false;
	}
	
	public boolean moverDamaNegra(){
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				try{
					if(m.moverDama(i,j,i-1,j+1,tablero.getTablero())){
						fi=i;
						ci=j;
						ff=i-1;
						cf=j+1;
						return true;
					}
				}catch (ArrayIndexOutOfBoundsException ex){}
				try{
					if(m.moverDama(i,j,i-1,j-1,tablero.getTablero())){
						fi=i;
						ci=j;
						ff=i-1;
						cf=j-1;
						return true;
					}
				}catch (ArrayIndexOutOfBoundsException ex){}
				try{
					if(m.moverDama(i,j,i+1,j-1,tablero.getTablero())){
						fi=i;
						ci=j;
						ff=i+1;
						cf=j-1;
						return true;
					}
				}catch (ArrayIndexOutOfBoundsException ex){}
				try{
					if(m.moverDama(i,j,i+1,j+1,tablero.getTablero())){
						fi=i;
						ci=j;
						ff=i+1;
						cf=j+1;
						return true;
					}
				}catch (ArrayIndexOutOfBoundsException ex){}
			}
		}
		return false;
	}
	public Tablero getTablero() { return tablero; }
	public Jugador getJugador()	{ return j; }
	
}
