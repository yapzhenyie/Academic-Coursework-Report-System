package oodj.group5.utils;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;

public enum EnumLogDuration {

    PAST_30_DAYS("Past 30 days", "Past 30 days", Date.valueOf(LocalDate.now().minusDays(30))),
    PAST_90_DAYS("Past 90 days", "Past 90 days", Date.valueOf(LocalDate.now().minusDays(90))),
    THIS_YEAR(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)), "Year " + String.valueOf(Calendar.getInstance().get(Calendar.YEAR)), Date.valueOf(LocalDate.now().with(TemporalAdjusters.firstDayOfYear()).atStartOfDay().toLocalDate()), Date.valueOf(LocalDate.now().with(TemporalAdjusters.lastDayOfYear()).atStartOfDay().toLocalDate())),
    LAST_YEAR(String.valueOf(Calendar.getInstance().get(Calendar.YEAR) - 1), "Year " + String.valueOf(Calendar.getInstance().get(Calendar.YEAR) - 1), Date.valueOf(LocalDate.now().minusYears(1).with(TemporalAdjusters.firstDayOfYear()).atStartOfDay().toLocalDate()), Date.valueOf(LocalDate.now().minusYears(1).with(TemporalAdjusters.lastDayOfYear()).atStartOfDay().toLocalDate()));
    
    private String name;
    private String displayName;
    private Date dateStart;
    private Date dateEnd;

    private EnumLogDuration(String name, String displayName, Date dateStart) {
        this(name, displayName, dateStart, Date.valueOf(LocalDate.now()));
    }

    private EnumLogDuration(String name, String displayName, Date dateStart, Date dateEnd) {
        this.name = name;
        this.displayName = displayName;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public String getName() {
        return this.name;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public Date getDateStart() {
        return this.dateStart;
    }

    public Date getDateEnd() {
        return this.dateEnd;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static EnumLogDuration getEnum(String name) {
        for (EnumLogDuration logDuration : EnumLogDuration.values()) {
            if (logDuration.getName().equals(name)) {
                return logDuration;
            }
        }
        return null;
    }
}
