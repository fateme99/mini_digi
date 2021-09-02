package Model.Good;

import java.util.ArrayList;

public class Characteristic {
    //private static ArrayList<Characteristic> allCharacteristics;
    //private String characteristicName;
    private String characteristicExplanation;

    public Characteristic( String characteristicExplanation) {
        /*allCharacteristics.add(this);
        this.characteristicName = characteristicName;*/
        this.characteristicExplanation = characteristicExplanation;
    }

    /*public String getCharacteristicName() {
        return characteristicName;
    }*/

    public String getCharacteristicExplanation() {
        return characteristicExplanation;
    }
}
