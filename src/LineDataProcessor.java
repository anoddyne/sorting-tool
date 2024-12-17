import java.util.*;

public class LineDataProcessor extends AbstractDataProcessor {

    private final List<String> lines = new ArrayList<>();

    public LineDataProcessor(String sortingType) {
        super(sortingType);
    }

    @Override
    public void process(List<String> inputData) {
        lines.addAll(inputData);
    }

    @Override
    public void printResult() {
        int numsLength = lines.size();
        System.out.printf("Total lines: %d.\n", numsLength);
        if ("natural".equals(sortingType)) {
            System.out.println("Sorted data: ");
            lines.sort(Comparator.comparingInt(String::length).reversed());
            lines.forEach(System.out::println);
        } else if ("byCount".equals(sortingType)) {
            Map<String, Integer> sortedInput = new TreeMap<>();
            for (String line : lines) {
                sortedInput.put(line, sortedInput.getOrDefault(line, 0) + 1);
            }
            sortedInput.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .forEach(entry -> System.out.printf("%s: %d time(s), %d%%%n", entry.getKey(), entry.getValue(), Math.round(entry.getValue() * 100.0 / numsLength)));
        }
    }
}
