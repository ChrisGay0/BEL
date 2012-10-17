package com.cgsolutions.security.utility;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.context.support.ApplicationObjectSupport;

public class MyDateUtils extends ApplicationObjectSupport{
    public static Map<Integer, Integer> DAYS_IN_MONTH = new HashMap<Integer, Integer>();

    public static final int MINUTES_IN_A_DAY = 1440;
    public static final String STANDARD_DATE_FORMAT = "dd MMM yyyy";

    static {
        DAYS_IN_MONTH.put(Calendar.JANUARY, 31);
        DAYS_IN_MONTH.put(Calendar.FEBRUARY, 28);
        DAYS_IN_MONTH.put(Calendar.MARCH, 31);
        DAYS_IN_MONTH.put(Calendar.APRIL, 30);
        DAYS_IN_MONTH.put(Calendar.MAY, 31);
        DAYS_IN_MONTH.put(Calendar.JUNE, 30);
        DAYS_IN_MONTH.put(Calendar.JULY, 31);
        DAYS_IN_MONTH.put(Calendar.AUGUST, 31);
        DAYS_IN_MONTH.put(Calendar.SEPTEMBER, 30);
        DAYS_IN_MONTH.put(Calendar.OCTOBER, 31);
        DAYS_IN_MONTH.put(Calendar.NOVEMBER, 30);
        DAYS_IN_MONTH.put(Calendar.DECEMBER, 31);
    }
    
    public static Date getEasterDate(int year) {
        Date result = null;

        int a = year % 19;
        int b = year / 100;
        int c = year % 100;
        int d = b / 4;
        int e = b % 4;
        int f = ( b + 8 ) / 25;
        int g = ( b - f + 1 ) / 3;
        int h = ( 19 * a + b - d - g + 15 ) % 30;
        int i = c / 4;
        int k = c % 4;
        int l = (32 + 2 * e + 2 * i - h - k) % 7;
        int m = (a + 11 * h + 22 * l) / 451;
        int p = (h + l - 7 * m + 114) % 31;

        int month = ( h + l - 7 * m + 114 ) / 31;
        int day = p + 1;

        GregorianCalendar gc = new GregorianCalendar(year, month - 1, day);
        result = gc.getTime();

        return result;
    }

    public static boolean areDatesTheSame(Date date1, Date date2){
        return (getStringFromDate(date1, "yyyy-MM-dd").equals(getStringFromDate(date2, "yyyy-MM-dd")));
    }

