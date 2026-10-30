// Singleton Pattern - Ensures only one instance of a class exists
class Singleton {
    private static Singleton instance;
    
    // Private constructor - prevents instantiation from outside
    private Singleton() {
    }
    
    // Public static method to get singleton instance
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    } 
    
    public void display() {
        System.out.println("Singleton Object Created");
    }
}

// Demo class to test Singleton Pattern
public class SingletonPattern {
    public static void main(String[] args) {
        System.out.println("=== Singleton Pattern Demo ===");
        
        // Create first instance
        Singleton s1 = Singleton.getInstance();
        
        // Create second instance
        Singleton s2 = Singleton.getInstance();
        
        // Both should reference the same object
        s1.display();
        System.out.println("s1 == s2: " + (s1 == s2));  // Output: true
        System.out.println("s1 hash: " + s1.hashCode());
        System.out.println("s2 hash: " + s2.hashCode());
    }
}
