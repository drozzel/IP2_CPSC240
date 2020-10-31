/**Tour University
 * This program is designed to take user input to generate a campus object.
 * It then takes the user throughout the campus allowing them to input directional commands.
 * The user may quit the program at any time by entering "q".
 * @author Daniel Rozzel
 * @version 1.0
 */
package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import static java.lang.System.exit;


public class TourUMW {
    private static Scanner stdin = new Scanner(System.in);




    public static void main(String[] args) {




        Campus umw = setUpCampus(stdin);
        TourStatus umwTour = new TourStatus();
        umwTour.setCampus(umw);
        umwTour.setCurrentLocation(umw.getStartingLocation());
        String exit = null;




           Location currently = umwTour.getCurrentLocation();
           System.out.println(currently.getName());
           System.out.println(currently.getDescription());
           System.out.println("These are the possible doors for this location.");
           for (Door door : currently.getDoors()) {
               System.out.println(door.describe());
           }
           System.out.println("These are the available items for this location.");
           for (Item item : currently.getItems()) {
               System.out.println(item.describe() + "\n");
           }
           currently.setHaveVisited(true);




       while (exit == null){
           System.out.println("Which direction would you like to go to next?");








            String result =(promptUser(stdin).carryOut(umwTour));
            if(result.contains("exit")){
                System.out.println(result);
                exit ="exit";
            }
            else{
                System.out.println(result);
            }









        }
        System.exit(1);

    }


    /**
     * This method takes a scanner and returns any user input given.
     *
     * @param input The name of the scanner
     * @return
     */
    public static UserInputCommand promptUser(Scanner input) {
        String userInput = input.nextLine();
        ArrayList<String> directions = new ArrayList<>();
        directions.add("n");
        directions.add("e");
        directions.add("s");
        directions.add("w");
        UserInputCommand result = null;
        if (directions.contains(userInput)) {
            result = new MovementCommand(userInput);

        }
        else if(userInput.contains("q")){
            result = new ExitCommand(userInput);
        }
        else if(userInput.contains("txt")){
            result = new FileCommand(userInput);
        }
        else{
            result = new InvalidCommand(userInput);
        }
        return result;


    }


    /**
     * This method creates a completed campus object from a text file.
     *
     * @param input The name of the scanner that will read the file.
     * @return Campus
     */

    public static Campus setUpCampus(Scanner input) {
        TourStatus random = null;

        String fileName = null;
        boolean fileNotFound = true;
        Campus campus = null;
        while (fileNotFound) {
            System.out.println("Enter the file name containing the information of the campus.");


            fileName = promptUser(input).carryOut(random);
            if (fileName.contains("exit")) {
                System.out.println(fileName);
                System.exit(1);
            }
            campus = new Campus(fileName);
            File file = new File(fileName);
            ArrayList<String> firstRead = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String currentLine;
                while ((currentLine = br.readLine()) != null) {
                    firstRead.add(currentLine);
                }


                /**This if statement will run if the file contains the proper format.
                 */
                fileNotFound = false;
                if (firstRead.contains("*****") && firstRead.contains("+++")) {
                    ArrayList<String> fileInfo = null;
                    try {
                        fileInfo = new ArrayList<>();
                        input = new Scanner(file).useDelimiter("\\+\\+\\+|\\*\\*\\*\\*\\*|:|\r\n");
                        while (input.hasNext()) {
                            String fileOutput = input.next().replaceAll("(?m)^\\s", "");
                            fileInfo.add(fileOutput);
                        }


                    } catch (FileNotFoundException e) {
                        System.out.println("File not found.");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    /** Code to differentiate between the doors and locations.
                     */
                    int locationsIndex = 0;
                    int doorsIndex = 0;
                    int itemsIndex = 0;
                    for (int counter = 0; counter < fileInfo.size(); counter++) {
                        String output = fileInfo.get(counter);
                        if (output.contains("Locations")) {
                            locationsIndex = counter;
                        } else if (output.contains("Doors")) {
                            doorsIndex = counter;
                        } else if (output.contains("Items")) {
                            itemsIndex = counter;
                        }

                    }

                    /**Create all the location objects without their doors.
                     */
                    ArrayList<Location> umwLocations = new ArrayList<>();
                    List<String> locationsInfo = fileInfo.subList(locationsIndex, doorsIndex);
                    locationsInfo.remove(0);
                    for (String location : locationsInfo) {
                        Location newLocation = new Location();
                        String[] locationInfo = location.split("\\r?\\n|\\r");
                        newLocation.setName(locationInfo[0]);
                        String description = "";
                        for (int counter = 1; counter < locationInfo.length; counter++) {
                            description = description + " " + locationInfo[counter];
                        }
                        newLocation.setDescription(description);
                        if (locationInfo[0].contains("Double Drive") || locationInfo[0].contains("Campus Walk") || locationInfo[0].contains("Link") || locationInfo[0].contains("Bell")) {
                            newLocation.setOutside(true);

                        } else {
                            newLocation.setOutside(false);
                        }
                        umwLocations.add(newLocation);
                    }
                    /**Create all the door objects.
                     */
                    ArrayList<Door> umwDoors = new ArrayList<>();
                    List<String> doorsInfo = fileInfo.subList(doorsIndex, itemsIndex - 2);
                    for (String door : doorsInfo) {
                        String[] doorInfo = door.split("\\r?\\n|\\r");
                        String leavingLocationName = doorInfo[0].stripTrailing();
                        String direction = doorInfo[1].stripTrailing();
                        String enteringLocationName = doorInfo[2].stripTrailing();
                        Location leavingLocation = null;
                        Location enteringLocation = null;
                        int counter = 0;
                        while (leavingLocation == null) {
                            Location location = umwLocations.get(counter);
                            if (location.getName().contains(leavingLocationName)) {
                                leavingLocation = location;

                            } else {

                            }
                            counter++;

                        }
                        counter = 0;
                        while (enteringLocation == null) {
                            Location location = umwLocations.get(counter);
                            if (location.getName().contains(enteringLocationName)) {
                                enteringLocation = location;

                            } else {

                            }
                            counter++;

                        }

                        Door newDoor = new Door(direction, leavingLocation, enteringLocation);
                        umwDoors.add(newDoor);

                    }
                    /**Add the corresponding doors to each location.
                     */
                    for (Location location : umwLocations) {
                        for (Door door : umwDoors) {
                            if (door.getLeavingLocation().getName().contains(location.getName())) {
                                location.addDoor(door);
                            } else {

                            }

                        }
                    }
                    /**Create all the items.
                     */
                    ArrayList<Item> umwItems = new ArrayList<>();
                    List<String> itemsInfo = fileInfo.subList(itemsIndex, fileInfo.size() - 1);
                    for (String item : itemsInfo) {
                        Item newItem = new Item();
                        String[] itemInfo = item.split("\\r?\\n|\\r");
                        newItem.setName(itemInfo[0]);
                        newItem.setStartingLocationName(itemInfo[1]);
                        String message = "";
                        for (int counter = 2; counter < itemInfo.length; counter++) {
                            message = message + itemInfo[counter];

                        }
                        newItem.setMessage(message);
                        umwItems.add(newItem);
                    }

                    /**Add all the items to their corresponding locations.
                     */
                    for (Location location : umwLocations) {
                        for (Item item : umwItems) {
                            if (item.getStartingLocationName().contains(location.getName())) {
                                location.addItem(item);
                            } else {

                            }

                        }
                    }


                    /**Add the completed locations to the Campus hashtable "locations".
                     */
                    for (Location location : umwLocations) {
                        campus.addLocation(location);
                    }
                    campus.setStartingLocation(umwLocations.get(0));

                } else {
                    System.out.println("The file format is incorrect.Please rerun the program and enter a file with the proper format.");


                }
            } catch (IOException e) {
                System.out.println("File not found.");
                fileNotFound = true;
            }
        }
        return campus;
    }

