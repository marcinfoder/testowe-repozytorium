package com.capgemini;

import java.util.Comparator;
import com.capgemini.persistence.domain.Message;

public class DateComparator implements Comparator<Message> 
{
    @Override
    public int compare(Message m1, Message m2) 
    {
        return m1.getTwitterPublishAt().compareTo(m2.getTwitterPublishAt());
    }
}
