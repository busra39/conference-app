package test;

import main.Conference;
import main.TALK_TYPE;
import main.Talk;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by busracanak on 29/04/18.
 */
public class ConferenceTest {
    private Conference conference;

    @Before
    public void setUp(){
        conference = new Conference();
        conference.setMorningSessionMins(120);
        conference.setNoonSessionMins(360);
    }

    @Test
    public void shouldReturnTotalSessionMins(){
        assertEquals(480, conference.getMorningSessionMins() + conference.getNoonSessionMins());
    }

    @Test
    public void shouldReturnDefaultTalks(){
        assertEquals(0, conference.getTalks().size());
    }

    @Test
    public void shouldReturnTalks(){
        Talk t1 = new Talk(1, "t1", 30);
        Talk t2 = new Talk(2, "t2", 45);
        Talk t3 = new Talk(3, "t3", 60);
        conference.getTalks().add(t1);
        conference.getTalks().add(t2);
        conference.getTalks().add(t3);
        assertEquals(3, conference.getTalks().size());
    }

    @Test
    public void shouldReturnDays(){
        conference.setDayCount(1);
        assertEquals(1, conference.getDayCount());
    }
}