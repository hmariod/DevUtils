/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package develsys.utils;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

/**
 *
 * @author hmariod
 */
public class LoginEvent extends Event {
    public static final EventType<LoginEvent> USER_LOG_ON = new EventType(ANY, "USER_LOG_ON");
    public static final EventType<LoginEvent> USER_LOG_OFF = new EventType(ANY, "USER_LOG_OFF");
    public static final EventType<LoginEvent> USER_LOG_FAIL = new EventType(ANY, "USER_LOG_FAIL");
    
    public LoginEvent() {
        this(USER_LOG_OFF);
    }
    
    public LoginEvent(EventType<? extends Event> arg0) {
        super(arg0);
    }
    public LoginEvent(Object arg0, EventTarget arg1, EventType<? extends Event> arg2) {
        super(arg0, arg1, arg2);
    }  
}
