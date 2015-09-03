package com.c2s.music.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DateUtils
{

	private static final String	Format			= "dd-MMM-yyyy";
	private static final String	FormatWithTime	= "dd-MMM-yyyy HH:mm:ss";
	private static final String	TimeFormat		= "HH:mm:ss";

	private DateUtils()
	{
	}

	public static String getTime()
	{
		SimpleDateFormat timeFormat = new SimpleDateFormat(TimeFormat);
		Calendar calendar = Calendar.getInstance();
		Date currDate = calendar.getTime();
		String d1 = timeFormat.format(currDate);
		return "  " + d1;
	}

	public static String getTime(Date date)
	{
		if (date == null)
			return null;

		SimpleDateFormat dateFormat = new SimpleDateFormat(TimeFormat);
		return dateFormat.format(date);

	}

	public static Date now()
	{
		Date now = new Date();
		String systemDateWithTime = DateUtils.getApplicationDateWithTime(toString(now));
		return DateUtils.toDateWithTime(systemDateWithTime);

		// return org.apache.commons.lang.time.DateUtils.truncate(now,
		// Calendar.HOUR);
		// Calendar calendar = Calendar.getInstance();
		// calendar.set(Calendar.MILLISECOND, 0);
		// return calendar.getTime();
	}

	public static String nowWithTime()
	{

		SimpleDateFormat dateFormat = new SimpleDateFormat(FormatWithTime);
		Date now = new Date();
		return dateFormat.format(now);
	}

	public static String toString(Date date)
	{
		if (date == null)
			return null;

		SimpleDateFormat dateFormat = new SimpleDateFormat(Format);
		return dateFormat.format(date);
	}

	public static String toString(Date date, String format)
	{
		if (date == null)
			return null;

		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);

	}

	public static Date toDate(String date)
	{
		if (date == null || date.equals(""))
			return null;

		SimpleDateFormat dateFormat = new SimpleDateFormat(Format);
		try
		{
			return dateFormat.parse(date);
		} catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static Date toDate(String date, String format)
	{
		if (date == null || date.equals(""))
			return null;

		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		try
		{
			return dateFormat.parse(date);
		} catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static Date toDateWithTime(String date)
	{
		if (date == null || date.equals(""))
			return null;

		SimpleDateFormat dateFormat = new SimpleDateFormat(FormatWithTime);
		try
		{
			return dateFormat.parse(date);
		} catch (ParseException e)
		{
			try
			{
				dateFormat = new SimpleDateFormat(Format);
				return dateFormat.parse(date);
			} catch (Exception e1)
			{

			}
		}

		return null;
	}

	/**
	 * returns formatted time as per TimeFormat
	 * 
	 * @param applicationDate
	 *            TODO
	 * @return
	 */
	public static String getApplicationDateWithTime(String applicationDate)
	{

		SimpleDateFormat dateFormat = new SimpleDateFormat(TimeFormat);
		Long currentTime = Calendar.getInstance().getTime().getTime();
		// formatted time as per Time Format
		String formattedTime = dateFormat.format(currentTime);
		String formattedDateWithTime = applicationDate + " " + formattedTime;

		return formattedDateWithTime;
	}

	public static String toDateWithTime(Date date)
	{

		if (date == null || date.equals(""))
			return null;

		SimpleDateFormat dateFormat = new SimpleDateFormat(FormatWithTime);
		try
		{
			return dateFormat.format(date);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String addMonth(String date)
	{
		if (date == null || date.equals(""))
			return null;

		Calendar c1 = Calendar.getInstance();
		Date actDate = DateUtils.toDate(date);
		c1.setTime(actDate);
		c1.roll(Calendar.MONTH, +1);
		int month = actDate.getMonth();
		if (month == 11)
		{
			c1.roll(Calendar.YEAR, +1);
			return toString(c1.getTime());
		} else
		{
			return toString(c1.getTime());
		}
	}

	public static String dateAdd(String date, int value, String field)
	{
		java.util.Calendar clndr = java.util.Calendar.getInstance();
		String month[] = new String[12];
		month[0] = new String("Jan");
		month[1] = new String("Feb");
		month[2] = new String("Mar");
		month[3] = new String("Apr");
		month[4] = new String("May");
		month[5] = new String("Jun");
		month[6] = new String("Jul");
		month[7] = new String("Aug");
		month[8] = new String("Sep");
		month[9] = new String("Oct");
		month[10] = new String("Nov");
		month[11] = new String("Dec");

		String stringday = date.substring(0, 2);
		String stringmonth = date.substring(3, 6);
		String stringyear = date.substring(7, 11);
		int intday = Integer.parseInt(stringday);

		// if( ( intday < 1 ) || ( intday > 31 ) )
		// throw new ProcessException("invalid Day");

		int intyear = Integer.parseInt(stringyear);
		int intmonth = 0;
		int x = -1;
		for (x = 0; x < month.length; x++)
		{
			if (month[x].equalsIgnoreCase(stringmonth))
			{
				intmonth = x;
				break;
			}
		}

		// if ( (x > 11 ) || (x < 0 ))
		// {
		// throw new ProcessException("Month format Error");
		// }
		java.util.Date processdate = new java.util.Date(intyear, intmonth, intday);
		clndr.setTime(processdate);

		if (field.equalsIgnoreCase("D"))
		{
			clndr.add(java.util.Calendar.DATE, value);
		}
		if (field.equalsIgnoreCase("M"))
		{
			clndr.add(java.util.Calendar.MONTH, value);
		}
		if (field.equalsIgnoreCase("Y"))
		{
			clndr.add(java.util.Calendar.YEAR, value);
		}

		java.util.Date date1 = clndr.getTime();
		intday = date1.getDate();
		stringmonth = month[date1.getMonth()];
		intyear = date1.getYear();

		String stringintday = String.valueOf(intday);
		if (stringintday.length() == 1)
			stringintday = "0" + stringintday;

		return new String("" + stringintday + "-" + stringmonth + "-" + intyear);
	}

	public static String changeDateFormat(String date)
	{
		String changedDate = null;

		try
		{

			int index = date.indexOf('-');
			String day = date.substring(0, index);
			String month = date.substring(index + 1, index + 4);
			String year = date.substring(index + 5, index + 9);

			int mm = 0;

			if (month.equalsIgnoreCase("jan"))
				mm = 1;
			else
				if (month.equalsIgnoreCase("feb"))
					mm = 2;
				else
					if (month.equalsIgnoreCase("mar"))
						mm = 3;
					else
						if (month.equalsIgnoreCase("apr"))
							mm = 4;
						else
							if (month.equalsIgnoreCase("may"))
								mm = 5;
							else
								if (month.equalsIgnoreCase("jun"))
									mm = 6;
								else
									if (month.equalsIgnoreCase("jul"))
										mm = 7;
									else
										if (month.equalsIgnoreCase("aug"))
											mm = 8;
										else
											if (month.equalsIgnoreCase("sep"))
												mm = 9;
											else
												if (month.equalsIgnoreCase("oct"))
													mm = 10;
												else
													if (month.equalsIgnoreCase("nov"))
														mm = 11;
													else
														if (month.equalsIgnoreCase("dec"))
															mm = 12;

			if (mm <= 9)
				changedDate = day + "-0" + mm + "-" + year;
			else
				changedDate = day + "-" + mm + "-" + year;

		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return changedDate;
	}

	public static ArrayList changetoGeorgianFormat(String date)
	{
		ArrayList georgianFormat = new ArrayList();

		try
		{
			int index = date.indexOf('-');
			String day = date.substring(0, index);
			String month = date.substring(index + 1, index + 3);
			String year = date.substring(index + 4, index + 8);
			georgianFormat.add(0, year);
			georgianFormat.add(1, month);
			georgianFormat.add(2, day);
		} catch (Exception e)
		{
			// e.printStackTrace();
			// throw new ProcessException(e.getMessage());
		}

		return georgianFormat;
	}

	public static String toStringWithTime(Date date)
	{

		if (date == null || date.equals(""))
			return null;

		SimpleDateFormat dateFormat = new SimpleDateFormat(FormatWithTime);
		try
		{
			return dateFormat.format(date);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String dateWithFullMonth(String date)
	{
		String changedDate = null;

		try
		{

			int index = date.indexOf('-');
			String day = date.substring(0, index);
			String month = date.substring(index + 1, index + 4);
			String year = date.substring(index + 5, index + 9);

			if (month.equalsIgnoreCase("jan"))
				month = "January";
			else
				if (month.equalsIgnoreCase("feb"))
					month = "February";
				else
					if (month.equalsIgnoreCase("mar"))
						month = "March";
					else
						if (month.equalsIgnoreCase("apr"))
							month = "April";
						else
							if (month.equalsIgnoreCase("may"))
								month = "May";
							else
								if (month.equalsIgnoreCase("jun"))
									month = "June";
								else
									if (month.equalsIgnoreCase("jul"))
										month = "July";
									else
										if (month.equalsIgnoreCase("aug"))
											month = "August";
										else
											if (month.equalsIgnoreCase("sep"))
												month = "September";
											else
												if (month.equalsIgnoreCase("oct"))
													month = "October";
												else
													if (month.equalsIgnoreCase("nov"))
														month = "November";
													else
														if (month.equalsIgnoreCase("dec"))
															month = "December";

			changedDate = month + " " + day + ", " + year;

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return changedDate;

	}

	public static Date applicationDateWithServerTime(Date applicationDate)
	{
		String applicationDateWithTime = DateUtils.getApplicationDateWithTime(toString(applicationDate));
		return DateUtils.toDateWithTime(applicationDateWithTime);
	}

	public static Date addDays(Date date, int days)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}

	public static String toStringFormatted(Date date, String dateformat)
	{
		if (date == null || date.equals(""))
			return null;

		if (dateformat == null || "".equals(dateformat))
			dateformat = Format;

		SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);
		try
		{
			return dateFormat.format(date);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * returns formatted time as per TimeFormat
	 * 
	 * @param applicationDate
	 *            TODO
	 * @return
	 */
	public static String toStringFormattedWithTime(String applicationDate, String timeFormat)
	{

		SimpleDateFormat dateformat = new SimpleDateFormat(timeFormat);
		Long currentTime = Calendar.getInstance().getTime().getTime();
		// formatted time as per Time Format
		String formattedTime = dateformat.format(currentTime);
		String formattedDateWithTime = applicationDate + " " + formattedTime;

		return formattedDateWithTime;
	}

}
