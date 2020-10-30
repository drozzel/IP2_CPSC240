/**This class creates a location object that consists of a name,
 * a brief description,as well as if the location is outside(meaning it must be visited
 * multiple times),  as well as if it has already been visited too.
 * It also contains an ArrayList of all the possible doors.
 *
 */
package main;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Location {
    private String name;
    private String description;
    private boolean haveVisited;
    private boolean isOutside;
    private ArrayList<Door> doors = new ArrayList<Door>();
    private ArrayList<Item> items = new ArrayList<>();

    /**
     * This is the default constructor for the location class.
     */
    Location() {
    }

    /**
     * A constructor for a location that takes the file as an argument in order to create a location object.
     *
     * @param input
     */
    Location(Scanner input) {

    }

    /**
     * Sets the name of the location.
     *
     * @param name a String used to identify the location.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the location.
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the description of the location.
     *
     * @param description a brief summary of a location.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the description of the location.
     *
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method sets a true value if the location is located outside.
     *
     * @param outside
     */
    public void setOutside(boolean outside) {
        isOutside = outside;
    }

    /**
     * This method returns true if the location is located outside.
     *
     * @return boolean
     */
    public boolean isOutside() {
        return isOutside;
    }

    /**
     * This methods sets a true value if the location has been visited.
     *
     * @param haveVisited
     */
    public void setHaveVisited(boolean haveVisited) {
        this.haveVisited = haveVisited;
    }

    /**
     * This method returns a true value if the location has been visited.
     *
     * @return
     */
    public boolean isHaveVisited() {
        return haveVisited;
    }

    /**
     * This method returns the ArrayList of doors.
     *
     * @return ArrayList<Door>
     */
    public ArrayList<Door> getDoors() {
        return doors;
    }

    /**
     * Returns all the possible doors connected to the location as a String.
     *
     * @return String.
     */

    public String getDoorsDescription() {
        String theDoors = "";
        for (Door door : doors) {
            theDoors = theDoors + " " + "\n" + door.describe();
        }
        return theDoors;
    }

    /**
     * This method adds a door to the existing list of doors.
     *
     * @param door the door being added.
     */
    public void addDoor(Door door) {
        doors.add(door);
    }

    /**
     * This method takes a direction and leaves the current Location through a door.
     * It then returns the name of the Location that door led to.
     *
     * @param direction
     * @return Location
     */
    public Location leaveLocation(String direction) {

        Location validLocation = null;
        for (Door door : doors) {
            String doorDirection = door.getDirection();
            if (doorDirection.contains(direction)) {
                validLocation = (door.getEnteringLocation());
                break;
            }
            else {
                validLocation = null;
            }
        }
        if(validLocation==null){
            System.out.println("There is no valid door in this direction.");
        }
        else{

        }
        return validLocation;
    }

    /**This method adds an Item to the ArrayList of items.
     * @param item the Item object being added.
     */
    public void addItem(Item item){
        items.add(item);
    }

    /**This method removes an Item
     * @param item the Item object being removed.
     */
    public void removeItem(Item item){
        items.remove(item);
    }

    /**This method returns an Item object given the name as a String.
     * @param name a String representing the name of the Item.
     * @return If there is an item with the name it will return the item.
     * Else it will print a message and return a null item.
     */
    public Item getItemNamed(String name) {
        Item returnMe = new Item();
        for (Item item : items) {
            if (item.getName().equals(name)) {
                returnMe = item;
            } else {
                System.out.println("No such item exists.");
            }

        }
        return returnMe;
    }

    /**This method returns the ArrayList of items at this location.
     * @return
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    public String doorDescriptions(){
        String description = "These are the possible doors for this location.";
        for(Door door: this.doors){
            description =description +"\n"+ door.describe();

        }
        return description;
    }
    public String itemDescription(){
        if(this.items.size()<0) {
            String description = "These are the available items for this location.";
            for (Item door : this.items) {
                description = description + "\n" + door.describe();

            }
        }
        else{
            description = "";
        }
        return description;
    }
}


