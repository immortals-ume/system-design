package design.patterns.creational;

// Product interface
interface Vehicle {
    void drive();
}

// Concrete products
class Car implements Vehicle {
    public void drive() {
        System.out.println("Driving a car");
    }
}

class Bike implements Vehicle {
    public void drive() {
        System.out.println("Riding a bike");
    }
}

// Factory class
class VehicleFactory {
    public static Vehicle getVehicle(String type) {
        if (type.equalsIgnoreCase("car")) {
            return new Car();
        } else if (type.equalsIgnoreCase("bike")) {
            return new Bike();
        }
        throw new IllegalArgumentException("Unknown vehicle type");
    }
}

// Usage in client code
public class FactoryDemo {
    public static void main(String[] args) {
        Vehicle vehicle = VehicleFactory.getVehicle("car");
        vehicle.drive();  // Output: Driving a car
    }
}
