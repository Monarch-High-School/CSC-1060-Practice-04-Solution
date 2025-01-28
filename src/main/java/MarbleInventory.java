/**
 * Describe the class
 * @author 
 * @version 1.0
 * @since 2025-01
 */

import java.util.ArrayList;
public class MarbleInventory {    
    /** The list of marbles */
    private ArrayList<Marble> inventory;
    /** The target radius of the marbles */
    private double targetRadius;
    /** The tolerance in cm of the radius */
    private double tolerance;

    /**
     * Creates a MarbleInventory object and sets instance variables
     * @param tr
     * @param tol
     */
    public MarbleInventory(double tr, double tol) {
        inventory = new ArrayList<Marble>();
        targetRadius = tr;
        tolerance = tol;
    }

    public int getSize() {
        return inventory.size();
    }
    
    public double getTargetRadius() {
        return targetRadius;
    }

    public double getTolerance() {
        return tolerance;
    }

    /**
     * Adds a Marble object if it falls within the tolerance of the target radius of the inventory.
     * @param m The Marble object to add
     * @return true/false whether the Marble was added
     */
    public boolean add(Marble m) {
        if (Math.abs(targetRadius - m.getRadius()) <= tolerance) {
            inventory.add(m);
            return true;
        }
        return false;
    }


    /**
     * Gets the current inventory
     * @return Simply returns a refernece to the inventory
     */
    public ArrayList<Marble> getInventory() {
        return inventory;
    }

    /**
     * Changes the tolerance of deviation from the targetRadius. Removes items from the list outside of the accepted range.
     * @return ArrayList of marbles that were removed
     */
    public ArrayList<Marble> setTolerance(double tol) {
        tolerance = tol; // set new tolerance
        ArrayList<Marble> removed = new ArrayList<Marble>();

        for (int i = inventory.size() - 1; i >= 0; i--) {
            Marble m = inventory.get(i);
                // check if the radius is outside the accepted tolerance
            if (Math.abs(m.getRadius()-targetRadius) > tolerance) {
                removed.add(m);
                inventory.remove(i);
            }
        }
        return removed;
    }

    /**
     * Removes marbles by color
     * @return ArrayList of marbles that were removed
     */
    public ArrayList<Marble> removeByColor(String c) {
        ArrayList<Marble> removed = new ArrayList<Marble>();

        for (int i = inventory.size() - 1; i >= 0; i--) {
            Marble m = inventory.get(i);
                // check if color matches
            if (m.getColor().equals(c)) {
                removed.add(m);
                inventory.remove(i);
            }
        }
        return removed;
    }

}