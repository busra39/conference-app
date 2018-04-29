package test;

import main.TALK_TYPE;
import main.Talk;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
/**
 * Created by busracanak on 29/04/18.
 */
public class TalkTest {
    private Talk talk;

    @Before
    public void setUp(){
        talk = new Talk(23, "Talk Title", 60);
    }

    @Test
    public void shouldReturnTalkTitle(){
        assertEquals("Talk Title", talk.getTitle());
    }

    @Test
    public void shouldReturnTalkDuration(){
        assertEquals(60, talk.getDuration());
    }

    @Test
    public void shouldReturnDefaultTalkType(){
        assertEquals(TALK_TYPE.TALK, talk.getType() );
    }

    @Test
    public void shouldReturnTalkType(){
        talk.setType(TALK_TYPE.LUNCH);
        assertEquals(TALK_TYPE.LUNCH, talk.getType() );
    }
}