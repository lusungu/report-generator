package com.elucive.reportgenerator.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.TimeZone;


public class DateUtil {

	/**
	 * Date when WCFCB set minimum pension at K125.00
	 * @return date
	 */
	public static Date penCommencementDateThreshold() {
		Calendar commencementDate = Calendar.getInstance();
		commencementDate.set(Calendar.MONTH, Calendar.MARCH);
		commencementDate.set(Calendar.DAY_OF_MONTH, 31);
		commencementDate.set(Calendar.YEAR, 2017);
		commencementDate.set(Calendar.HOUR_OF_DAY, 23);
		commencementDate.set(Calendar.MINUTE, 59);
		commencementDate.set(Calendar.SECOND, 59);
		return commencementDate.getTime();
	}
	
	public static Date firstDayOfThisYear() {
		Calendar startDate = Calendar.getInstance();
		startDate.set(getYear(), Calendar.JANUARY, 1, 0, 0, 0);
		return startDate.getTime();
	}
	
	/**
	 * Gets the last day of the year. i.e. 31-December-{financialYear} 23:59:59
	 */
	public static Date endDate(Integer financialYear) {
		Calendar endDate = Calendar.getInstance();

		endDate.set(Calendar.MONTH, Calendar.DECEMBER);
		endDate.set(Calendar.DAY_OF_MONTH, 31);
		endDate.set(Calendar.YEAR, financialYear);
		endDate.set(Calendar.HOUR_OF_DAY, 23);
		endDate.set(Calendar.MINUTE, 59);
		endDate.set(Calendar.SECOND, 59);
		return endDate.getTime();
	}

	public static Date startDate(Integer financialYear) {
		Calendar startDate = Calendar.getInstance();
		startDate.set(financialYear, Calendar.JANUARY, 1, 0, 0, 0);
		return startDate.getTime();
	}

	/**
	 * Returns current year as an Integer
	 * */
	public static Integer getYear() {
		Calendar startDate = Calendar.getInstance();
		return startDate.get(Calendar.YEAR);
	}
	
	/**
	 * Gets this years year
	 * @return
	 */
	public static int getThisYearsYear() {
		Calendar startDate = Calendar.getInstance();
		return startDate.get(Calendar.YEAR);
	}

	/**
	 * Calculate number of years. 
	 * @param first (startDate) oldest
	 * @param last (end Date) new
	 * @return
	 */
	public static int getDiffYears(Date first, Date last) {
		return calculateAge(first, last);
	}
	
	/**
     * Calculates the age of a person in years based on their date of birth and a reference date.
     *
     * @param dateOfBirth The date of birth of the person.
     * @param toDate      The reference date for calculating the age.
     * @return The age of the person in years.
     * @throws IllegalArgumentException If either the dateOfBirth or toDate is null.
     */
	public static int calculateAge(Date dateOfBirth, Date toDate) {
		if (dateOfBirth == null || toDate == null) {
            throw new IllegalArgumentException("Date of birth and toDate must not be null.");
        }

        LocalDate birthday = convertToLocalDate(dateOfBirth);
        LocalDate today = convertToLocalDate(toDate);

        Period p = Period.between(birthday, today);
        return p.getYears();
	}
	
