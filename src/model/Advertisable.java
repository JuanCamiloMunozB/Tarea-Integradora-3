package model;

import java.util.Random;

/**
 * The Advertisable interface represents an an entity to which advertisements may be shown.
 */
public interface Advertisable {
    
    /**
     * Generates a random advertisement.
     * @return The generated advertisement as a string.
     */
    public default String generateAdvertisement(){
        String addString = "";
        Random random = new Random();
        int randomNumber = random.nextInt(3) + 1;

        switch(randomNumber){
            case 1:
                addString = "\nSubscribe to Combo Plus and get Disney+ and Star+ at an incredible price!\n";
            break;

            case 2:
                addString = "\nNow your pets have a favorite app: Laika. The best products for your furry friend.\n";
            break;

            case 3:
                addString = "\nIt's our anniversary! Visit your nearest Éxito and be surprised with the best offers.\n";
            break;
        }

        addString += "-if you don't want more ads, switch to premium\n";

        return addString;
    }
}
