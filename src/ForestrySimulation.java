import java.util.Scanner;
/**
 * The main method enters the forestry simulation
 * @author Danial Haroon
 */
public class ForestrySimulation {

    private static Scanner keyboard = new Scanner(System.in);


    public static void main(String[] args) {
        System.out.println("Welcome to the Forestry Simulation");
        System.out.println("----------------------------------");

        if (args.length == 0) {
            System.out.println("No forests provided.");
            return;
        }

        int forestIndex = 0;

        while (forestIndex < args.length) {

            //Opens up the CSV file from the command line
            String name = args[forestIndex];
            String forestName = args[forestIndex] + ".csv";
            System.out.println("Initializing from " + name);
            Forest currentForest = new Forest(forestName);

            //Checking for valid forest name
            if (!currentForest.loadForest(args[forestIndex] + ".csv")) {
                System.out.println("Error opening/reading " + forestName + ".csv");
                forestIndex++;
                continue;

            }//end of if statement

            boolean exitLoop = false;

            // Do while loop that creates the menu system
            do {
                char choice;
                System.out.print("\n(P)rint, (A)dd, (C)ut, (G)row, (R)eap, (S)ave, (L)oad, (N)ext, e(X)it : ");
                choice = keyboard.next().charAt(0);
                keyboard.nextLine(); // consume newline

                switch (Character.toUpperCase(choice)) {

                    case 'P':
                        currentForest.printForest();
                        break;

                    case 'A':
                        currentForest.addRandomTree();
                        break;

                    case 'C':
                        System.out.print("Tree number to cut down: ");
                        int index;

                        while (!keyboard.hasNextInt()) {
                            System.out.println("That is not an integer");
                            System.out.print("Tree number to cut down: ");
                            keyboard.next();
                        }//end of while loop

                        index = keyboard.nextInt();
                        keyboard.nextLine();
                        currentForest.cutDownTree(index);
                        break;

                    case 'G':
                        currentForest.simulateYear();
                        break;

                    case 'R':
                        System.out.print("Height to reap from: ");
                        double height;

                        while (!keyboard.hasNextDouble()) {
                            System.out.println("That is not an integer");
                            System.out.print("Height to reap from: ");
                            keyboard.next();
                        }//end of while loop

                        height = keyboard.nextDouble();
                        keyboard.nextLine();
                        currentForest.reapForest(height);
                        break;

                    case 'S':
                        currentForest.saveForest();
                        break;

                    case 'L':
                        System.out.print("Enter forest name: ");

                        Forest oldForest = currentForest;

                        String newForestName = keyboard.nextLine();
                        currentForest = new Forest(newForestName);

                        // If loading from the database fails, print an error message
                        if (!currentForest.loadForestDB(newForestName + ".db")) {
                            System.out.println("Error opening/reading " + newForestName + ".db");
                            System.out.println("Old forest retained");
                            currentForest = oldForest; // Reloads the old forest
                        }//end of if statement
                        break;

                    case 'N':
                        System.out.println("Moving to the next forest");
                        exitLoop = true;
                        break;

                    case 'X':
                        System.out.println("\nExiting the Forestry Simulation");
                        return;

                    default:
                        System.out.println("Invalid menu option, try again");
                }

            } while (!exitLoop); // end of do-while

            forestIndex++; // Move to the next forest

        }// end of while loop

    }// end of main method

}// end of Forestry class