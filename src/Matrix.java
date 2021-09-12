import java.util.Arrays;

/**
 * class represents Matrix. Matrix should have constant amount of columns in each row.
 * Following options are available for matrix operations:
 * 1. Display of a matrix
 * 2. Matrix addition
 * 3. Scalar multiplication
 * 4. Multiplication of matrices
 * 5. Transposing matrix (by Main, Side Diagonal, Vertical, Horizontal)
 * 6. Inversion of a matrix
 */
public class Matrix {
    // number of matrix rows
    private int rows;
    // number of matrix columns
    private int columns;
    // matrix representation as an array
    private double[][] matrix;

    /**
     * Constructor of a matrix class from two-dimensional array.
     * Array has to have constant number of columns through all rows.
     *
     * @param matrix double[][] - matrix representation
     */
    public Matrix(double[][] matrix) {
        rows = matrix.length;
        columns = matrix[0].length;
        this.matrix = new double[rows][];
        for (int i = 0; i < rows; i++) {
            this.matrix[i] = Arrays.copyOf(matrix[i], matrix[i].length);
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i].length != columns) {
                    throw new IllegalArgumentException("Matrix should have constant amount of columns for each row");
                }
            }
        }
    }

    /**
     * Constructor of a matrix class from another matrix
     *
     * @param matrix Matrix - constructing class from the same class
     */
    public Matrix(Matrix matrix) {
        this.matrix = matrix.getMatrix().clone();

        rows = this.matrix.length;
        columns = this.matrix[0].length;
    }

    /**
     * Getter for matrix
     *
     * @return double[][] - matrix representation
     */
    public double[][] getMatrix() {
        return matrix;
    }

    /**
     * Get a single value from an array
     *
     * @param row    int - row of an array
     * @param column int - column of an array
     * @return double - value of an array
     */
    private double getValue(int row, int column) {
        return matrix[row][column];
    }

    /**
     * Display an array
     */
    public void displayArray() {
        for (double[] ints : matrix) {
            for (double anInt : ints)
                System.out.print(anInt + " ");
            System.out.println();
        }
    }

    /**
     * Add two matrices.
     * Matrices have to have the same size - same number of rows and columns
     *
     * @param secondMatrix Matrix - another matrix to be added
     * @return Matrix - new matrix is returned
     */
    public Matrix AddMatrices(Matrix secondMatrix) throws IllegalArgumentException {

        if (secondMatrix.rows != rows)
            throw new IllegalArgumentException("Can't add arrays of different rows numbers");
        if (secondMatrix.columns != columns)
            throw new IllegalArgumentException("Can't add arrays of different columns numbers");

        //Matrix result;
        double[][] result = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++)
                result[i][j] = matrix[i][j] + secondMatrix.getValue(i, j);
        }


        return new Matrix(result);

    }

    /**
     * Multiply whole matrix by another value
     *
     * @param multiplyValue double - value by which matrix will be multiplied
     */
    public void scalarMultiplication(double multiplyValue) {

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                matrix[i][j] = matrix[i][j] * multiplyValue;

    }

    /**
     * Multiply two matrices.
     * Number of columns of first matrix have to be the same as a number of rows of second matrix
     *
     * @param secondMatrix Matrix - Second matrix by which it will be multiplied.
     * @return Matrix - new matrix is returned
     */
    public Matrix multiplyMatrices(Matrix secondMatrix) {


        if (columns != secondMatrix.rows)
            throw new IllegalArgumentException("Wrong amount of rows of second matrix");

        double[][] result = new double[rows][secondMatrix.columns];


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < secondMatrix.columns; j++) {
                for (int k = 0; k < columns; k++) {
                    result[i][j] += matrix[i][k] * secondMatrix.getValue(k, j);

                }

            }
        }
        return new Matrix(result);
    }

    /**
     * Transposing whole matrix by a main diagonal
     */
    public void transposeMainDiagonal() {
        double[][] result = new double[columns][rows];

        //int columnsNumber = array[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[j][i] = matrix[i][j];
            }
        }

        matrix = result;
        adjustRowColumns();
    }

    /**
     * Transposing whole matrix by a side diagonal
     */
    public void transposeSideDiagonal() {
        double[][] result = new double[columns][rows];


        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < columns; j++) {
                result[columns - j - 1][rows - i - 1] = matrix[i][j];
            }
        }

        matrix = result;
        adjustRowColumns();
    }

    /**
     * Method used during transposing of a matrix to adjust number of rows and columns.
     */
    private void adjustRowColumns() {
        rows = matrix.length;
        columns = matrix[0].length;
    }

    /**
     * Transposing whole matrix vertically
     */
    public void transposeVertical() {
        double[][] result = new double[rows][columns];


        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < columns; j++) {
                result[i][columns - j - 1] = matrix[i][j];
            }
        }

        matrix = result;

    }

    /**
     * Transposing whole matrix horizontally
     */
    public void transposeHorizontal() {
        double[][] result = new double[rows][columns];

        for (int i = 0; i < rows; i++) {

            System.arraycopy(matrix[i], 0, result[rows - i - 1], 0, columns);
        }

        matrix = result;

    }

    /**
     * Get determinant of a matrix
     *
     * @return double - determinant of a matrix
     */
    public double getDeterminant() {
        return determinant(matrix);
    }

    /**
     * Method used to calculate determinant of a matrix recursively.
     *
     * @param array double[][] - array of which determinant will be calculated
     * @return double - determinant of an array
     */
    private double determinant(double[][] array) {
        double total = 0;
        if (array.length == 1)
            return array[0][0];
        if (array.length == 2)
            return array[0][0] * array[1][1] - array[0][1] * array[1][0];
        else {

            int n = array.length;

            for (int j = 0; j < n; j++) {

                double[][] temp = splitArray(array, 0, j);

                int sign = 1;
                if (j % 2 != 0)
                    sign = -1;

                double det = determinant(temp);
                total += det * array[0][j] * sign;


            }

        }
        return total;

    }

    /**
     * Method to split an array into another one - delete all rows and columns given as a parameter.
     *
     * @param array  double[][] - array from which row and column should be deleted
     * @param row    int - row number to be deleted
     * @param column int - column number to be deleted
     * @return double[][] new array without selected row and column
     */
    private double[][] splitArray(double[][] array, int row, int column) {
        int n = array.length - 1;

        if (row >= array.length)
            throw new IllegalArgumentException("Row number cannot be greater than array size");
        if (column >= array[0].length)
            throw new IllegalArgumentException("Column number cannot be greater than array size");
        int addColumn;
        int addRow;
        double[][] result = new double[n][n];

        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                //System.out.println(Arrays.toString(array[i+1]));

                if (j >= column)
                    addColumn = 1;
                else
                    addColumn = 0;
                if (i >= row)
                    addRow = 1;
                else
                    addRow = 0;

                result[i][j] = array[i + addRow][j + addColumn];
            }
        }
        return result;
    }

    /**
     * Method to inverse whole matrix.
     */
    public void inverseMatrix() {
        double det = getDeterminant();

        //System.out.println("Determinant: "+det);
        if (det == 0)
            throw new ArithmeticException("This matrix doesn't have an inverse.");
        double[][] cofactors = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                double detCofactor = determinant(splitArray(matrix, i, j));

                int sign = 1;
                if ((i + j) % 2 != 0)
                    sign = -1;

                cofactors[i][j] = detCofactor * sign;
            }
        }
        matrix = cofactors;
        transposeMainDiagonal();
        scalarMultiplication(1.0 / det);

    }


}
