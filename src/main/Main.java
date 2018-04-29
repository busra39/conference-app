package main;

public class Main {

    public static final int MORNING_SESSION_START  = 9;
    public static final int MORNING_SESSION_END  = 12;
    public static final int NOON_SESSION_START  = 1;
    public static final int NOON_SESSION_END  = 5;
    public static final String TALKS_PATH = "Input.txt";

    public static void main(String[] args) {
        ConferenceUtil util = new ConferenceUtil();
        util.processSessions(MORNING_SESSION_START, MORNING_SESSION_END, NOON_SESSION_START, NOON_SESSION_END);
        util.processTalks(TALKS_PATH);
        util.processDays();
        util.output();
    }
}
