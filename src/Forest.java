import java.io.*;
import java.util.ArrayList;
/**
 * Forest that can contain multiple tree objects.
 * @author Danial Haroon
 */
public class Forest {

    // Variables
    private String name; //name of the forest
    private ArrayList<Tree> trees; //list of all trees in the forest

/**
 * Constructs a forest.
 * @param name The name of the forest.
*/
public Forest(String name) {
        this.name = name;
        this.trees = new ArrayList<>();

    }// end of Forest constructor

    /**
     * Loads data from a CSV file and populates the forest.
     * @param csvFilePath The path to the CSV file with Tree data.
     * @return true If loading is successful.
     * @return false If loading is unsuccessful.
     * @throws IOException If an I/O occurs while reading the file.
     * @throws NumberFormatException If the format of the data in the file is incorrect.
     */
    public boolean loadForest(String csvFilePath) {

        //Reads the CSV file
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;


            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Tree.Species species = convertSpecies(data[0]);

                //checks if species is valid
                if (species == null) {
                    System.out.println("Invalid species in the CSV file.");
                    return false;
                }//end of if statement

                //Converts the data into Tree data
                int yearPlanted = Integer.parseInt(data[1]);
                double height = Double.parseDouble(data[2]);
                double growthRate = Double.parseDouble(data[3]);

                Tree tree = new Tree(species, yearPlanted, height, growthRate);
                trees.add(tree);
            }//end of while loop

            return true;

        } catch (IOException | NumberFormatException e) {
            return false;

        }// end of try-catch block

    }// end of loadForest method

    /**
     * Loads a previously saved db file.
     * @param dbfileName The name of the db file.
     * @return true If the file opens
     * @return false If the file does not open
     */
    public boolean loadForestDB(String dbfileName) {

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(dbfileName))) {

            trees = (ArrayList<Tree>) inputStream.readObject();
            return true;

        } catch (IOException | ClassNotFoundException exception) {
            return false;

        }// end of try-catch block

    }// end of loadForestFromDB method

    /**
     * Converts String value to its corresponding enum value
     * @param speciesString The string value of the species.
     * @return species The enum value of the species string.
     * @return null If the species string has no corresponding enum.
     */

    private Tree.Species convertSpecies(String speciesString) {


        for (Tree.Species species : Tree.Species.values()) {

            if (species.toString().equalsIgnoreCase(speciesString)) {
                return species;
            }//end of if statement
        }//end of for loop
        return null;

    }// end of convertToSpeciesEnum method

    /**
     * Prints the forest
     */
    public void printForest() {
        System.out.println("Forest name: " + name);

        int index;

        for (index = 0; index < trees.size(); index++) {
            System.out.println("     " + index + " " + trees.get(index));
        }//end of for loop

        System.out.println("There are " + trees.size() + " trees, with an average height of " + calculateAverageHeight());

    }// end of printForest method

    /**
     * Adds a random tree to the forest.
     */
    public void addRandomTree() {

        Tree tree = Tree.generateRandomTree();
        trees.add(tree);

    }// end of addRandomTree method

    /**
     * Removes a tree from the forest
     * @param index The index of the tree to be removed.
     */
    public void cutDownTree(int index) {

        // Make sure tree number is valid
        if (index >= 0 && index < trees.size()) {
            trees.remove(index);

        } else {
            System.out.println("Tree number " + index + " does not exist");
        }//end of if-else statement

    }// end of cutDownTree method

    /**
     * Simulates a year growth of all trees in the forest
     */
    public void simulateYear() {

        for (Tree tree : trees) {
            tree.simulateYear();
        }//end of for loop


    }// end of simulateYear method

    /**
     * Reaps and replaces trees from the forest that are taller than a specified height.
     * @param height Minimum height of the tree to be reaped
     */
    public void reapForest(double height) {

        int index;
        for (index = 0; index < trees.size(); index++) {

            // checks if the trees are tall enough
            if (trees.get(index).getHeight() > height) {

                System.out.println("Reaping the tall tree " + trees.get(index));

                // generates a random tree to replace the reaped tree with
                Tree newTree = Tree.generateRandomTree();
                trees.set(index, newTree);
                System.out.println("Replaced with new tree " + newTree);
            }//end of if statement

        }// end of for loop

    }// end of reapForest method

    /**
     * Saves the forest to a db file.
     * @throws IOException If file cannot be saved as a db file.
     */
    public void saveForest() {

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(name + ".db"))) {

            outputStream.writeObject(trees);

        } catch (IOException exception) {

            exception.printStackTrace();

        }// end of try-catch block

    }// end of saveForest method

    /**
     * Calculates the average height of all trees in the forest.
     * @return averageHeight Average height of all trees rounded to two decimal places.
     */
    private double calculateAverageHeight() {


        double totalHeight = 0;
        for (Tree tree : trees) {
            totalHeight += tree.getHeight();
        }//end of for loop

        // Divide total height by amount of trees
        double averageHeight = trees.isEmpty() ? 0 : totalHeight / trees.size();

        return Math.round(averageHeight * 100.0) / 100.0;

    }// end of calculateAverageHeight method

}// end of Forest class