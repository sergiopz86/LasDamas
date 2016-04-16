
public class Maquina {
	
	/*fi => fila inicial ci => columna inicial 
	  fm => fila media cm => columna media
	  ff => fila final cf => columna final*/
	int fi, ci, fm, cm, ff, cf;
	
	public boolean mover(int fi, int ci, int ff, int cf, char tablero[][]){ 
		if(tablero[fi][ci]=='N' && tablero[ff][cf]=='.'
				&& (ff==fi+1 && (cf==ci-1 || cf==ci+1))){
			this.fi=fi; this.ci=ci; this.ff=ff; this.cf=cf;
			return true;
		}
		return false;
	}
	
	public boolean comer(int fi, int ci, int fm, int cm, int ff, int cf, char tablero[][]){
		if(tablero[fi][ci]=='N' && 
				((tablero[fm][cm]=='B' || tablero[fm][cm]=='D') 
						&& (fm==fi+1 & (cm==ci-1 || cm==ci+1)) 
						&& tablero[ff][cf]=='.') && (ff==fi+2 && (cf==ci-2 || cf==ci+2))){
			this.fi=fi; this.ci=ci; this.fm=fm; this.cm=cm; this.ff=ff; this.cf=cf;
			return true;
		}
		return false;
	}
	
	public boolean moverDama(int fi, int ci, int ff, int cf, char tablero[][]){
		if(tablero[fi][ci]=='X' && tablero[ff][cf]=='.'
				&& ((ff==fi+1 || ff==fi-1) && (cf==ci-1 || cf==ci+1))){
			this.fi=fi; this.ci=ci; this.ff=ff; this.cf=cf;
			return true;
		}
		return false;
	}
	
	public boolean comerDama(int fi, int ci, int fm, int cm, int ff, int cf, char tablero[][]){
		if(tablero[fi][ci]=='X' && 
				((tablero[fm][cm]=='B' || tablero[fm][cm]=='D') 
						&& ((fm==fi+1 || fm==fi-1) & (cm==ci-1 || cm==ci+1))
						&& tablero[ff][cf]=='.') && ((ff==fi+2 || ff==fi-2) && (cf==ci-2 || cf==ci+2))){
			this.fi=fi; this.ci=ci; this.fm=fm; this.cm=cm; this.ff=ff; this.cf=cf;
			return true;
		}
		return false;
	}
	
	public int getFi(){ return fi; }
	public int getCi(){ return ci; }
	public int getFm(){ return fm; }
	public int getCm(){ return cm; }
	public int getFf(){ return ff; }
	public int getCf(){ return cf; }

}
