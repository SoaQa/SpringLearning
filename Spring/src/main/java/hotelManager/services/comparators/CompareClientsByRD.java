package hotelManager.services.comparators;

import hotelManager.models.HotelClient;

import java.util.Comparator;

public class CompareClientsByRD implements Comparator<HotelClient> {

    public int compare(HotelClient c1, HotelClient c2) {
        if (c1.getRentDate().isBefore(c2.getRentDate())){
            return -1;
        }
        else if (c2.getRentDate().isBefore(c1.getRentDate())){
            return 1;
        }
        else {
            return 0;
        }
    }
}
