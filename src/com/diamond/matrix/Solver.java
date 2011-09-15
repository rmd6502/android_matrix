package com.diamond.matrix;

public class Solver {
	MatrixAdaptor matrix;
	
	public Solver(MatrixAdaptor _matrix) {
		matrix = _matrix;
	}
	
	public void solve() {
		if (matrix == null || matrix.theMatrix.size() == 0 || matrix.columns == 0) {
			return;
		}
		int matrixSize = matrix.theMatrix.size();
		int numCols = matrix.columns;
		int numRows = matrixSize / numCols;
		for (int pivot = 0; pivot < numRows; ++pivot) {
			int idx = pivot * (numCols + 1);
			while (idx < matrixSize && matrix.theMatrix.get(idx) == 0) {
				idx += numCols;
			}
			if (idx >= matrixSize) {
				continue;
			}
			int row = idx / numCols;
			if (row > pivot) {
				swapRows(row, pivot);
			}
			scalarMultiply(pivot, 1.0/matrix.theMatrix.get(idx));
			idx = pivot;
			for (row = 0; row < numRows; ++row, idx += numCols) {
				if (row == pivot) {
					continue;
				}
				scalarMultiplyAndAdd(pivot, row, -matrix.theMatrix.get(idx));
			}
		}
	}
	
	/** row *= scalar */
	protected void scalarMultiply(int rowNum, double scalar) {
		int idx = rowNum * matrix.columns;
		for (int i=0; i < matrix.columns; ++i) {
			matrix.theMatrix.set(idx + i, scalar * matrix.theMatrix.get(idx+i));
		}
	}
	
	/** swap contents of row1 and row2 */
	protected void swapRows(int row1, int row2) {
		if (row1 == row2) {
			return;
		}
		int idx1 = row1 * matrix.columns;
		int idx2 = row2 * matrix.columns;
		
		for (int i=0; i < matrix.columns; ++i) {
			Double t = matrix.theMatrix.get(idx1+i);
			matrix.theMatrix.set(idx1 + i, matrix.theMatrix.get(idx2+i));
			matrix.theMatrix.set(idx2 + i, t); 
		}
	}
	
	/** row2 += row1 * scalar, row1 unchanged */
	protected void scalarMultiplyAndAdd(int row1, int row2, double scalar) {
		int idx1 = row1 * matrix.columns;
		int idx2 = row2 * matrix.columns;
		for (int i=0; i < matrix.columns; ++i) {
			matrix.theMatrix.set(idx2 + i, matrix.theMatrix.get(idx2+i) + scalar * matrix.theMatrix.get(idx1+i));
		}
	}
}
