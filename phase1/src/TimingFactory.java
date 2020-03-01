
import java.time.*;
/**
 * This class creates instances of Timing with given string input
 *
 * @author Daniel Shoichet
 *
 */
public class TimingFactory {

    public void TimingFactory(){}

    /**
     * returns an instance of Timing with a start time but no end time
     *
     *
     * @param year year of this Timing
     * @param month month of this Timing
     * @param dayOfMonth dayOfMonth of this Timing
     * @param hour the hour for this Timing
     * @param minute the minute for this Timing
     * @return a new Timing object as specified
     */
    public Timing createTiming(int year, String month, int dayOfMonth, int hour, int minute){
        return new Timing(LocalDateTime.of(year, getEnumMonth(month), dayOfMonth, hour, minute));
    }

    /**
     * returns an instance of Timing with a start time and an end time. The first set of parameters is for start time
     * and the second set of parameters is for end time.
     *
     * @param year1 the year of the start of this timing
     * @param month1 the month of the start of this timing
     * @param dayOfMonth1 the dayOfMonth of the start of this timing
     * @param hour1 the hour of the start of this timing
     * @param minute1 the minute of the start of this timing
     * @param year2 the year of the end of this timing
     * @param month2 the month of the end of this timing
     * @param dayOfMonth2 the dayOfMonth of the end of this timing
     * @param hour2 the hour of the end of this timing
     * @param minute2 the minute of the end of this timing
     * @return
     */
    public Timing createTiming(int year1, String month1, int dayOfMonth1, int hour1, int minute1, int year2,
                               String month2, int dayOfMonth2, int hour2, int minute2){
        return new Timing(LocalDateTime.of(year1, getEnumMonth(month1), dayOfMonth1, hour1, minute1),
                LocalDateTime.of(year2, getEnumMonth(month2), dayOfMonth2, hour2, minute2));
    }

    private Month getEnumMonth(String month){
        switch (month.toUpperCase()){
            case "JANUARY":
                return  Month.of(1);
            case "FEBRUARY":
                return  Month.of(2);
            case "MARCH":
                return  Month.of(3);
            case "APRIL":
                return  Month.of(4);
            case "MAY":
                return  Month.of(5);
            case "JUNE":
                return  Month.of(6);
            case "JULY":
                return  Month.of(7);
            case "AUGUST":
                return  Month.of(8);
            case "SEPTEMBER":
                return  Month.of(9);
            case "OCTOBER":
                return  Month.of(10);
            case "NOVEMBER":
                return  Month.of(11);
            case "DECEMBER":
                return  Month.of(12);
            default:
                return null;

        }
    }
}
