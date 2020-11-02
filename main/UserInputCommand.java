package main;

/**This is the blueprint for user input command.
 */
public interface UserInputCommand {

    public default String carryOut(TourStatus ts){
       String thisThing= "";
       return thisThing;
    }

}
