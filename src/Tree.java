import java.io.Serializable;
import java.util.Random;
/**
 * Represents all the attributes of a tree
 * @author Danial Haroon
 */

public class Tree implements Serializable {

    // Constants
    private static final int MINIMUM_YEAR_PLANTED = 2000;
    private static final int MAXIMUM_YEAR_PLANTED = 25;
    private static final double MINIMUM_HEIGHT = 10.0;
    private static final double MAXIMUM_HEIGHT = 20.0;
    private static final double MINIMUM_GROWTH_RATE = 10.0;
    private static final double MAXIMUM_GROWTH_RATE = 20.0;

    // Variables
    public enum Species { Birch, Maple, Fir }
    private Species species; // tree specie
    private int yearPlanted; // year the tree is planted
    private double height; // height of a tree
    private double growthRate; // growth rate as a percentage

    /**
     * Constructor
     * @param species Species of the Tree.
     * @param yearPlanted Year the Tree was planted.
     * @param height Height of the Tree.
     * @param growthRate Growth rate as a percentage.
     */
    public Tree(Species species, int yearPlanted, double height, double growthRate) {
        this.species = species;
        this.yearPlanted = yearPlanted;
        this.height = height;
        this.growthRate = growthRate;

    }// End of Tree constructor

    /**
     * Generates a random tree
     * @return Tree A tree with random species, year planted, height, and growth rate.
     */
    public static Tree generateRandomTree() {
        Random random = new Random();
        Species[] speciesArray = Species.values();
        Species species = speciesArray[random.nextInt(speciesArray.length)];

        int yearPlanted = MINIMUM_YEAR_PLANTED + random.nextInt(MAXIMUM_YEAR_PLANTED); // Year between 2000 and 2024
        double height = MINIMUM_HEIGHT + random.nextDouble() * MAXIMUM_HEIGHT; // Height between 10 and 20 feet
        double growthRate = MINIMUM_GROWTH_RATE + random.nextDouble() * MAXIMUM_GROWTH_RATE; // Growth rate between 10% and 20%

        return new Tree(species, yearPlanted, height, growthRate);

    }// End of generateRandomTree method

    /**
     * Simulates a year growth of the tree depending on its growth rate.
     */
    public void simulateYear() {
        height *= 1 + growthRate / 100;

    }

    /**
     * Gets the height of a tree.
     * @return height The Tree height in feet.
     */
    public double getHeight() {
        return height;

    }// End of getHeight method

    /**
     * Converts tree variables to CSV format
     * @return String Tree species, year planted, height, and growth rate
     */
    public String toCSV() {
        return species + "," + yearPlanted + "," + height + "," + growthRate;

    }// End of toCSV method

    /**
     * Constructs a string of the tree information.
     *
     * @return String prints out the tree's information
     */
    @Override
    public String toString() {
        return species + " " + yearPlanted + " " + String.format("%.2f", height) + "' " + String.format("%.1f", growthRate) + "%";

    }// End of toString method

}// End of Tree class