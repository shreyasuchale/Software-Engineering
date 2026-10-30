import java.util.ArrayList;
import java.util.List;

// Observer Pattern - Defines one-to-many relationship between objects

// Observer Interface
interface Observer {
    void update(String message);
}

// Step 1: Create Subject class
class Subject {
    private List<Observer> observers = new ArrayList<>();
    private String state;
    
    // Step 2: Register observers
    public void attach(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            System.out.println("Observer attached: " + observer.getClass().getSimpleName());
        }
    }
    
    public void detach(Observer observer) {
        observers.remove(observer);
        System.out.println("Observer detached: " + observer.getClass().getSimpleName());
    }
    
    // Step 3: Notify observers whenever state changes
    public void setState(String state) {
        System.out.println("\nSubject State Changed to: " + state);
        this.state = state;
        notifyAllObservers();
    }
    
    private void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update(this.state);
        }
    }
    
    public String getState() {
        return state;
    }
}

// Concrete Observer 1
class EmailObserver implements Observer {
    private String name;
    
    public EmailObserver(String name) {
        this.name = name;
    }
    
    @Override
    public void update(String message) {
        // Step 4: Display updated values
        System.out.println(name + " (Email): Received update - " + message);
    }
}

// Concrete Observer 2
class SMSObserver implements Observer {
    private String phoneNumber;
    
    public SMSObserver(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    @Override
    public void update(String message) {
        System.out.println(phoneNumber + " (SMS): Received update - " + message);
    }
}

// Concrete Observer 3
class LogObserver implements Observer {
    @Override
    public void update(String message) {
        System.out.println("[LOG]: State changed to - " + message);
    }
}

// Demo class to test Observer Pattern
public class ObserverPattern {
    public static void main(String[] args) {
        System.out.println("=== Observer Pattern Demo ===");
        
        // Create subject
        Subject subject = new Subject();
        
        // Create observers
        EmailObserver emailObserver = new EmailObserver("user@example.com");
        SMSObserver smsObserver = new SMSObserver("+1-800-123-4567");
        LogObserver logObserver = new LogObserver();
        
        // Register observers
        subject.attach(emailObserver);
        subject.attach(smsObserver);
        subject.attach(logObserver);
        
        // Change state and notify observers
        subject.setState("Order Confirmed");
        subject.setState("Order Dispatched");
        subject.setState("Order Delivered");
        
        System.out.println("\n--- Detaching SMS Observer ---");
        subject.detach(smsObserver);
        
        subject.setState("Order Returned");
    }
}
