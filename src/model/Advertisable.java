package model;

import java.util.Random;;

public interface Advertisable {
    
    public default String getAdvertisement(){
        String addString = "";
        Random random = new Random();
        int randomNumber = random.nextInt(3) + 1;

        switch(randomNumber){
            case 1:
                addString = "Subscribe to Combo Plus and get Disney+ and Star+ at an incredible price!";
            break;

            case 2:
                addString = "Now your pets have a favorite app: Laika. The best products for your furry friend.";
            break;

            case 3:
                addString = "It's our anniversary! Visit your nearest Éxito and be surprised with the best offers.";
            break;
        }

        return addString;
    }
}
