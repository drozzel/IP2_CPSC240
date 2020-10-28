/**This class creates a campus object that contains
 * a name and a hashtable of locations.
 */
package main;

import java.util.Hashtable;

public class Campus {
    private String campusName;
    private Hashtable<String,Location> locations = new Hashtable<String,Location>();
    private Location startingLocation;
    private String fileName;

    /**This constructor creates a campus object by pulling data from a file.
     * @param fileName
     */
    Campus(String fileName){
        setFileName(fileName);
    }

    Campus(Location startingLocation, String campusName){
        setStartingLocation(startingLocation);
        setCampusName(campusName);
    }


    /**This method adds a location to the hashtable "locations".
     * The keys are the location name.
     * @param location the new location being added.
     */
    public void addLocation(Location location){
        locations.put(location.getName(),location);
    }

    /**This method returns the location object associated with the given name.
     * @param name a String representing the name of the location.
     * @return Location
     */
    public Location getLocation(String name){
      return locations.get(name);
    }

    /**This method removes the location object associated with the given name.
     * @param name a String representing the name of the location.
     */
    public void removeLocation(String name){
        locations.remove(name);
    }

    /**This method sets the name of the campus.
     * @param campusName
     */
    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    /**This method returns the name of the campus.
     * @return
     */
    public String getCampusName() {
        return campusName;
    }

    /**This method sets the starting location of the campus tour.
     * @param startingLocation
     */
    public void setStartingLocation(Location startingLocation) {
        this.startingLocation = startingLocation;
    }

    /**This method returns the starting location of the campus tour.
     * @return
     */
    public Location getStartingLocation() {
        return startingLocation;
    }
    /**This method sets the name of the campus file.
     * @param fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**This method returns the name of the campus file.
     * @return String
     */
    public String getFileName() {
        return fileName;
    }
}
