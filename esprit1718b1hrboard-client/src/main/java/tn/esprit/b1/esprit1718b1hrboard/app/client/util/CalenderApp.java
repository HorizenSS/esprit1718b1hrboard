package tn.esprit.b1.esprit1718b1hrboard.app.client.util;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.Calendar.Style;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.model.Interval;
import com.calendarfx.view.CalendarView;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tn.esprit.b1.esprit1718b1hrboard.entities.Interview;
public class CalenderApp extends Application{
	public static Calendar birthdays;
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		/*CalendarView calendarView = new CalendarView();

        Calendar birthdays = new Calendar("Birthdays");
        Calendar holidays = new Calendar("Holidays");

        birthdays.setStyle(Style.STYLE1);
        holidays.setStyle(Style.STYLE2);
        Entry<String> dentistAppointment = new Entry<>("Dentist");
        LocalDate l1 = LocalDate.of(2018, 4, 8);
        LocalDate l2 = LocalDate.of(2018, 4, 9);
        LocalTime lt1 = LocalTime.of(10, 0);
        LocalTime lt2 = LocalTime.of(11, 0);
        Interval i = new Interval(l1,lt1,l1,lt2);
        dentistAppointment.setInterval(i);
        holidays.addEntry(dentistAppointment);
        CalendarSource myCalendarSource = new CalendarSource("My Calendars");
        myCalendarSource.getCalendars().addAll(holidays);

        calendarView.getCalendarSources().addAll(myCalendarSource);

        calendarView.setRequestedTime(LocalTime.now());

        Thread updateTimeThread = new Thread("Calendar: Update Time Thread") {
                @Override
                public void run() {
                        while (true) {
                                Platform.runLater(() -> {
                                        calendarView.setToday(LocalDate.now());
                                        calendarView.setTime(LocalTime.now());
                                });

                                try {
                                        // update every 10 seconds
                                        sleep(10000);
                                } catch (InterruptedException e) {
                                        e.printStackTrace();
                                }

                        }
                };
        };

        updateTimeThread.setPriority(Thread.MIN_PRIORITY);
        updateTimeThread.setDaemon(true);
        updateTimeThread.start();
        //calendarView.getEntry

        

        Scene scene = new Scene(calendarView);
        primaryStage.setTitle("Calendar");
        primaryStage.setScene(scene);
        primaryStage.setWidth(1000);
        primaryStage.setHeight(700);
        primaryStage.centerOnScreen();
        primaryStage.show();
        
        
        primaryStage.setOnCloseRequest(e -> {
        	LocalDate l3 = LocalDate.of(2018, 1, 1);
            LocalDate l4 = LocalDate.of(2018, 12, 30);
        	Map<LocalDate, List<Entry<?>>> result = holidays.findEntries(l3 ,l4, ZoneId.systemDefault());
        	for (Map.Entry<LocalDate, List<Entry<?>>> entry : result.entrySet()) {
				System.out.println(entry.getKey());
				System.out.println(entry.getValue());
			}
        	
        });*/
		
	}
	public static List<Entry<?>> startt(Stage primaryStage , List<Interview> l){
		
		Map<LocalDate, List<Entry<?>>> map = new HashMap();
		
		CalendarView calendarView = new CalendarView();

        birthdays = new Calendar("Birthdays");
        Calendar holidays = new Calendar("Holidays");

        birthdays.setStyle(Style.STYLE1);
        holidays.setStyle(Style.STYLE2);
        Entry<String> dentistAppointment = new Entry<>("Dentist");
        LocalDate l1 = LocalDate.of(2018, 4, 8);
        LocalDate l2 = LocalDate.of(2018, 4, 9);
        LocalTime lt1 = LocalTime.of(10, 0);
        LocalTime lt2 = LocalTime.of(11, 0);
        Interval i = new Interval(l1,lt1,l1,lt2);
        dentistAppointment.setInterval(i);
        holidays.addEntry(dentistAppointment);
        for (Interview interview : l) {
        	LocalDate date = interview.getInteviewDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        	LocalTime ltime1 = interview.getTimebegin().toLocalTime();
        	LocalTime ltime2 = interview.getTimeend().toLocalTime();
        	Interval i2 = new Interval(date,ltime1,date,ltime2);
        	Entry<String> inter = new Entry<>(interview.getInterviewType());
        	inter.setInterval(i2);
        	birthdays.addEntry(inter);
		}
        CalendarSource myCalendarSource = new CalendarSource("My Calendars");
        myCalendarSource.getCalendars().addAll(birthdays);

        calendarView.getCalendarSources().addAll(myCalendarSource);

        calendarView.setRequestedTime(LocalTime.now());

        Thread updateTimeThread = new Thread("Calendar: Update Time Thread") {
                @Override
                public void run() {
                        while (true) {
                                Platform.runLater(() -> {
                                        calendarView.setToday(LocalDate.now());
                                        calendarView.setTime(LocalTime.now());
                                });

                                try {
                                        // update every 10 seconds
                                        sleep(10000);
                                } catch (InterruptedException e) {
                                        e.printStackTrace();
                                }

                        }
                };
        };

        updateTimeThread.setPriority(Thread.MIN_PRIORITY);
        updateTimeThread.setDaemon(true);
        updateTimeThread.start();
        //calendarView.getEntry

        

        Scene scene = new Scene(calendarView);
        primaryStage.setTitle("Calendar");
        primaryStage.setScene(scene);
        primaryStage.setWidth(1000);
        primaryStage.setHeight(700);
        primaryStage.centerOnScreen();
        primaryStage.show();
        
        
        
        List<Entry<?>> listentry = new ArrayList();
		LocalDate l3 = LocalDate.of(2018, 1, 1);
        LocalDate l4 = LocalDate.of(2018, 12, 30);
    	Map<LocalDate, List<Entry<?>>> result = birthdays.findEntries(l3 ,l4, ZoneId.systemDefault());
    	for (Map.Entry<LocalDate, List<Entry<?>>> entry : result.entrySet()) {
			for (Entry<?> ent : entry.getValue()) {
				listentry.add(ent);
			}
		}
        return listentry;
	}
	
	 public static void main(String[] args) {
         launch(args);
 }

}
