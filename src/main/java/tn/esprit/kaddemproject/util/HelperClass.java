package tn.esprit.kaddemproject.util;

import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class HelperClass {



    // when dealing with java.util.Date
    public static long diffInDaysBetween(Date secondDate, Date firstDate){
        long diffInMillies = secondDate.getTime() - firstDate.getTime();
        return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }
}
