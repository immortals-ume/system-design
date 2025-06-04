package design.patterns;

import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(String message);
}

class EmailSubscriber implements Observer {
    public void update(String message) {
        System.out.println("Email: " + message);
    }
}

class SMSSubscriber implements Observer {
    public void update(String message) {
        System.out.println("SMS: " + message);
    }
}

class PushSubscriber implements Observer {
    public void update(String message) {
        System.out.println("Push Notification: " + message);
    }
}

class NotificationService {
    private final List<Observer> observers = new ArrayList<>();

    public void subscribe(Observer o) {
        observers.add(o);
    }

    public void unsubscribe(Observer o) {
        observers.remove(o);
    }

    public void notifyUsers(String msg) {
        for (Observer o : observers) o.update(msg);
    }
}

public class Main {
    public static void main(String[] args) {
        NotificationService notificationService = new NotificationService();

        Observer email = new EmailSubscriber();
        Observer sms = new SMSSubscriber();
        Observer push = new PushSubscriber();

        notificationService.subscribe(email);
        notificationService.subscribe(sms);
        notificationService.subscribe(push);

        notificationService.notifyUsers("New promo launched!");

        notificationService.unsubscribe(sms);

        notificationService.notifyUsers("50% discount on all items!");
    }
}
