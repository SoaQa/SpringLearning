package hotelManager.services.comparators;

import hotelManager.models.HotelClient;

import java.util.Comparator;

public class CompareClientsByName implements Comparator<HotelClient> {
    public int compare(HotelClient o1, HotelClient o2) {
        int res = String.CASE_INSENSITIVE_ORDER.compare(o1.getFirstName(), o2.getFirstName());
        if (res == 0) {
            res = o1.getFirstName().compareTo(o2.getFirstName());
        }
        return res;

    }
}