    /**A command type that controls user movement.
     *
     */
    public static class MovementCommand implements UserInputCommand {
        private String direction;

        public MovementCommand(String direction) {
            this.direction = direction;


        }

        @Override
        /**This method returns a String that contains a description of an updated location.
         * This description includes the name, description,doors and items of a location.
         * If there is no valid location it will print the current location and ask the user to reeneter a valid direction.
         * @param ts The tourStatus object that will be updated.
         */
        public String carryOut(TourStatus ts) {
            Location currently = ts.getCurrentLocation();
            Location possibleUpdate = currently.leaveLocation(direction);
            String carryPrint = "";
            if(possibleUpdate == null) {
                carryPrint = "You are currently at " + currently.getName()+"\n";
                carryPrint = carryPrint + currently.doorDescriptions()+"\n";
                carryPrint = carryPrint + currently.itemDescriptions();
                carryPrint = carryPrint+ "\n Please enter a valid direction.";


            }
            else  {
                ts.setCurrentLocation(possibleUpdate);
                currently = ts.getCurrentLocation();
                if (currently.isOutside() && currently.isHaveVisited()) {
                    carryPrint=(currently.getName()+"\n");
                    carryPrint = carryPrint + currently.doorDescriptions()+"\n";
                    carryPrint = carryPrint + currently.itemDescriptions();


                }
                else if (currently.isOutside() && !currently.isHaveVisited()) {
                    carryPrint=(currently.getName()+"\n");
                    carryPrint = carryPrint + currently.getDescription()+"\n";
                    carryPrint = carryPrint + currently.doorDescriptions()+"\n";
                    carryPrint = carryPrint + currently.itemDescriptions();

                }
                else if (!currently.isOutside() && !currently.isHaveVisited()) {
                    carryPrint=(currently.getName()+"\n");
                    carryPrint = carryPrint + currently.getDescription()+"\n";
                    carryPrint = carryPrint + currently.doorDescriptions()+"\n";
                    carryPrint = carryPrint + currently.itemDescriptions();



                }
                else{
                    carryPrint=(currently.getName()+"\n");
                    carryPrint = carryPrint + currently.doorDescriptions()+"\n";
                    carryPrint = carryPrint + currently.itemDescriptions();

                }


            }

            currently.setHaveVisited(true);


            return carryPrint;
        }


    }

    /**This command notifies the user they will now exit the program.
     */
    public static class ExitCommand implements UserInputCommand{

        private String exit;

        ExitCommand(String exit){
            this.exit = exit;
        }

        @Override
        public String carryOut(TourStatus ts){
            String nowExit = "You will now exit the program.";
            return nowExit;

        }

    }

    /**This command returns the fileName it was given.
     */
    public static class FileCommand implements UserInputCommand{
        private String fileName;

        FileCommand(String fileName){
            this.fileName = fileName;
        }


        public String carryOut(TourStatus ts) {
            return fileName;
        }

    }

    /**this command notifies the user that the command they entered is invalid.
     */
    public static class InvalidCommand implements UserInputCommand{
        private String invalidInput;

        InvalidCommand(String invalidInput){
            this.invalidInput = invalidInput;
        }

        public String carryOut(TourStatus ts){
            String result ="This command is invalid please enter a new command.";

            return result;

        }

    }
    
}

