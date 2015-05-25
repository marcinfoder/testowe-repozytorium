package com.capgemini;

import java.util.Comparator;
import com.capgemini.persistence.domain.Message;

public class DateComparator implements Comparator<Message> 
{
	public static final String DATE_FORMAT = "dd/MM/yyyy";
	public static final String TIME_FORMAT = "HH:mm";
	public static final String DATE_AND_TIME_FORMAT = "dd/MM/yyyy HH:mm";
	
    @Override
    public int compare(Message m1, Message m2) 
    {
        return m1.getTwitterPublishAt().compareTo(m2.getTwitterPublishAt());
    }
}
