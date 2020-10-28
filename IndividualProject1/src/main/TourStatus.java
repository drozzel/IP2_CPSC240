/**This class maintains and updates the tour status of a given Campus object.
 */
package main;

public class TourStatus {
    private Campus campus;
    private Location currentLocation;

    TourStatus(){}

    /**This method sets up the campus that is being toured.
     * @param campus
     */
    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    /**This method returns the campus which is being toured.
     * @return Campus
     */
    public Campus getCampus() {
        return campus;
    }

    /**This method sets the current location of the tour.
     * @param currentLocation
     */
    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    /**This method returns the current location of the tour.
     * @return
     */
    public Location getCurrentLocation() {
        return currentLocation;
    }

    /**This method takes a String direction.
     * It then determines if there is a door in that direction.
     * If there is a door in that direction then it will set the current location-
     * to that doors entering location.
     * @param direction a String representing a direction.
     */
    public void updateTourLocation(String direction){
        currentLocation.leaveLocation(direction);

    }
}
