/**This class creates a door object that consists of a direction.
 * And an entering and leaving location.
 */
package main;

import java.util.Scanner;

public class Door {
    private String direction;
    private Location leavingLocation;
    private Location enteringLocation;

    Door(String direction,Location leavingLocation, Location enteringLocation){
        setDirection(direction);
        setEnteringLocation(enteringLocation);
        setLeavingLocation(leavingLocation);
    }
    Door(Scanner input,Campus campus){

    }

    /**This method returns a description of the door.
     * It notifies the user that if it heads in the direction-
     * they can get to the leaving location.
     * @return String
     */
    public String describe(){
        String described = "You can go " + direction + " to get to " + enteringLocation.getName()+".";
        return described;
    }

    /**This sets the direction the door is located.
     * @param direction
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**This method returns the direction the door is located.
     * @return String
     */
    public String getDirection() {
        return direction;
    }

    /**This method sets the location through which a door is entered.
     * @param enteringLocation
     */
    public void setEnteringLocation(Location enteringLocation) {
        this.enteringLocation = enteringLocation;
    }

    /**This method returns the location through which a door is entered.
     * @return Location
     */
    public Location getEnteringLocation() {
        return enteringLocation;
    }

    /**This method sets the location through which a door was left.
     * @param leavingLocation
     */
    public void setLeavingLocation(Location leavingLocation) {
        this.leavingLocation = leavingLocation;
    }

    /**This method returns the location through which a door was left.
     * @return
     */
    public Location getLeavingLocation() {
        return leavingLocation;
    }
}
