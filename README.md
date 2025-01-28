# CSC 1060 Practice 04

In this practice you will implement classes that help a toy marble factory maintain quality control. The first class represents a Marble that has a diameter, mass, density, and color. The second class keeps an inventory of marbles produced that fall within acceptable tolerances (a tolerance is the amount of difference you can *tolerate* from an accepted value).

Unfortunately, this toy marble factory is not using best practices, and it will produce marbles that are between 10% below or 10% above the radius specified. 

## Task
1. Implement the `Marble` class in the `Marble.java` file. Implement the `MarbleInventory` Test that your class works by creating and object and testing the methods in the `MIRunner.java` class.
2. Complete the REFLECTIONS.md file, part of the reflection will be to generate JavaDoc. You do not need to generate getter/setter documentation if it doesn't exist.

## Usage Table
The following table illustrates all the methods you need to create along with their effect/return values.
## Example Data
| Code Statement | Effect / Return|
| :--- | :---  |
| `Marble.MATERIALS` | An enumeration (see starter code) for the materials available |
| `Marble.GLASS_DENSITY` | `2.51` The mass (g) of glass per cm^3  |
| `Marble.AGATE_DENSITY` | `2.62` The mass (g) of agate per cm^3 |
| `Marble.PLASTIC_DENSITY` | `2.55` The mass (g) of plastic per cm^3 | 
| `Marble.GLASS_DENSITY = 2.20;` | Fails. Error is "You cannot assign a value to final variable GLASS_DENSITY" |
| `MarbleInventory mi = new MarbleInventory(0.65, 0.0325); `| Creates a new `MarbleInventory`. It will only add marbles that have a target radius of 0.65cm ± 0.0325cm.  |
| `mi.getTargetRadius();`|  `0.65` |
| `mi.getTolerance();`|  `0.0325` |
|`Marble m1 = new Marble(Marble.MATERIALS.AGATE, 0.65, "blue");` | Creates a new blue marble made of agate with radius of 0.65cm with a randomly generated deviation of [10%, 10%). In this case, the radius was -2% off: .65 + (0.65 * -0.02) = 0.637 |
| `m1.getMaterial();` | String `"AGATE"` |
| `m1.getRadius();` | double `0.637` |
| `m1.getVolume();` | double `1.082696932` |
| `m1.getMass();` | double `2.836665963` |
| `m1.getColor();` | String `blue` |
| `System.out.println(m1);` | Calls overridden `toString()` method. `toString()` returns String in the format `{Material: AGATE, Radius: 0.637, Volume: 1.082696932, Mass: 2.836665963, Color: "blue"}` |
| `mi.add(m1);` | Adds `m1` to the inventory and returns `true` to indicate successful adding. | 
| `mi.getSize();` | `1` the current size of the list |
| `mi.getInventory()` | Returns an `ArrayList<Marble>` that contains the marbles in the list. `[{Material: AGATE, Radius: 0.637, Volume: 1.082696932, Mass: 2.836665963, Color: blue}]` | 
|`m2 = new Marble(Marble.MATERIALS.AGATE, 0.65, "red");` | Creates a new red marble made of agate. The radius deviation is +7% or 0.0455cm - 0.6955cm |
|`mi.add(m2);` | Does not add `m2` to the inventory because it is outside the acceptable tolerances. Returns `false`.|
| `mi.setTolerance(0.04);` | Adjusts the tolerance to 0.04. Any Marbles outside of the range [0.61, 0.69] are removed. Returns an `ArrayList<Marble>` of the removed `Marble` objects.|
 | `mi.removeByColor("blue");` | Removes all marbles that are blue. Returns an `ArrayList<Marble>` of the removed `Marble` objects. |
 

## Unit Tests
There are units tests to check the constructors of both classes, the getters, and each of the methods. To run the unit tests manually run the command `gradle test` at the terminal.