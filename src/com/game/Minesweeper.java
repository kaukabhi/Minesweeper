package com.game;

import java.util.Scanner;

/*
 * 
 * Implemented Minesweeper game.
 * 
 */
public class Minesweeper {

	public static void main(String[] args) {
		Board board = new Board();

		try {
			Scanner scanner = new Scanner(System.in);

			int n = 0, m = 0;
			do {
				System.out.println("Enter the grid size (less than or equal to 30): ");
				n = scanner.nextInt();

				System.out.println("Enter the number of mines: ");
				m = scanner.nextInt();
			} while (n > 30);

			board.initialize(n, m);

			System.out.println("Initialized board:");
			board.printBoard();

			boolean gameOver = false;

			int row = 0, col = 0;
			do {
				System.out.println("Row?");
				row = scanner.nextInt();

				System.out.println("Column?");
				col = scanner.nextInt();

				if (row == -1 && col == -1) {
					System.exit(0);
				}

				if ((row >= 0 && row < n) && (col >= 0 && col < n)) {
					gameOver = board.click(row, col);

					if (gameOver) {
						System.out.println("\n\nGame Over");
						board.printBoard();
						System.exit(0);

					} else {
						System.out.println("\n\n");
						board.printBoard();
						System.out.println("\n\n");
					}
				}

			} while (!gameOver);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
