package e.acer_aspire.fooddeliveryservice.handlers;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {
    /**
     * param in is long (E.g: 1545472656340L)
     * @return human readable date format
     */
    public static String getTimeDate(long timestamp){
        SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return sfd.format(new Date(timestamp));
    }
}
