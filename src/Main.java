import java.util.Scanner;

class Main {
    public static void main(String[] args) {


        Operations();

    }


    public static void Operations() {
        String[] menuOptions = {"1. Add matrices", "2. Multiply matrix by a constant", "3. Multiply matrices",
                "4. Transpose matrix", "5. Calculate a determinant", "6. Inverse matrix", "0. Exit"};
        String[] menuOptions4 = {"1. Main diagonal", "2. Side diagonal", "3. Vertical line", "4. Horizontal line"};
        Menu menu = new Menu(menuOptions, menuOptions4);

        int[] menuChoice = menu.displayMenu();

        switch (menuChoice[0]) {
            case 1:

                Matrix matrix1 = new Matrix(getArray(1));
                Matrix matrix2 = new Matrix(getArray(2));

                new Matrix(matrix1.AddMatrices(matrix2)).displayArray();

                break;
            case 2:

                matrix1 = new Matrix(getArray(0));

                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter constant:");
                int constant = scanner.nextInt();

                matrix1.scalarMultiplication(constant);
                matrix1.displayArray();

                break;
            case 3:

                matrix1 = new Matrix(getArray(1));
                matrix2 = new Matrix(getArray(2));

                try {
                    new Matrix(matrix1.multiplyMatrices(matrix2)).displayArray();
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }

                break;
            case 4:
                switch (menuChoice[1]) {
                    case 1 -> {
                        matrix1 = new Matrix(getArray(0));
                        matrix1.transposeMainDiagonal();
                        System.out.println("The result is:");
                        matrix1.displayArray();
                    }
                    case 2 -> {
                        matrix1 = new Matrix(getArray(0));
                        matrix1.transposeSideDiagonal();
                        System.out.println("The result is:");
                        matrix1.displayArray();
                    }
                    case 3 -> {
                        matrix1 = new Matrix(getArray(0));
                        matrix1.transposeVertical();
                        System.out.println("The result is:");
                        matrix1.displayArray();
                    }
                    case 4 -> {

                        matrix1 = new Matrix(getArray(0));
                        matrix1.transposeHorizontal();
                        System.out.println("The result is:");
                        matrix1.displayArray();
                    }
                    default -> System.out.println("Invalid return type");
                }
                break;
            case 5:
                matrix1 = new Matrix(getArray(0));

                System.out.println("The result is:");
                System.out.println(matrix1.getDeterminant());
                break;
            case 6:
                matrix1 = new Matrix(getArray(0));
                try {
                    matrix1.inverseMatrix();

                    System.out.println("The result is:");
                    matrix1.displayArray();
                } catch (ArithmeticException e) {
                    System.out.println(e.getMessage());
                }


                break;
            case 0:
                System.exit(0);
            default:
                System.out.println("Invalid return type");

        }
    }



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







}

