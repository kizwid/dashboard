package sandkev.dash.service;

import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 01/12/2016.
 */
public class LoggingMonitorService implements MonitorService {

    private static final EventHandler EVENT_HANDLER = new EventHandler() {
        @Override
        public void handle(Event event) {
            System.out.println("Event: " + event);
        }
    };
    private final List<Task> tasks;

    public LoggingMonitorService() {
        this.tasks = new ArrayList<>();
    }

    @Override
    public long registerTask(Task task) {
        task.addEventHandler(EventType.ROOT, EVENT_HANDLER);
        return 0;
    }
}
