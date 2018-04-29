package main;

import java.util.Comparator;

/**
 * Created by busracanak on 29/04/18.
 */

/* This class is to keep talk's features.*/
public class Talk {

    private int id;
    private String title;
    private int duration;
    private String startTime;
    private TALK_TYPE flag = TALK_TYPE.TALK;

    public Talk(int id, String title, int duration) {
        this.id = id;
        this.title = title;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public TALK_TYPE getType() {
        return flag;
    }

    public void setType(TALK_TYPE flag) {
        this.flag = flag;
    }
}

class TalkCompare implements Comparator<Talk> {

    @Override
    public int compare(Talk t1, Talk t2) {
        if(t1.getDuration() < t2.getDuration()){
            return 1;
        } else {
            return -1;
        }
    }
}