import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;


class MarbleTest {

    @Test
    public void testConstants() {
        double DELTA = 1e-6;
        String [] names = new String[] {"Glass", "Agate", "Plastic"};
        double [] expected = {2.51, 2.62, 2.55};
        double [] actual = new double[] {Marble.GLASS_DENSITY, Marble.AGATE_DENSITY, Marble.PLASTIC_DENSITY};
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i], DELTA, "Testing constant for density of " + names[i] + ". Expected: " + expected[i] + ", but got " + actual[i]);
        }
    }

    @Test
    public void testMarbleInventoryConstructor() {
        double DELTA = 1e-6; // delta should be small
        MarbleInventory mi = new MarbleInventory(0.65, 0.0325);
        double expectedTR = 0.65;
        double actualTR = mi.getTargetRadius();
        assertEquals(expectedTR, actualTR, DELTA, "Checking target radius for marble inventory: " + expectedTR + ", but got " + actualTR);
        
        double expectedTol = 0.0325;
        double actualTol = mi.getTolerance();
        assertEquals(expectedTol, actualTol, DELTA, "Checking tolerance for marble inventory: " + expectedTol + ", but got " + actualTol);
    }   

    @Test
    public void testMarbleGetMaterial() {
        Marble m = new Marble(Marble.MATERIALS.AGATE, 0.65, "blue");
        String expected = "AGATE";
        String actual = m.getMaterial();
        assertEquals(expected, actual, "Checking marble material: " + expected + ", but got " + actual);
    }   

    @Test
    public void testMarbleGetRadius() {
        double radius = 0.65;
        double DELTA = radius * 0.1; // delta is +/- 10%
        Marble m = new Marble(Marble.MATERIALS.AGATE, 0.65, "blue");
        double expected = 0.65;
        double actual = m.getRadius();
        assertEquals(expected, actual, DELTA, "Checking marble radius: " + expected + ", but got " + actual);
    }   

    @Test
    public void testMarbleGetVolume() {
        double DELTA = 0.39; // for this radius, we need to be within 0.39 cm^3
        Marble m = new Marble(Marble.MATERIALS.AGATE, 0.65, "blue");
        double expected = 1.15034651;
        double actual = m.getVolume();
        assertEquals(expected, actual, DELTA, "Checking marble volume: " + expected + ", but got " + actual);
    }   

    @Test
    public void testMarbleGetMassAgate() {
        double DELTA = 1.0; // the mass needs to be within 1
        Marble m = new Marble(Marble.MATERIALS.AGATE, 0.65, "blue");
        double expected = 3.013907856;
        double actual = m.getMass();
        assertEquals(expected, actual, DELTA, "Checking marble mass for agate: " + expected + ", but got " + actual);
    } 

    @Test
    public void testMarbleGetMassGlass() {
        double DELTA = 0.96; // the mass needs to be within 0.96
        Marble m = new Marble(Marble.MATERIALS.GLASS, 0.65, "blue");
        double expected = 2.88736974;
        double actual = m.getMass();
        assertEquals(expected, actual, DELTA, "Checking marble mass for glass: " + expected + ", but got " + actual);
    }       

    @Test
    public void testMarbleGetMassPlastic() {
        double DELTA = 0.98; // the mass needs to be within 0.98
        Marble m = new Marble(Marble.MATERIALS.PLASTIC, 0.65, "blue");
        double expected = 2.9333836;
        double actual = m.getMass();
        assertEquals(expected, actual, DELTA, "Checking marble mass for plastic: " + expected + ", but got " + actual);
    }       

    @Test
    public void testMarbleGetColor() {
        Marble m = new Marble(Marble.MATERIALS.AGATE, 0.65, "blue");
        String expected = "blue";
        String actual = m.getColor();
        assertEquals(expected, actual, "Checking marble color: " + expected + ", but got " + actual);
    }     

    @Test
    public void testMarbleToString() {
        Marble m = new Marble(Marble.MATERIALS.AGATE, 0.65, "blue");
        String expected = "{Material: AGATE, Radius: 0.637, Volume: 1.082696932, Mass: 2.836665963, Color: blue}";
        String actual = m.toString();
        assertEquals(expected, actual, "Checking marble toString(): " + expected + ", but got " + actual);
    }    

    @Test
    public void testAddMarbleSuccessful() {
        Marble m = new Marble(Marble.MATERIALS.AGATE, 0.65, "blue");
        MarbleInventory mi = new MarbleInventory(0.65, 0.0325);
        assertTrue(mi.add(m), "Add marble within tolerance to inventory, but failed.");
    } 
    
    @Test
    public void testAddMarbleUnsuccessful() {
        Marble m = new Marble(Marble.MATERIALS.GLASS, 0.5, "red");
        MarbleInventory mi = new MarbleInventory(0.65, 0.0325);
        assertFalse(mi.add(m), "Add marble outside of tolerance to inventory, but marble was added.");
    }         

    @Test
    public void testSetTolerance() {
        MarbleInventory mi = new MarbleInventory(0.65, 0.06);
            // create and add 10 marbles within tolerance
        for (int i = 0; i < 10; i++) {
            mi.add(new Marble(Marble.MATERIALS.GLASS, 0.65, "red"));
        }

            // create a list of marbles 
        ArrayList<Marble> keep = new ArrayList<Marble>();
        ArrayList<Marble> drop = new ArrayList<Marble>();
        
        double DELTA = 0.03;
        for (Marble m : mi.getInventory()) {
            if (Math.abs(m.getRadius() - 0.65) <= DELTA) {
                keep.add(m);
            }
            else {
                drop.add(m);
            }
        }
        
        ArrayList<Marble> actualRemoved = mi.setTolerance(DELTA);

        assertEquals(drop, actualRemoved, "Set tolerance test did not return correct result for items to remove.");
        assertEquals(keep, mi.getInventory(), "Set tolerance test did not return correct result for items to remove.");
    }   

    @Test
    public void testRemoveByColor() {
        MarbleInventory mi = new MarbleInventory(0.65, 0.06);
            // create and add 10 marbles within tolerance
        for (int i = 0; i < 10; i++) {
            mi.add(new Marble(Marble.MATERIALS.GLASS, 0.65, "red"));
            mi.add(new Marble(Marble.MATERIALS.GLASS, 0.65, "blue"));
            mi.add(new Marble(Marble.MATERIALS.GLASS, 0.65, "green"));
        }

            // create a list of marbles 
        ArrayList<Marble> keep = new ArrayList<Marble>();
        ArrayList<Marble> drop = new ArrayList<Marble>();
        
        for (Marble m : mi.getInventory()) {
            if (m.getColor().equals("green")) {
                drop.add(m);
            }
            else {
                keep.add(m);
            }
        }
        
        ArrayList<Marble> actualRemoved = mi.removeByColor("green");

        assertEquals(drop, actualRemoved, "Remove by color did not remove correct items.");
        assertEquals(keep, mi.getInventory(), "Remove by color did not keep correct items.");
    }   
}