	/**
     * Converts a java.util.Date object to a LocalDate object.
     *
     * @param date The java.util.Date object to be converted.
     * @return The equivalent LocalDate object.
     */
	private static LocalDate convertToLocalDate(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

	public static Calendar getCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	
	/**
	 * 
	 * @param date
	 * @return date as string d-m-Y
	 */
	public static String getDateAsString(Date date) {
		return String.format("%1$td-%1$tm-%1$tY", date);
	}



	public static Date startDate(Date startDate) {
		
		Locale locale = new Locale("en", "ZM");
		TimeZone tz = TimeZone.getTimeZone("Africa/Lusaka");
		
		Calendar sDate = Calendar.getInstance(tz, locale);
		sDate.setTime(startDate == null ? new Date() : startDate);
		sDate.set(Calendar.MINUTE, 0);
		sDate.set(Calendar.SECOND, 0);
		sDate.set(Calendar.HOUR_OF_DAY, 0);
		return sDate.getTime();

	}
	
	public static Date endDate(Date endDate) {		
		Locale locale = new Locale("en", "ZM");
		TimeZone tz = TimeZone.getTimeZone("Africa/Lusaka");
		
		Calendar sDate = Calendar.getInstance(tz, locale);
		sDate.setTime(endDate == null ? new Date() : endDate);
		sDate.set(Calendar.HOUR_OF_DAY, 23);
		sDate.set(Calendar.MINUTE, 59);
		sDate.set(Calendar.SECOND, 59);
		return sDate.getTime();

	}
	
	/**
	 * 1-January-{THIS_YEAR}
	 */
	public static Date thisStartDate() {
		Calendar sDate = Calendar.getInstance();
		sDate.set(Calendar.DAY_OF_MONTH, 1);
		sDate.set(Calendar.MONTH, 0);
		sDate.set(Calendar.YEAR, getYear());
		sDate.set(Calendar.HOUR_OF_DAY, 0);
		sDate.set(Calendar.MINUTE, 0);
		sDate.set(Calendar.SECOND, 0);
		return sDate.getTime();
	}
	
	/**
	 * This years last day
	 * 31-DECEMBER-{THIS_YEAR}
	 * @return
	 */
	public static Date thisEndDate() {
		Calendar sDate = Calendar.getInstance();
		sDate.set(Calendar.DAY_OF_MONTH, 31);
		sDate.set(Calendar.MONTH, Calendar.DECEMBER);
		sDate.set(Calendar.YEAR, getYear());
		sDate.set(Calendar.HOUR_OF_DAY, 23);
		sDate.set(Calendar.MINUTE, 59);
		sDate.set(Calendar.SECOND, 59);
		return sDate.getTime();
	}
	
	public static Date prevStartDate() {
		Calendar sDate = Calendar.getInstance();
		sDate.set(Calendar.DAY_OF_MONTH, 1);
		sDate.set(Calendar.MONTH, 0);
		sDate.set(Calendar.YEAR, getYear() - 1);
		sDate.set(Calendar.HOUR_OF_DAY, 0);
		sDate.set(Calendar.MINUTE, 0);
		sDate.set(Calendar.SECOND, 0);
		return sDate.getTime();
	}
	
	public static Date prevEndDate() {
		Calendar sDate = Calendar.getInstance();
		sDate.set(Calendar.DAY_OF_MONTH, 31);
		sDate.set(Calendar.MONTH, 11);
		sDate.set(Calendar.YEAR, getYear() - 1);
		sDate.set(Calendar.HOUR_OF_DAY, 23);
		sDate.set(Calendar.MINUTE, 59);
		sDate.set(Calendar.SECOND, 59);
		return sDate.getTime();
	}
	
	public static Date previousYearDateToday() {
		Integer prevYear = getYear() - 1;
		Calendar sDate = Calendar.getInstance();
		sDate.set(Calendar.YEAR, prevYear);
		return sDate.getTime();
	}
	
	/**
	 * Returns number of days between two calendar dates. Takes daylight savings into account.
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int daysBetween(Calendar startDate, Calendar endDate){
	
	    Calendar dayOne = (Calendar) startDate.clone(),
	            dayTwo = (Calendar) endDate.clone();

	    if (dayOne.get(Calendar.YEAR) == dayTwo.get(Calendar.YEAR)) {
	        return Math.abs(dayOne.get(Calendar.DAY_OF_YEAR) - dayTwo.get(Calendar.DAY_OF_YEAR));
	    } else {
	        if (dayTwo.get(Calendar.YEAR) > dayOne.get(Calendar.YEAR)) {
	            //swap them
	            Calendar temp = dayOne;
	            dayOne = dayTwo;
	            dayTwo = temp;
	        }
	        int extraDays = 0;

	        int dayOneOriginalYearDays = dayOne.get(Calendar.DAY_OF_YEAR);

	        while (dayOne.get(Calendar.YEAR) > dayTwo.get(Calendar.YEAR)) {
	            dayOne.add(Calendar.YEAR, -1);
	            // getActualMaximum() important for leap years
	            extraDays += dayOne.getActualMaximum(Calendar.DAY_OF_YEAR);
	        }

	        return extraDays - dayTwo.get(Calendar.DAY_OF_YEAR) + dayOneOriginalYearDays ;
	    }
	}
	
	/**
	 * Gets the number of days between startDate and endDate. Does not take into accout daylight savings
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws IOException
	 */
	public static int daysBetween(Date startDate, Date endDate) throws IOException {
		Long daysBetween = ChronoUnit.DAYS.between(startDate.toInstant(), endDate.toInstant());
		return daysBetween.intValue();		
	}
	
	
	public static Integer daysInAMonth(int year, int month) {
		YearMonth yearMonthObject = YearMonth.of(year, month);
		int daysInMonth = yearMonthObject.lengthOfMonth();
		return daysInMonth;
	}
	
	/**
	 * Number of months between given date
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int monthsBetween(final Date startDate, final Date endDate) {
	    final Calendar calStart = Calendar.getInstance();
	    calStart.setTime(startDate);
	    final Calendar calEnd = Calendar.getInstance();
	    calEnd.setTime(endDate);
	    int diff = (calEnd.get(Calendar.YEAR) - calStart.get(Calendar.YEAR)) * 12 + calEnd.get(Calendar.MONTH) - calStart.get(Calendar.MONTH);
	    return diff;
	}
	
	/**
	 * Number of months between given date. 
	 * If number of days is above 16 then then month++
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int numberOfMonthsBetween(Date dateOfBirth, Date toDate) {
		LocalDate today = toDate.toInstant()
			      .atZone(ZoneId.systemDefault())
			      .toLocalDate();
		
		LocalDate birthday = dateOfBirth.toInstant()
			      .atZone(ZoneId.systemDefault())
			      .toLocalDate();

		Period p = Period.between(birthday, today);
		int years = p.getYears();
		int months = p.getMonths();
		int days = p.getDays();
		
		if (years > 0 ) {
			months = months + (years * 12);
		}
				
		if (days > 16) {
			months++;
		}
		return months;
	}
	
	/**
	 * Gets months formatted as MMyyy between given start date and end date.
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static List<String> getMonthsBetween(Date startDate, Date endDate){
		
		final Locale locale = new Locale("en", "ZM");
		DateFormat df2 = new SimpleDateFormat("MMyyyy", locale);
		
		Calendar calStartDate = Calendar.getInstance(locale);
	    calStartDate.setTime(startDate);
	    
	    Calendar calEndDate = Calendar.getInstance(locale);
	    calEndDate.setTime(endDate);
		
		List<String> months = new ArrayList<String>();
		
		while (calStartDate.getTime().getTime() <= calEndDate.getTime().getTime()) {
			months.remove(df2.format(calStartDate.getTime()));
	        months.add(df2.format(calStartDate.getTime()));
	        calStartDate.add(Calendar.DAY_OF_MONTH, 1);
	    }		
		return months;
	}
	
	
	public static String getMonthString(Date startDate, String dateType){
		
		final Locale locale = new Locale("en", "ZM");
		DateFormat df2 = new SimpleDateFormat(dateType, locale);
		
		Calendar calStartDate = Calendar.getInstance(locale);
	    calStartDate.setTime(startDate);
		
		String month = df2.format(calStartDate.getTime());	
		return month;
	}
	
	/**
	 * Gets the months between dates in the format supplied in dataType e.g. (MMM-yyyy, MM-yyyy etc)
	 * @param startDate
	 * @param endDate
	 * @param dateType
	 * @return
	 */
	public static List<String> getMonthsBetweenDates(Date startDate, Date endDate, String dateType){
		
		final Locale locale = new Locale("en", "ZM");
		DateFormat df2 = new SimpleDateFormat(dateType, locale);
		
		Calendar calStartDate = Calendar.getInstance(locale);
	    calStartDate.setTime(startDate);
	    
	    Calendar calEndDate = Calendar.getInstance(locale);
	    calEndDate.setTime(endDate);
		
		List<String> months = new ArrayList<String>();
		
		while (calStartDate.getTime().getTime() <= calEndDate.getTime().getTime()) {
			months.remove(df2.format(calStartDate.getTime()).toUpperCase());
	        months.add(df2.format(calStartDate.getTime()).toUpperCase());
	        calStartDate.add(Calendar.DAY_OF_MONTH, 1);
	    }		
		return months;
	}

	/**
     * Retrieves the last day of the specified month and year.
     *
     * @param month The month (0 to 11).
     * @param year  The year.
     * @return The last day of the specified month and year.
     */
	public static int getLastDayOfMonth(int month, int year) {

		final Locale locale = new Locale("en", "ZM");
	    Calendar dateCal = Calendar.getInstance(locale);
	    dateCal.set(year, month, 2);
	    int maxDay = dateCal.getActualMaximum(Calendar.DAY_OF_MONTH);
	    return maxDay;
	}
	
	public static Date getLastDayOfThisMonth() {

		final Locale locale = new Locale("en", "ZM");
	    Calendar lastDay = Calendar.getInstance(locale);
	    lastDay.set(Calendar.DAY_OF_MONTH, lastDay.getActualMaximum(Calendar.DAY_OF_MONTH));
	    
	    return lastDay.getTime();
	}
	
	public static Date getLastDayOfPreviousMonth() {
		Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        int max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, max);
        return calendar.getTime();
	}
	
