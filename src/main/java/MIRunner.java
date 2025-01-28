/**
 * This file runs the MarbleInventory and Marble classes
 * @author
 * @version 1.0
 * @since 2025-01
 */

 import java.util.ArrayList;

 public class MIRunner {

    public static void main (String args[]) {
         // test the constants
         System.out.println(Marble.GLASS_DENSITY);
         System.out.println(Marble.AGATE_DENSITY);
         System.out.println(Marble.PLASTIC_DENSITY);

            // try creating an inventory
         System.out.println("Creating marble inventory. ");
         MarbleInventory mi = new MarbleInventory(0.65, 0.1);

         System.out.println("Material: " + mi.getTargetRadius());
         System.out.println(mi.getTolerance());
         
            // create a marble
         System.out.println("Creating marble");
         Marble m1 = new Marble(Marble.MATERIALS.AGATE, 0.65, "blue");

         System.out.println("Material: " + m1.getMaterial());
         System.out.println("Radius: " + m1.getRadius());
         System.out.println("Volume: " +m1.getVolume());
         System.out.println("Mass: " + m1.getMass());
         System.out.println("Color: " + m1.getColor());
         System.out.println("Printable object: " +m1);

         System.out.println("Adding marble to inventory");
         System.out.println("Result: " + mi.add(m1));
         System.out.println("Current inventory: " + mi.getInventory());

         System.out.println("Changing tolerance.");
         ArrayList<Marble> dropped = mi.setTolerance(.005);
         System.out.println("Inventory after tolerance change: " + mi.getInventory());
         System.out.println("Removed items: " + dropped);

            // set tolerance back
         mi.setTolerance(0.1);
         System.out.println("Adding several marbles of various colors.");
         for (int i = 0; i < 10; i++) {
            mi.add(new Marble(Marble.MATERIALS.PLASTIC, 0.65, "red"));
            mi.add(new Marble(Marble.MATERIALS.GLASS, 0.65, "green"));
            mi.add(new Marble(Marble.MATERIALS.AGATE, 0.65, "blue"));
            mi.add(new Marble(Marble.MATERIALS.AGATE, 0.65, "yellow"));
         }
         System.out.println("Inventory after adding: " + mi.getInventory());

         System.out.println("Removing yellow marbles.");
         dropped = mi.removeByColor("yellow");
         System.out.println("Inventory after removal" + mi.getInventory());
         System.out.println("Removed marbles" + dropped);
         
    }
 }