    public static int daysFromSaturday(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
            return 0;
        }
        return c.get(Calendar.DAY_OF_WEEK);
    }

    public static int daysFromMonday(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if(c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
            return 0;
        }
        else if(c.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY){
            return 1;
        }
        else if(c.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY){
            return 2;
        }
        else if(c.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY){
            return 3;
        }
        else if(c.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY){
            return 4;
        }
        else if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
            return 5;
        }
        else{
            return 6;
        }
        
    }
    public static Date decrementByDays(Date date, int days) {
        Calendar cal = getCalendarFromDate(date);
        cal.add(Calendar.DATE, -days);
        return cal.getTime();
    }

    public static Date decrementByMinutes(Date date, int minutes) {
        Calendar cal = getCalendarFromDate(date);
        cal.add(Calendar.MINUTE, -minutes);
        return cal.getTime();
    }
    
    public static Date decrementByWeeks(Date date, int weeks) {
        Calendar cal = getCalendarFromDate(date);
        cal.add(Calendar.WEEK_OF_YEAR, -weeks);
        return cal.getTime();
    }
    
    public static Date decrementByYears(Date date, int years) {
        Calendar cal = getCalendarFromDate(date);
        cal.add(Calendar.YEAR, -years);
        return cal.getTime();
    }

    public static Date decrementMonth(Date date) {
        Calendar cal = getCalendarFromDate(date);
        cal.add(Calendar.MONTH, -1);
        return cal.getTime();
    }

    public static Calendar getCalendarFromDate(Date date){
        if(date == null){
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }


    /**
     * Assumes that Saturday is the start of the week
     * @param shiftDate
     * @return
     */
    public static Date getDateAtStartOfWeek(Date date){
        return MyDateUtils.decrementByDays(date, MyDateUtils.daysFromSaturday(date));
    }
    
    public static Date getMondayAsStartOfTheWeek(Date date){
        Date monday = MyDateUtils.decrementByDays(date, MyDateUtils.daysFromMonday(date));
        
        return getDateFromString(getStringFromDate(monday, STANDARD_DATE_FORMAT), STANDARD_DATE_FORMAT);
    }
    
    public static Date getDateAtStartOfYear(){
        return getDateFromString("01-01-" + Calendar.getInstance().get(Calendar.YEAR), "dd-MM-yyyy");
    }
    
    public static Date getDateAtEndOfYear(){
        return getDateFromString("31-12-" + Calendar.getInstance().get(Calendar.YEAR), "dd-MM-yyyy");
    }

    public static Date getDateFromDateAndTime(Date date, Time time){
        if(date == null || time == null){
            return null;
        }
        Calendar endDateCalendar = Calendar.getInstance();
        Calendar endTimeCalendar = Calendar.getInstance();
        Calendar endTimestampCalendar = Calendar.getInstance();
        endDateCalendar.setTime(date);
        endTimeCalendar.setTime(time);
        endTimestampCalendar.setTime(time);
        endTimestampCalendar.set(endDateCalendar.get(Calendar.YEAR), endDateCalendar.get(Calendar.MONTH), endDateCalendar.get(Calendar.DATE), endTimeCalendar.get(Calendar.HOUR_OF_DAY), endTimeCalendar.get(Calendar.MINUTE));
        return endTimestampCalendar.getTime();
    }

    public static Date getDateFromString(String dateString, String dateFormatString) {
        if ("0".equals(dateString)) {
            return null;
        }
        
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatString);
        try{
            return dateFormat.parse(dateString);
        }
        catch(ParseException e){
            throw new RuntimeException(e);
        }
    }

    public static int getDaysInMonth(int month, int year){
        if(month == Calendar.FEBRUARY && isGregorianLeapYear(year)){
            return 29;
        }
        return DAYS_IN_MONTH.get(month);
    }
    
    public static final boolean isGregorianLeapYear(int gregorianYear) {
        return (((gregorianYear % 4) == 0) &&
               (((gregorianYear % 100) != 0) || ((gregorianYear % 400) == 0)));
    }

    public static int getDifferenceInDaysBetweenDates(Date date1, Date date2) {
        return (int) (getDifferenceInMillisBetweenDates(date1, date2) / 1000 / 60 /60 / 24);
    }

    public static long getDifferenceInMillisBetweenDates(Date date1, Date date2) {
        if(date1 == null || date2 == null){
            return 0;
        }
        return Math.abs((date1.getTime() - date2.getTime()));
    }

    public static int getDifferenceInMinutesBetweenDates(Date date1, Date date2) {
        if(date1 == null || date2 == null){
            return 0;
        }
        // Round up to the nearest minute ratehr than down
        return (int) Math.ceil(getDifferenceInMillisBetweenDates(date1, date2) / 1000.0 / 60.0);
    }

    public static int getDifferenceInWeeksBetweenDates(Date date1, Date date2) {
        return (int) ((getDifferenceInMillisBetweenDates(date1, date2) + 3600000) / 1000 / 60 /60 / 24 / 7);
        
    }

    public static List<Date> getListOfDatesBetween(Date start, Date end) {
        List<Date> dateList = new ArrayList<Date>();
        for(Date date = start; date.before(MyDateUtils.incrementByDays(end, 1)); date = MyDateUtils.incrementByDays(date, 1)) {
            dateList.add(date);
        }
        return dateList;
    }

    
    public static Date getStartOfDay(Calendar date) {
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        return date.getTime();
    }
    
    public static Date getStartOfDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static String getStringFromDate(Date date, String dateFormatString) {
        if(date == null){
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatString);
        return dateFormat.format(date);
    }

    public static Date getTimeOnly(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.YEAR, 1970);
        c.set(Calendar.MONTH, 1);
        c.set(Calendar.DATE, 1);
        return c.getTime();
    }
    
    public static Timestamp getTimestampFromDateAndTime(Date date, Time time){
        if(date == null || time == null){
            return null;
        }
        Calendar endDateCalendar = Calendar.getInstance();
        Calendar endTimeCalendar = Calendar.getInstance();
        Calendar endTimestampCalendar = Calendar.getInstance();
        endDateCalendar.setTime(date);
        endTimeCalendar.setTime(time);
        endTimestampCalendar.setTime(time);
        endTimestampCalendar.set(endDateCalendar.get(Calendar.YEAR), endDateCalendar.get(Calendar.MONTH), endDateCalendar.get(Calendar.DATE), endTimeCalendar.get(Calendar.HOUR_OF_DAY), endTimeCalendar.get(Calendar.MINUTE));
        return new Timestamp(endTimestampCalendar.getTime().getTime());
    }
    
    public static Date getTomorrowsDate() {
        Calendar tomorrowsDate = Calendar.getInstance();
        tomorrowsDate.setTime(incrementByDays(new Date(), 1));
        return getStartOfDay(tomorrowsDate);
    }

    public static Date incrementByDays(Date date, int days) {
        Calendar cal = getCalendarFromDate(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    public static Date incrementByHours(Date date, int hours) {
        Calendar cal = getCalendarFromDate(date);
        cal.add(Calendar.HOUR_OF_DAY, hours);
        return cal.getTime();
    }
    
    public static Date incrementByMinutes(Date date, int minutes) {
        Calendar cal = getCalendarFromDate(date);
        cal.add(Calendar.MINUTE, minutes);
        return cal.getTime();
    }
    
    public static Date incrementByWeeks(Date date, int weeks) {
        Calendar cal = getCalendarFromDate(date);
        cal.add(Calendar.WEEK_OF_YEAR, weeks);
        return cal.getTime();
    }
    
    public static Date incrementByYears(Date date, int years) {
        Calendar cal = getCalendarFromDate(date);
        cal.add(Calendar.YEAR, years);
        return cal.getTime();
    }

    public static Date incrementMonth(Date date) {
        Calendar cal = getCalendarFromDate(date);
        cal.add(Calendar.MONTH, 1);
        return cal.getTime();
    }
    
    public static Date incrementMinutes(Date date, int minutes) {
        Calendar cal = getCalendarFromDate(date);
        cal.add(Calendar.MONTH, 1);
        return cal.getTime();
    }
    
    public static boolean isDateBetween(Date date, Date startDate, Date endDate) {
        if(date.before(startDate)) {
            return false;
        }
        
        if(date.after(endDate)) {
            return false;
        }
        
        return true;
    }

    /**
     * Determine if the time on the supplied Date is between the time on the other 2 Dates.
     * This method only cares about the time.
     * @param time
     * @param startTime
     * @param endTime
     * @return
     */
    public static boolean isTimeBetween(Date time, Date startTime, Date endTime) {
        Date dTime = MyDateUtils.getDateFromString("01 Jan 2000 "+MyDateUtils.getStringFromDate(time, "HH:mm"), "dd MMM yyyy HH:mm");
        Date dStartTime = MyDateUtils.getDateFromString("01 Jan 2000 "+MyDateUtils.getStringFromDate(startTime, "HH:mm"), "dd MMM yyyy HH:mm");
        Date dEndTime = MyDateUtils.getDateFromString("01 Jan 2000 "+MyDateUtils.getStringFromDate(endTime, "HH:mm"), "dd MMM yyyy HH:mm");
        
        return !dTime.before(dStartTime) && !dTime.after(dEndTime);
    }
    
    public static boolean isItAMonday(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return (c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY);
    }
    
    public static boolean isItATuesday(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return (c.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY);
    }
    
    public static boolean isItAWednesday(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return (c.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY);
    }
    
    public static boolean isItAThursday(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return (c.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY);
    }
    
    public static boolean isItAFriday(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return (c.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY);
    }
    
    public static boolean isItASaturday(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY);
    }
    
    public static boolean isThisDateToday(Date date){
        return (getStringFromDate(date, "yyyy-MM-dd").equals(getStringFromDate(new Date(), "yyyy-MM-dd")));
    }
    public static String renderDayName(int dayOfWeek){
        String dayName = "";
        switch (dayOfWeek) {
        case Calendar.MONDAY:
            dayName =  "Monday";
            break;
        case Calendar.TUESDAY:
            dayName =  "Tuesday";
            break;
        case Calendar.WEDNESDAY:
            dayName =  "Wednesday";
            break;
        case Calendar.THURSDAY:
            dayName =  "Thursday";
            break;
        case Calendar.FRIDAY:
            dayName =  "Friday";
            break;
        case Calendar.SATURDAY:
            dayName =  "Saturday";
            break;
        case Calendar.SUNDAY:
            dayName =  "Sunday";
        default:
            break;
        }
        return dayName;
    }

    public static String renderDayNameShortFormat(int dayOfWeek){
        String dayName = "";
        switch (dayOfWeek) {
        case Calendar.MONDAY:
            dayName =  "Mon";
            break;
        case Calendar.TUESDAY:
            dayName =  "Tue";
            break;
        case Calendar.WEDNESDAY:
            dayName =  "Wed";
            break;
        case Calendar.THURSDAY:
            dayName =  "Thu";
            break;
        case Calendar.FRIDAY:
            dayName =  "Fri";
            break;
        case Calendar.SATURDAY:
            dayName =  "Sat";
            break;
        case Calendar.SUNDAY:
            dayName =  "Sun";
        default:
            break;
        }
        return dayName;
    }
    
    public static String renderMonthName(int month){
        String monthName = "";
        switch (month) {
        case Calendar.JANUARY:
            monthName =  "January";
            break;
        case Calendar.FEBRUARY:
            monthName =  "February";
            break;
        case Calendar.MARCH:
            monthName =  "March";
            break;
        case Calendar.APRIL:
            monthName =  "April";
            break;
        case Calendar.MAY:
            monthName =  "May";
            break;
        case Calendar.JUNE:
            monthName =  "June";
            break;
        case Calendar.JULY:
            monthName =  "July";
            break;
        case Calendar.AUGUST:
            monthName =  "August";
            break;
        case Calendar.SEPTEMBER:
            monthName =  "September";
            break;
        case Calendar.OCTOBER:
            monthName =  "October";
            break;
        case Calendar.NOVEMBER:
            monthName =  "November";
            break;
        case Calendar.DECEMBER:
            monthName =  "December";
            break;
        default:
            break;
        }
        return monthName;
    }
    
    public static Date setTimeToOneMinuteToMidnight(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        return c.getTime();
    }
    
    public static Date setTimeToMidnight(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        return c.getTime();
    }
    
    public static Date removeZoneOffsetFromDate(Date inputDate){
        long initialDate = inputDate.getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(inputDate);
        initialDate -= calendar.getTimeZone().getDSTSavings();
        return new Date(initialDate);
    }
    
    /**
     * Converts a number of minutes into the format HH:MM, for example
     * 90 minutes will become 01:30.
     * @param minutes
     * @return
     */
    public static String convertMinutesToHours(Integer minutes) {
        if(minutes > 0) {
            Integer numHours = minutes / 60;
            Integer numMinutes = minutes - (numHours * 60);
        
            String hrs = numHours.toString();
            String mins = numMinutes.toString();
            if(numHours < 10) {
                hrs = "0" + hrs;
            }
            if(numMinutes < 10) {
                mins = "0" + mins;
            }
            return hrs + ":" + mins;
        }
        else {
            return "00:00";
        }
    }
    
    /**
     * 
     * @param date
     * @param time
     * @param incrementDay Increments the day to support going past midnight.
     * @return
     */
    public static Date combineDateAndTime(Date date, Date time, boolean incrementDay) {
        String dateFormat = "dd MMM yyyy HH:mm";
        String d = MyDateUtils.getStringFromDate(date, "dd MMM yyyy");
        String t = MyDateUtils.getStringFromDate(time, "HH:mm");
        Date result = MyDateUtils.getDateFromString(d + " " + t, dateFormat);
        if(incrementDay) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(result);
            cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
        }
        return result;
    }
}