	public static Integer getMonthNumber(String shortMonthName) {
		Map<String, Integer> months = new HashMap<String, Integer>();
		months.put("JAN", 0);
		months.put("FEB", 1);
		months.put("MAR", 2);
		months.put("APR", 3);
		months.put("MAY", 4);
		months.put("JUN", 5);
		months.put("JUL", 6);
		months.put("AUG", 7);
		months.put("SEP", 8);
		months.put("OCT", 9);
		months.put("NOV", 10);
		months.put("DEC", 11);
		return months.get(shortMonthName);		
	}

	/**
	 * Checks if the given date is between 01-01-financialYear 00:00:00 and 31-12-financialYear 23:59:59
	 * @param givenDate
	 * @param financialYear
	 * @return
	 */
	public static boolean isBetween(Date givenDate, Integer financialYear) {
		Instant start = DateUtil.startDate(financialYear).toInstant();
		Instant end = DateUtil.endDate(financialYear).toInstant();
		Instant date = givenDate.toInstant();
		return (!date.isBefore( start ) ) && ( date.isBefore( end ) ) ;
	}
	
	/**
	 * Check if the given date is between start date and end date
	 * @param givenDate
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static boolean isBetween(Date givenDate, Date startDate, Date endDate) {
		Instant start = startDate.toInstant();
		Instant end = endDate.toInstant();
		Instant date = givenDate.toInstant();
		return (!date.isBefore( start ) ) && ( date.isBefore( end ) ) ;
	}

	public static String pasFinancialYear(Integer financialYear) {
		if (financialYear < 2020) {
			final Integer nextYear = financialYear + 1;						
			String finYear = financialYear.toString()+"/"+nextYear.toString();
			return finYear;
			
		} else if (financialYear == 2020) {						
			String finYear = financialYear.toString();
			return finYear;
		} else {			
			String finYear = financialYear.toString();
			return finYear;
		}
	}

	public static Date pasFinancialYearEndDate(Integer financialYear) {
		if (financialYear < 2020) {							
			final Integer nextYear = financialYear + 1;
			Calendar cEndDate = Calendar.getInstance();
			cEndDate.set(Calendar.YEAR, nextYear);
			cEndDate.set(Calendar.MONTH, Calendar.MARCH);
			cEndDate.set(Calendar.DAY_OF_MONTH, 31);
			cEndDate.set(Calendar.HOUR_OF_DAY, 23);
			cEndDate.set(Calendar.MINUTE, 59);
			cEndDate.set(Calendar.SECOND, 59);
			
			Date endDate = cEndDate.getTime();
			return endDate;
			
		} else if (financialYear == 2020) {
			Calendar cEndDate = Calendar.getInstance();
			cEndDate.set(Calendar.YEAR, financialYear);
			cEndDate.set(Calendar.MONTH, Calendar.DECEMBER);
			cEndDate.set(Calendar.DAY_OF_MONTH, 31);
			cEndDate.set(Calendar.HOUR_OF_DAY, 23);
			cEndDate.set(Calendar.MINUTE, 59);
			cEndDate.set(Calendar.SECOND, 59);
			Date endDate = cEndDate.getTime();
			return endDate;
		} else {
						
			Calendar cEndDate = Calendar.getInstance();
			cEndDate.set(Calendar.YEAR, financialYear);
			cEndDate.set(Calendar.MONTH, Calendar.DECEMBER);
			cEndDate.set(Calendar.DAY_OF_MONTH, 31);
			cEndDate.set(Calendar.HOUR_OF_DAY, 23);
			cEndDate.set(Calendar.MINUTE, 59);
			cEndDate.set(Calendar.SECOND, 59);
			
			Date endDate = cEndDate.getTime();
			return endDate;
		}
	}

	public static Date pasFinancialYearStartDate(Integer financialYear) {
		if (financialYear < 2020) {				
			
			Calendar cStartDate = Calendar.getInstance();
			cStartDate.set(Calendar.YEAR, financialYear);
			cStartDate.set(Calendar.MONTH, Calendar.APRIL);
			cStartDate.set(Calendar.DAY_OF_MONTH, 1);
			cStartDate.set(Calendar.HOUR_OF_DAY, 0);
			cStartDate.set(Calendar.MINUTE, 0);
			cStartDate.set(Calendar.SECOND, 0);
						
			Date startDate = cStartDate.getTime();
			return startDate;
			
		} else if (financialYear == 2020) {
							
			Calendar cStartDate = Calendar.getInstance();
			cStartDate.set(Calendar.YEAR, financialYear);
			cStartDate.set(Calendar.MONTH, Calendar.APRIL);
			cStartDate.set(Calendar.DAY_OF_MONTH, 1);
			cStartDate.set(Calendar.HOUR_OF_DAY, 0);
			cStartDate.set(Calendar.MINUTE, 0);
			cStartDate.set(Calendar.SECOND, 0);
						
			Date startDate = cStartDate.getTime();
			return startDate;
		} else {
						
			Calendar cStartDate = Calendar.getInstance();
			cStartDate.set(Calendar.YEAR, financialYear);
			cStartDate.set(Calendar.MONTH, Calendar.JANUARY);
			cStartDate.set(Calendar.DAY_OF_MONTH, 1);
			cStartDate.set(Calendar.HOUR_OF_DAY, 0);
			cStartDate.set(Calendar.MINUTE, 0);
			cStartDate.set(Calendar.SECOND, 0);
						
			Date startDate = cStartDate.getTime();
			return startDate;
		}
	}

	/**
	 * Get the year from a date
	 */
	public static Integer year(Date giveDate) {
		Calendar cReceiptDate = Calendar.getInstance(TimeZone.getTimeZone("Africa/Lusaka"), new Locale("en", "ZM"));
		cReceiptDate.setTime(giveDate);
		Integer financialYear = cReceiptDate.get(Calendar.YEAR);
		return financialYear;
	}

