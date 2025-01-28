/**
 * Solution file for CSC 1060 Practice 04
 * @author J. Cihlar
 * @version 1.0
 * @since 2025-01
 */


public class Marble {
        /** Enumeration representing the type of materials available */
    public enum MATERIALS {GLASS, AGATE, PLASTIC};
        /**  Density of glass in g/cm^3 */
    public final static double GLASS_DENSITY = 2.51;
       /**  Density of glass in g/cm^3 */
    public final static double AGATE_DENSITY = 2.62;
       /**  Density of glass in g/cm^3 */
    public final static double PLASTIC_DENSITY = 2.55;

        /** the marble's material */
    private MATERIALS material;
        /** the radius +/- 10% of the passed in value */
    private double radius;
        /** the marble's volume */
    private double volume;
        /** the marble's mass */
    private double mass;
        /** the marble's color */
    private String color;    

    /**
     * Creates Marble object of the MATERIAL specified with a radius +/- 10% of the color specified.
     * @param m
     * @param r
     * @param c
     */
    public Marble(MATERIALS m, double r, String c) {
        material = m;
        color = c;
            // figure out a deviation
        double dev = (Math.random() * 0.2) - 0.1;
        radius = r * (1 + dev);
            // calculate volume
        volume = (4.0/3.0) * Math.PI * Math.pow(radius, 3);
        double density = 0.0;
            // select correct density
        switch (material) {
            case GLASS:
                density = GLASS_DENSITY;
                break;
            case PLASTIC:
                density = PLASTIC_DENSITY;
                break;
            case AGATE:
                density = AGATE_DENSITY;
                break;
            default:
        }
        mass = density * volume;
    }

    /**
     * Returns a String version of the marble's material
     * @return String version of the material name
     */
    public String getMaterial() {
        switch(material) {
            case MATERIALS.GLASS:
                return "GLASS";
            case MATERIALS.AGATE:
                return "AGATE";
            case MATERIALS.PLASTIC:
                return "PLASTIC";
            default:
                return "";
        }
    }

    public double getRadius() {
        return radius;
    }

    public double getVolume() {
        return volume;
    }

    public double getMass() {
        return mass;
    }
    
    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "{Material: "+ getMaterial() + ", Radius: " + radius + ", Volume: " + volume + ", Mass: " + mass +", Color: "+color+"}";
    }
}