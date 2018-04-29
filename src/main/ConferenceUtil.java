package main;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by busracanak on 29/04/18.
 */
public class ConferenceUtil {

    public Conference conference;
    private final SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
    private Calendar cal = new GregorianCalendar();

    public ConferenceUtil() {
        if(conference == null)
            conference = new Conference();
    }

    public void processSessions(int morningSessionStart, int morningSessionEnd, int noonSessionStart, int noonSessionEnd) {
        int morningSessionHours =  morningSessionEnd - morningSessionStart;
        conference.setMorningSessionMins(morningSessionHours*60);

        int noonSessionHours = noonSessionEnd - noonSessionStart;
        conference.setNoonSessionMins(noonSessionHours*60);
    }

    public void processTalks(String filePath){
        FileInputStream fstream = null;

        try {
            fstream = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String line;

        int talkId = 1;
        int totalTalkDuration = 0;

        try {
            while ((line = br.readLine()) != null) {

                String mins = line.substring(line.lastIndexOf(" "), line.length());
                int duration = 5;
                if(mins.contains("min")){
                    duration = Integer.parseInt(mins.substring(0, mins.lastIndexOf("m")).trim());
                }
                totalTalkDuration +=duration;

                Talk talk = new Talk(talkId, line, duration);
                conference.getTalks().add(talk);
                talkId++;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int days = totalTalkDuration / (conference.getMorningSessionMins() + conference.getNoonSessionMins());
        double remains = totalTalkDuration % (conference.getMorningSessionMins() + conference.getNoonSessionMins());
        if(remains >0) days++;
        conference.setDayCount(days);

        Collections.sort(conference.getTalks(), new TalkCompare());
    }

    public void output() {
        int track = 1;

        System.out.println("Track " +  track + ":");

        List<Talk> talks = conference.getTalks();
        for (int i = 0; i < talks.size(); i++){
            System.out.println(talks.get(i).getStartTime() + " " + talks.get(i).getTitle());
            if(talks.get(i).getType() == TALK_TYPE.LUNCH) {
                System.out.println("12:00 PM Lunch Time");
            }

            if(talks.get(i).getType() == TALK_TYPE.NETWORKING ) {
                System.out.println("05:00 PM Networking Event");
                System.out.println();
                System.out.println();
                if(i != talks.size() - 1) {
                    track++;
                    System.out.println("Track " +  track + ":");
                }
            }
        }
    }

    public void processDays(){
        int talkIndex = 0;
        for(int i = 0; i < conference.getDayCount(); i++){
             talkIndex = processDay(talkIndex);
        }
    }

    private int processDay(int talkIndex) {

        int morningSession = conference.getMorningSessionMins();
        int noonSession = conference.getNoonSessionMins();
        List<Talk> talks = conference.getTalks();

        talkIndex = processMorningSession(talks, talkIndex, morningSession);
        if(talkIndex < talks.size())
            talkIndex = processNoonSession(talks, talkIndex, noonSession);

        return talkIndex;
    }

    private int processMorningSession(List<Talk> talks, int talkIndex, int morningSession) {
        setCalendar(9, 0, Calendar.AM);
        return settings(talkIndex, talks, morningSession, TALK_TYPE.LUNCH);
    }

    private int processNoonSession(List<Talk> talks, int talkIndex, int noonSession) {
        setCalendar(1, 0, Calendar.PM);
        return settings(talkIndex, talks, noonSession, TALK_TYPE.NETWORKING);
    }

    private int settings(int talkIndex, List<Talk> talks, int session, TALK_TYPE type){
        int i = talkIndex;
        for(; i < talks.size(); i++) {
            if (session >= talks.get(i).getDuration()){
                talks.get(i).setStartTime(sdf.format(cal.getTime()));
                cal.add(Calendar.MINUTE, talks.get(i).getDuration());
                session -= talks.get(i).getDuration();
            } else break;

        }
        talkIndex = i;
        talks.get(talkIndex - 1).setType(type);
        return talkIndex;
    }

    private void setCalendar(int hour, int min, int am_pm) {
        cal.set(Calendar.HOUR, hour);
        cal.set(Calendar.MINUTE, min);
        cal.set(Calendar.AM_PM, am_pm);
    }

}
