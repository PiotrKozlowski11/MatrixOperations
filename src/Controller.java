import java.util.Scanner;

public class Controller {

    // Main menu
    Menu mainMenu;
    // subMenu - transpose matrix
    Menu subMenuTranspose;

    /**
     * initialise all menus
     */
    public Controller() {
        initializeMenu();
    }

    /**
     * Create two-dimensional array from user input.
     *
     * @param matrixNumber int - parameter used to ask user which number of matrix is being created.
     *                     1 - First, 2 - Second, else - ""
     * @return double[][] - returned array
     */
    public static double[][] getArray(int matrixNumber) {
        String matrixName;
        if (matrixNumber == 1)
            matrixName = "first ";
        else if (matrixNumber == 2)
            matrixName = "second ";
        else
            matrixName = "";

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter size of " + matrixName + "matrix:");

        int a = scanner.nextInt();
        int b = scanner.nextInt();
        double[][] array = new double[a][b];

        System.out.println("Enter " + matrixName + " matrix:");

        for (int i = 0; i < array.length; i++)
            for (int j = 0; j < array[i].length; j++)
                array[i][j] = scanner.nextDouble();
        return array;

    }

    /**
     * Construct and initialize menus
     */
    private void initializeMenu() {
        mainMenu = new Menu();
        subMenuTranspose = new Menu();

        mainMenu.putItemList(1, "Add matrices");
        mainMenu.putItemList(2, "Multiply matrix by a constant");
        mainMenu.putItemList(3, "Multiply matrices");
        mainMenu.putItemList(4, "Transpose matrix");
        mainMenu.putItemList(5, "Calculate a determinant");
        mainMenu.putItemList(6, "Inverse matrix");
        mainMenu.putItemList(0, "Exit");

        subMenuTranspose.putItemList(1, "Main diagonal");
        subMenuTranspose.putItemList(2, "Side diagonal");
        subMenuTranspose.putItemList(3, "Vertical line");
        subMenuTranspose.putItemList(4, "Horizontal line");

    }

    /**
     * Main program loop with all options
     */
    public void operations() {


        while (true) {
            switch (mainMenu.getChoice()) {
                case 1 -> {

                    Matrix matrix1 = new Matrix(getArray(1));
                    Matrix matrix2 = new Matrix(getArray(2));

                    try {
                        new Matrix(matrix1.AddMatrices(matrix2)).displayArray();
                        System.out.println();
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }

                }
                case 2 -> {

                    Matrix matrix = new Matrix(getArray(0));

                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Enter constant:");
                    int constant = scanner.nextInt();

                    matrix.scalarMultiplication(constant);
                    matrix.displayArray();

                }
                case 3 -> {

                    Matrix matrix1 = new Matrix(getArray(1));
                    Matrix matrix2 = new Matrix(getArray(2));

                    try {
                        new Matrix(matrix1.multiplyMatrices(matrix2)).displayArray();
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }


                }
                case 4 -> menuTransposeMatrix();
                case 5 -> {
                    Matrix matrix = new Matrix(getArray(0));

                    System.out.println("The result is:");
                    System.out.println(matrix.getDeterminant());

                }
                case 6 -> {
                    Matrix matrix = new Matrix(getArray(0));
                    try {
                        matrix.inverseMatrix();

                        System.out.println("The result is:");
                        matrix.displayArray();
                    } catch (ArithmeticException e) {
                        System.out.println(e.getMessage());
                    }


                }
                case 0 -> System.exit(0);


            }
        }
    }

    /**
     * submenu - selecting which transpose should be done.
     */
    private void menuTransposeMatrix() {

        switch (subMenuTranspose.getChoice()) {
            case 1 -> {
                Matrix matrix = new Matrix(getArray(0));
                matrix.transposeMainDiagonal();
                System.out.println("The result is:");
                matrix.displayArray();
            }
            case 2 -> {
                Matrix matrix = new Matrix(getArray(0));
                matrix.transposeSideDiagonal();
                System.out.println("The result is:");
                matrix.displayArray();
            }
            case 3 -> {
                Matrix matrix = new Matrix(getArray(0));
                matrix.transposeVertical();
                System.out.println("The result is:");
                matrix.displayArray();
            }
            case 4 -> {

                Matrix matrix = new Matrix(getArray(0));
                matrix.transposeHorizontal();
                System.out.println("The result is:");
                matrix.displayArray();
            }
        }

    }
}
