import java.util.List;

public abstract class AbstractDataProcessor {
    protected String sortingType;

    public AbstractDataProcessor(String sortingType) {
        this.sortingType = sortingType;
    }

    public abstract void process(List<String> inputData);
    public abstract void printResult();
}
