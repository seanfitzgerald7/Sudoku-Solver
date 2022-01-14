package sudoku;

/*
 * Backtracking Sudoku solver
 * 
 */

public class Solver {
	
	private static final int GRID_LENGTH = 9;
	private static int[][] board = {
			{7, 0, 2, 0, 5, 0, 6, 0, 0},
			{0, 0, 0, 0, 0, 3, 0, 0, 0},
			{1, 0, 0, 0, 0, 9, 5, 0, 0},
			{8, 0, 0, 0, 0, 0, 0, 9, 0},
			{0, 4, 3, 0, 0, 0, 7, 5, 0},
			{0, 9, 0, 0, 0, 0, 0, 0, 8},
			{0, 0, 9, 7, 0, 0, 0, 0, 5},
			{0, 0, 0, 2, 0, 0, 0, 0, 0},
			{0, 0, 7, 0, 4, 0, 2, 0, 3}
	};
	
	public static void main(String[] args) {
		
		System.out.println("Before Solving: ");
		printBoard(board);
		System.out.println();
		
		if (solveBoard(board)) {
			System.out.println("After Solving: ");
			printBoard(board);
		} else {
			System.out.println("Unsolvable.");
		}
	}
	
	private static void printBoard(int[][] board) {
		for (int i = 0; i < GRID_LENGTH; i++) {
			if (i % 3 == 0 && i != 0) {
				System.out.println("-----------");
			}
			for (int j = 0; j < GRID_LENGTH; j++) {
				if (j % 3 == 0 && j != 0) {
					System.out.print("|");
				}
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
	
	private static boolean isNumberInRow(int[][] board, int row, int number) {
		for (int i = 0; i < GRID_LENGTH; i++) {
			if (board[row][i] == number) return true;
		}
		return false;
	}
	
	private static boolean isNumberInCol(int[][] board, int col, int number) {
		for (int i = 0; i < GRID_LENGTH; i++) {
			if (board[i][col] == number) return true;
		}
		return false;
	}
	
	private static boolean isNumberInSquare(int[][] board, int col, int row, int number) {
		
		int boxRow = row - row % 3;
		int boxCol = col - col % 3;
		for (int i = boxRow; i < boxRow + 3; i++) {
			for (int j = boxCol; j < boxCol + 3; j++) {
				if (board[i][j] == number) return true;
			}
		}
		
		return false;
		
	}
	
	private static boolean isValid(int[][] board, int number, int row, int col) {
		return !isNumberInRow(board, row, number) && 
				!isNumberInCol(board, col, number) &&
				!isNumberInSquare(board, col, row, number);
	}
	
	private static boolean solveBoard(int[][] board) {
		for (int row = 0; row < GRID_LENGTH; row++) {
			for (int col = 0; col < GRID_LENGTH; col++) {
				if (board[row][col] == 0) {
					for (int i = 1; i <= GRID_LENGTH; i++) {
						if (isValid(board, i, row, col)) {
							board[row][col] = i;
							if (solveBoard(board)) return true;
							else {
								board[row][col] = 0;
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	}
	

}
