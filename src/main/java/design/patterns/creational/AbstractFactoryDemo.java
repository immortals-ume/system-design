package design.patterns.creational;

// Abstract product interfaces
interface Button {
    void paint();
}

interface Checkbox {
    void paint();
}

// Abstract factory interface
interface GUIFactory {
    Button createButton();

    Checkbox createCheckbox();
}

// Windows concrete products
class WindowsButton implements Button {
    public void paint() {
        System.out.println("Rendering Windows button");
    }
}

class WindowsCheckbox implements Checkbox {
    public void paint() {
        System.out.println("Rendering Windows checkbox");
    }
}

// Mac concrete products
class MacButton implements Button {
    public void paint() {
        System.out.println("Rendering Mac button");
    }
}

class MacCheckbox implements Checkbox {
    public void paint() {
        System.out.println("Rendering Mac checkbox");
    }
}

// Windows factory
class WindowsFactory implements GUIFactory {
    public Button createButton() {
        return new WindowsButton();
    }

    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}

// Mac factory
class MacFactory implements GUIFactory {
    public Button createButton() {
        return new MacButton();
    }

    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }
}

// Client code
class Application {
    private final Button button;
    private final Checkbox checkbox;

    public Application(GUIFactory factory) {
        button = factory.createButton();
        checkbox = factory.createCheckbox();
    }

    public void paint() {
        button.paint();
        checkbox.paint();
    }
}

// Usage
public class AbstractFactoryDemo {
    public static void main(String[] args) {
        GUIFactory factory;

        String osName = System.getProperty("os.name")
                .toLowerCase();
        if (osName.contains("windows")) {
            factory = new WindowsFactory();
        } else {
            factory = new MacFactory();
        }

        Application app = new Application(factory);
        app.paint();
    }
}
