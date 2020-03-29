import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int[][] id;
	private int[][] status; //1 ako je otovreno, 0 ako je blokirano
	private int N;  //broj kolona ili redova
	private int count; //racuna broj otvorenih polja
	private WeightedQuickUnionUF uf;
	private int virtual_top;
	private int virtual_bottom;
	
	public Percolation(int n){// pravi N x N mrezu, a na pocetku sva polja blokirana
		if (n <= 0) throw new java.lang.IllegalArgumentException("IllegalArgument");
		N = n;
		count = 0;
		uf = new WeightedQuickUnionUF(N*N + 2);
		id = new int[N+1][N+1];
		status = new int[N+1][N+1];
		for(int i = 1; i <= N; i++){
			for(int j = 1; j <= N; j++){
				id[i][j] = (i-1) * N + j-1;
				status[i][j] = 0; 
			}
		}
		virtual_top = N*N;
		virtual_bottom = N*N + 1;
	}
	
	public void open(int row, int col){// otvara kolonu/red ukoliko vec nije otvoren
		checkRange(row, col);
		if(isOpen(row, col));
		else{
			status[row][col] = 1;
			concat(row, col);
			count++;
		}
	}
	
	private void concat(int  row, int col){
		if(row != 1 && isOpen(row - 1, col)){
			union(id[row][col], id[row - 1][col]);
		}else if (row == 1){
			union(id[row][col], virtual_top);
		}
		if(row != N && isOpen(row + 1, col)){
			union(id[row][col], id[row + 1][col]);
		}else if(row == N){
			union(id[row][col], virtual_bottom);
		}
		if(col != 1 && isOpen(row, col - 1)){
			union(id[row][col], id[row][col - 1]);
		}
		if(col != N && isOpen(row, col + 1)){
			union(id[row][col], id[row][col + 1]);
		}
	}
	
	public boolean isOpen(int row, int col){  //da li je kolona/red otvoren
		checkRange(row, col);
		return status[row][col] == 1;
	}
	
    public boolean isFull(int row, int col){  // da li je kolona/red ispunjen
    	checkRange(row, col);
    	return uf.connected(id[row][col], virtual_top);
    }
    
    public int numberOfOpenSites(){// daje broj otvorenih polja
		return count;
    }
    
	public boolean percolates(){ // provera da li sistem ima perkolaciju
		return uf.connected(virtual_top, virtual_bottom);
	}
	private void checkRange(int i, int j){
		if (i <= 0 || j <= 0 || i > N || j > N)
			throw new IndexOutOfBoundsException();
	}
	
	private void union(int p, int q){
		if(!uf.connected(p, q)){
			uf.union(p, q);
		}
	}
}