	public static Integer pasFinancialYear(Date receiptDate) {
		Integer financialYear = year(receiptDate);
		
		// before 2020 and part 2020
		Calendar cStartDate = Calendar.getInstance();
		cStartDate.set(Calendar.YEAR, financialYear);
		cStartDate.set(Calendar.MONTH, Calendar.APRIL);
		cStartDate.set(Calendar.DAY_OF_MONTH, 1);
		cStartDate.set(Calendar.HOUR_OF_DAY, 0);
		cStartDate.set(Calendar.MINUTE, 0);
		cStartDate.set(Calendar.SECOND, 0);
		
		Calendar cEndDate = Calendar.getInstance();
		cEndDate.set(Calendar.YEAR, 2020);
		cEndDate.set(Calendar.MONTH, Calendar.APRIL);
		cEndDate.set(Calendar.DAY_OF_MONTH, 1);
		cEndDate.set(Calendar.HOUR_OF_DAY, 0);
		cEndDate.set(Calendar.MINUTE, 0);
		cEndDate.set(Calendar.SECOND, 0);
		Date firstEndDate = cEndDate.getTime();
		
		if (receiptDate.before(firstEndDate)) {
			Calendar cGivenDate = Calendar.getInstance();
			cGivenDate.setTime(receiptDate);
			
			if (cGivenDate.get(Calendar.MONTH) == Calendar.JANUARY
					|| cGivenDate.get(Calendar.MONTH) == Calendar.FEBRUARY
					|| cGivenDate.get(Calendar.MONTH) == Calendar.MARCH) {
				return cGivenDate.get(Calendar.YEAR) - 1;
			} else {
				return cGivenDate.get(Calendar.YEAR);
			}
		} else {
			return financialYear;
		}
	}
	
