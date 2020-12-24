package com.game;

import java.util.Random;

public class Board {
    private Tile[][] tiles;
    private int N = 0;
    private int totalRevealed = 0;
    
    private static int[][] neighbors = {{-1,-1}, {-1,0}, {-1,1}, {0,-1}, {0,1}, {1, 0}, {1, 1}, {1, -1}};
    
    public void initialize(int n, int m) throws Exception {
        this.N = n;
        tiles = new Tile[n][n];
        
        // If there are more mines to place than the total tiles, throw an exception
        if (m > n*n) {
            throw new Exception("Invalid mines.");
        }
        
        // Initialize each tile in the grid
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                
                tiles[i][j] = new Tile();
            }
        }
        
        placeMines(m); // time to place the mines
        
    }
    
    /*
        Randomly places mines.
    */
    private void placeMines(int m) {
        // Need to generate random row and column values to place the mine
        Random rand1 = new Random(), rand2 = new Random();
        
        while (m > 0) {
            int i = rand1.nextInt(this.N), j = rand2.nextInt(this.N);
            //System.out.println("i: " + i + "| j: " + j);
                           
            if (!tiles[i][j].hasMine()) {
                tiles[i][j].setHasMine(true);
                
                for (int[] nei : neighbors) {
                    
                    if (i + nei[0] < 0 || j + nei[1] < 0 || i + nei[0] >= this.N || j + nei[1] >= this.N) continue;
                    
                    if (!tiles[i+nei[0]][j+nei[1]].hasMine()) {
                        tiles[i+nei[0]][j+nei[1]].setMineCount(tiles[i+nei[0]][j+nei[1]].getMineCount()+1);
                    }
                }
                
                m--;
            }
        }
    }
    
    public void printBoard() {
        for (Tile[] tile : tiles) {
            
            for (Tile t : tile) {
                if (t.hasMine() && t.isRevealed()){
                    System.out.print("\t " + "*");
                    
                }else {
                 if (t.isRevealed()) {
                    System.out.print("\t " + t.getMineCount());
                 } else {
                     System.out.print("\t -");
                 }
                }
                
            }
            
            System.out.println();
            
        }
    }
    
    /*
     * If clicked on a mine, reveal all the mines and then it's game over.
     * 
     * If revealed all the tiles without stepping on a mine, it's game over too.
     */
    public boolean click(int i, int j) {
        
        if (tiles[i][j].isRevealed()) return false;
        
        if (tiles[i][j].hasMine()) {
            revealAllMines();
            return true;
        }
        
        reveal(tiles[i][j], i, j);
        
        return totalRevealed == (this.N * this.N);
    }
    
    /*
     * Reveals all possible neighbors and their neighbors recursively.
     * 
     */
    private void reveal(Tile curr, int i, int j) {
        
        if (curr.getMineCount() == 0) { // reveal neighboring tiles
            curr.setRevealed(true);
            totalRevealed++;
            
            for (int[] nei : neighbors) {
                if (i + nei[0] < 0 || j + nei[1] < 0 || i + nei[0] >= this.N || j + nei[1] >= this.N) continue;
                
                if (!tiles[i+nei[0]][j+nei[1]].hasMine() && !tiles[i+nei[0]][j+nei[1]].isRevealed()) {
                    //tiles[i+nei[0]][j+nei[1]].revealed = true;
                    reveal(tiles[i+nei[0]][j+nei[1]], i+nei[0], j+nei[1]);
                    
                }
                    
            }
            
        } else {
            curr.setRevealed(true);
        }

    }
    
    private void revealAllMines() {
        for (Tile[] tile : tiles) {
            
            for (Tile t : tile) {
                
                if (t.hasMine()) {
                    t.setRevealed(true);
                    totalRevealed++;
                }
                
            }
        }
    }
    
}
