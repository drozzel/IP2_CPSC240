package main;

import java.util.Scanner;

public class Item {
    private String name;
    private String message;
    private String startingLocationName;

    /**This is the default constructor of the item class.
     */
    Item(){

    }

    /**This is the parametrized constructor of the item class.
     * @param s a scanner object.
     */
    Item(Scanner s){

    }

    /**This sets the name of the Item.
     * @param name a String representing the name of the item.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**This method returns the name of the Item.
     * @return a String name
     */
    public String getName() {
        return name;
    }

    /**This method sets the  display message of the Item.
     * @param message a String that is displayed when an item is picked up.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**This method returns the display message of the Item.
     * @return String message
     */
    public String getMessage() {
        return message;
    }

    /**This method sets the name of the Starting Location of an object.
     * @param startingLocationName a String representing the name of the starting location.
     */
    public void setStartingLocationName(String startingLocationName) {
        this.startingLocationName = startingLocationName;
    }

    /**This method returns the name of the Starting Location of an object.
     * @return
     */
    public String getStartingLocationName() {
        return startingLocationName;
    }

    /**This method returns a String that contains the items name and the message of the item.
     * @return
     */
    public String describe(){
        String description = "Name: "+ this.getName() + "\n" + "Description: "+ this.getMessage();
        return description;
    }
}
