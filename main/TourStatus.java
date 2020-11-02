/**This class maintains and updates the tour status of a given Campus object.
 */
package main;

import java.util.ArrayList;

public class TourStatus {
    private Campus campus;
    private Location currentLocation;
    private ArrayList<Item> backpack = new ArrayList();

    /**
     * default constructor for the TourStatus class.
     */
    TourStatus() {
    }

    /**
     * This method sets up the campus that is being toured.
     *
     * @param campus The University to be set up.
     */
    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    /**
     * This method returns the campus which is being toured.
     *
     * @return Campus
     */
    public Campus getCampus() {
        return campus;
    }

    /**
     * This method sets the current location of the tour.
     *
     * @param currentLocation the Location currently at.
     */
    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    /**
     * This method returns the current location of the tour.
     *
     * @return Location
     */
    public Location getCurrentLocation() {
        return currentLocation;
    }

    /**
     * This method takes a String direction.
     * It then determines if there is a door in that direction.
     * If there is a door in that direction then it will set the current location-
     * to that doors entering location.
     *
     * @param direction a String representing a direction.
     */
    public void updateTourLocation(String direction) {
        currentLocation.leaveLocation(direction);

    }

    /**Returns the array list of the backpack items.
     *
     * @return ArrayList
     */
    public ArrayList<Item> getBackpack() {
        return backpack;
    }

    /**
     * This method adds an item to the backpack.
     *
     * @param item Item object to be added.
     */
    public void addToBackpack(Item item) {
        this.backpack.add(item);
    }

    /**
     * This methods removes an item from the backpack.
     *
     * @param item Item object to be removed.
     */
    public void removeFromBackpack(Item item) {
        this.backpack.remove(item);
    }

    /**
     * This method drops a given item based on the name of the item.
     *
     * @param itemNum the number corresponding to the item.
     * @return Returns the dropped item if found, else returns a print statement.
     */
    public Item dropFromBackpack(String itemNum) {
        Item returnMe = null;
        for (Item item : this.backpack) {
            String thisNum = item.getItemNum();
            if (thisNum == itemNum) {
                removeFromBackpack(item);
                returnMe = item;
                break;

            } else {
                System.out.println("This item is not in the backpack.");
            }



        }
        return returnMe;
    }

    /**This method lists all available items in the backpack.
     * @return a String describing all items in the backpack.
     */
    public String listBackpackItems(){
        String returnMe = "";
        for(Item item:this.backpack){
           returnMe = returnMe + item.describe();
        }
        return returnMe;
    }
}
