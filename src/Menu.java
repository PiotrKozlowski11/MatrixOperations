import java.util.Scanner;

public class Menu {
    private final String[] mainOptions;
    private final String[] options_4;

    public Menu(String[] mainOptions, String[] options_4) {
        this.mainOptions = mainOptions;
        this.options_4 = options_4;
    }

    public int[] displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        int subChoice = -1;

        do {
            //display options
            for (String s : mainOptions)
                System.out.println(s);
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                System.out.println("Sth wrong");
                scanner.nextLine();
                //choice = -1;
            }


        } while (choice >= mainOptions.length || choice < 0);

        if (choice == 4) {
            do {
                //display options
                for (String s : options_4)
                    System.out.println(s);
                if (scanner.hasNextInt()) {
                    subChoice = scanner.nextInt();
                } else {
                    System.out.println("Sth wrong");
                    scanner.nextLine();
                    //choice = -1;
                }

            } while (subChoice > options_4.length || subChoice < 1);

        }
        return new int[]{choice, subChoice};


    }

}
