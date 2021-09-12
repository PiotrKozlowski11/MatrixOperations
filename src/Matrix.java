import java.util.Arrays;

public class Matrix {
    private final int rows;
    private final int columns;
    private double[][] matrix;

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

    public Matrix(Matrix matrix) {
        this.matrix = matrix.getMatrix().clone();

        rows = this.matrix.length;
        columns = this.matrix[0].length;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public double getValue(int row, int column) {
        return matrix[row][column];
    }

    public void displayArray() {
        for (double[] ints : matrix) {
            for (double anInt : ints)
                System.out.print(anInt + " ");
            System.out.println();
        }
    }

    public Matrix AddMatrices(Matrix secondMatrix) {

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

    public void scalarMultiplication(double multiplyValue) {

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                matrix[i][j] = matrix[i][j] * multiplyValue;

    }

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

    public void transposeMainDiagonal() {
        double[][] result = new double[columns][rows];

        //int columnsNumber = array[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[j][i] = matrix[i][j];
            }
        }

        matrix = result;
    }

    public void transposeSideDiagonal() {
        double[][] result = new double[columns][rows];


        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < columns; j++) {
                result[columns - j - 1][rows - i - 1] = matrix[i][j];
            }
        }

        matrix = result;
    }

    public void transposeVertical() {
        double[][] result = new double[rows][columns];


        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < columns; j++) {
                result[i][rows - j - 1] = matrix[i][j];
            }
        }

        matrix = result;
    }

    public void transposeHorizontal() {
        double[][] result = new double[rows][columns];

        for (int i = 0; i < rows; i++) {

            System.arraycopy(matrix[i], 0, result[rows - i - 1], 0, columns);
        }

        matrix = result;
    }

    public Double getDeterminant() {
        return determinant(matrix);
    }

    private Double determinant(double[][] array) {
        double total = 0;
        if (array.length == 1)
            return array[0][0];
        if (array.length == 2)
            return array[0][0] * array[1][1] - array[0][1] * array[1][0];
        else {

            int n = array.length;

            for (int j = 0; j < n; j++) {
                if (array[j].length != n)
                    throw new IllegalArgumentException("Array columns number is not constant through array");


                double[][] temp = splitArray(array, 0, j);

                int sign = 1;
                if (j % 2 != 0)
                    sign = -1;

                Double det = determinant(temp);
                total += det * array[0][j] * sign;


            }

        }
        return total;

    }

    private double[][] splitArray(double[][] array, int row, int column) {
        int n = array.length - 1;

        if (row >= array.length)
            throw new IllegalArgumentException("Column number cannot be greater than array size");
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

    public void inverseMatrix() {
        Double det = getDeterminant();

        //System.out.println("Determinant: "+det);
        if (det == 0)
            throw new ArithmeticException("This matrix doesn't have an inverse.");
        double[][] cofactors = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                Double detCofactor = determinant(splitArray(matrix, i, j));

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
