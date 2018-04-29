package test;

import main.ConferenceUtil;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by busracanak on 29/04/18.
 */
public class UtilTests {
    ConferenceUtil util;

    @Before
    public void setUp(){
        util = new ConferenceUtil();
    }

    @Test
    public void shouldReturnConfigurations(){
        util.processSessions(8, 12, 1, 3);
        assertEquals(240, util.conference.getMorningSessionMins());
        assertEquals(120, util.conference.getNoonSessionMins());
    }

    @Test
    public void shouldReturnTalks(){
        util.processSessions(8, 12, 1, 3);
        util.processTalks("TestInput.txt");
        assertEquals(8, util.conference.getTalks().size());
        assertEquals(1, util.conference.getDayCount());
        assertEquals("Writing Fast Tests Against Enterprise Rails 60min", util.conference.getTalks().get(0).getTitle());
        assertEquals("Ruby Errors from Mismatched Gem Versions 48min", util.conference.getTalks().get(3).getTitle());
        assertEquals("Rails for Python Developers lightning", util.conference.getTalks().get(7).getTitle());
    }

    @Test
    public void shouldReturnSessions(){
        util.processSessions(9, 12, 1, 3);
        util.processTalks("TestInput.txt");
        util.processDays();
        assertEquals("09:00 AM", util.conference.getTalks().get(0).getStartTime());
        assertEquals("10:59 AM", util.conference.getTalks().get(2).getStartTime());
        assertEquals("01:00 PM", util.conference.getTalks().get(3).getStartTime());
        assertEquals("10:15 AM", util.conference.getTalks().get(7).getStartTime());
    }
}