	/**
	 * The threshold date used for calculating monthly pension values.
	 * 24th September 1994
	 */
	public static Date claimThresholDate() {
		Calendar threshDate = Calendar.getInstance();
		threshDate.set(Calendar.MONTH, Calendar.SEPTEMBER);
		threshDate.set(Calendar.DAY_OF_MONTH, 24);
		threshDate.set(Calendar.YEAR, 1994);
		threshDate.set(Calendar.HOUR_OF_DAY, 0);
		threshDate.set(Calendar.MINUTE, 0);
		threshDate.set(Calendar.SECOND, 0);
		return threshDate.getTime();
	}

	/**
     * Retrieves the predefined migration date used for data migration.
     *
     * @return The predefined data migration date.
     */
    public static Date getDataMigrationDate() {
        Calendar migrationDate = Calendar.getInstance();
        migrationDate.set(2012, Calendar.MARCH, 8, 0, 0, 0);
        return migrationDate.getTime();
    }

	/**
	 * Retrieves the current quarter of the year based on the system's local date.
	 *
	 * @return The current quarter of the year (1 to 4).
	 * 
	 */
	public static int getCurrentQuarter() {
	    LocalDate currentDate = LocalDate.now();
	    return currentDate.get(IsoFields.QUARTER_OF_YEAR);
	}

