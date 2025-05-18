package design.patterns.strategy.decorater;

public interface DataSource {
    void writeData(String data);

    String readData();
}