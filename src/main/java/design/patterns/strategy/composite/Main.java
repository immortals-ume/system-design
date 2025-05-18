package design.patterns.strategy.composite;

public class Main {
    public static void main(String[] args) {
        File file1 = new File("resume.pdf");
        File file2 = new File("budget.xlsx");

        Folder personalFolder = new Folder("Personal");
        personalFolder.add(file1);

        Folder workFolder = new Folder("Work");
        workFolder.add(file2);
        workFolder.add(personalFolder); // Adding a folder inside another

        Folder root = new Folder("MyDocuments");
        root.add(workFolder);

        root.display(""); // Recursive tree display
    }
}