package com.game;

public class Tile {
    private boolean hasMine = false;
    private boolean revealed = false;
    private int mineCount = 0;
    
    
	public boolean hasMine() {
		return hasMine;
	}
	public void setHasMine(boolean hasMine) {
		this.hasMine = hasMine;
	}
	public boolean isRevealed() {
		return revealed;
	}
	public void setRevealed(boolean revealed) {
		this.revealed = revealed;
	}
	public int getMineCount() {
		return mineCount;
	}
	public void setMineCount(int mineCount) {
		this.mineCount = mineCount;
	}
    
    
}
