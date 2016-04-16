
public class Tablero {
	
	char tablero[][] = new char[8][8];
	
	public Tablero(){
		for(int i=0;i<8;i++){
			for(int j=0; j<8; j++){
				tablero[i][j]='.';
			}
		}
		colocarFichas();
	}
	
	public void colocarFichas(){
		for(int i=7; i>=5; i--){
			for(int j=0; j<8; j++){
				if((i%2==1 && j%2==0)||(i%2==0 && j%2==1))
					tablero[i][j] = 'B';
			}
		}
		
		for(int i=0; i<=2; i++){
			for(int j=0; j<8; j++){
				if((i%2==1 && j%2==0)||(i%2==0 && j%2==1))
					tablero[i][j] = 'N';
			}
		}
		for(int i=0; i<8; i++){
			System.out.println(tablero[i]);
		}
	}
	
	public void reiniciar(){
		for(int i=0;i<8;i++){
			for(int j=0; j<8; j++){
				tablero[i][j]='.';
			}
		}
		for(int i=7; i>=5; i--){
			for(int j=0; j<8; j++){
				if((i%2==1 && j%2==0)||(i%2==0 && j%2==1))
					tablero[i][j] = 'B';
			}
		}
		for(int i=0; i<=2; i++){
			for(int j=0; j<8; j++){
				if((i%2==1 && j%2==0)||(i%2==0 && j%2==1))
					tablero[i][j] = 'N';
			}
		}
	}
	public char [][]getTablero() { return tablero; }
	
	public void setFicha(int f, int c, char letra) { tablero[f][c]=letra; }
	
	
	public void imprimir(){
		System.out.println(" ");
		for(int i=0; i<8; i++){
			System.out.println(tablero[i]);
		}
	}
	
}
