package patterns.strategy.composite;

public class File implements FileSystemItem {
    private final String name;

    public File(String name) {
        this.name = name;
    }

    public void display(String indent) {
        System.out.println(indent + "File: " + name);
    }
}