// Factory Pattern - Creates objects without exposing creation logic

// Step 1: Create interface
interface Vehicle {
    void drive();
    void displayInfo();
}

// Step 2: Create multiple implementations
class Car implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Car is driving on road");
    }
    
    @Override
    public void displayInfo() {
        System.out.println("This is a Car");
    }
}

class Bike implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Bike is riding on road");
    }
    
    @Override
    public void displayInfo() {
        System.out.println("This is a Bike");
    }
}

class Truck implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Truck is driving on highway");
    }
    
    @Override
    public void displayInfo() {
        System.out.println("This is a Truck");
    }
}

// Step 3: Create Factory class
class VehicleFactory {
    // Factory method that returns object based on user input
    public static Vehicle createVehicle(String vehicleType) {
        if (vehicleType == null) {
            return null;
        }
        
        switch (vehicleType.toLowerCase()) {
            case "car":
                return new Car();
            case "bike":
                return new Bike();
            case "truck":
                return new Truck();
            default:
                System.out.println("Invalid vehicle type: " + vehicleType);
                return null;
        }
    }
}

// Demo class to test Factory Pattern
public class FactoryPattern {
    public static void main(String[] args) {
        System.out.println("=== Factory Pattern Demo ===\n");
        
        // Step 4: Return object based on user input
        Vehicle car = VehicleFactory.createVehicle("car");
        Vehicle bike = VehicleFactory.createVehicle("bike");
        Vehicle truck = VehicleFactory.createVehicle("truck");
        
        // Step 5: Execute required method
        if (car != null) {
            car.displayInfo();
            car.drive();
            System.out.println();
        }
        
        if (bike != null) {
            bike.displayInfo();
            bike.drive();
            System.out.println();
        }
        
        if (truck != null) {
            truck.displayInfo();
            truck.drive();
        }
    }
}