	public static int getCurrentMonth() {
		return Calendar.getInstance().get(Calendar.MONTH);
	}

	
	/**
	 * Returns the abbreviated name of a month based on the given month number.
	 *
	 * @param monthNumber The month number (1 to 12) for which to retrieve the abbreviated name.
	 * @return The abbreviated name of the specified month (e.g., "JAN" for January).
	 * @throws IllegalArgumentException If the provided month number is not within the valid range (1 to 12).
	 */
	public static String getAbbreviatedMonthName(int monthNumber) {
	    if (monthNumber < 1 || monthNumber > 12) {
	        throw new IllegalArgumentException("Invalid month number: " + monthNumber);
	    }
	    return Month.of(monthNumber).name().substring(0, 3).toUpperCase();
	}
	
	/**
	 * Retrieves the quarter period string based on the provided month number.
	 *
	 * @param monthNumber The month number (1 to 12).
	 * @return The corresponding quarter period string (e.g., "Jan-Mar").
	 * @throws IllegalArgumentException If an invalid month number is provided (not in the range 1 to 12).
	 */
	public static String getQuarterByMonth(int monthNumber) {
	    if (monthNumber >= 1 && monthNumber <= 12) {
	        int quarter = (monthNumber - 1) / 3;
	        String[] quarters = {"Jan-Mar", "Apr-Jun", "Jul-Sep", "Oct-Dec"};
	        return quarters[quarter];
	    } else {
	        throw new IllegalArgumentException("Invalid month number: " + monthNumber);
	    }
	}
	
	/**
	 * Retrieves the quarter period string based on the provided quarter number.
	 *
	 * @param quarter The quarter number (1, 2, 3, or 4).
	 * @return The corresponding quarter period string (e.g., "Jan-Mar").
	 * @throws IllegalArgumentException If an invalid quarter number is provided (not in the range 1 to 4).
	 */
	public static String getQuarterPeriodByQuarter(Integer quarter) {
	    if (quarter >= 1 && quarter <= 4) {
	        String[] quarters = {"Jan-Mar", "Apr-Jun", "Jul-Sep", "Oct-Dec"};
	        return quarters[quarter - 1];
	    } else {
	        throw new IllegalArgumentException("Invalid quarter value: " + quarter);
	    }
	}
	
	/**
	 * Formats an optional date into a string representation.
	 *
	 * @param date The optional date to format.
	 * @return A formatted string representation of the date in the format "dd-MM-yyyy HH:mm".
	 */
	public String formatDate(Optional<Date> date) {
	    return date.map(d -> new SimpleDateFormat("dd-MM-yyyy HH:mm").format(d))
	               .orElse(" ");
	}

}
