package main;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by busracanak on 29/04/18.
 */
public class Conference {

    private int dayCount;
    private List<Talk> talks;
    private int morningSessionMins;
    private int noonSessionMins;


    public Conference() {
        talks = new ArrayList<Talk>();
    }

    public int getMorningSessionMins() {
        return morningSessionMins;
    }

    public void setMorningSessionMins(int morningSessionMins) {
        this.morningSessionMins = morningSessionMins;
    }

    public int getNoonSessionMins() {
        return noonSessionMins;
    }

    public void setNoonSessionMins(int noonSessionMins) {
        this.noonSessionMins = noonSessionMins;
    }

    public int getDayCount() {
        return dayCount;
    }

    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }

    public List<Talk> getTalks() {
        return talks;
    }
}
