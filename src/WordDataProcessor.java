import java.util.*;

public class WordDataProcessor extends AbstractDataProcessor {

    SortedMap<String, Integer> words = new TreeMap<>();
    int wordsLength = 0;

    public WordDataProcessor(String sortingType) {
        super(sortingType);
    }

    @Override
    public void process(List<String> inputData) {
        for (String item : inputData) {
            String[] parts = item.split("\\s+");
            for (String part : parts) {
                words.put(part, words.getOrDefault(part, 0) + 1);
                wordsLength++;
            }
        }
    }

    @Override
    public void printResult() {
        System.out.printf("Total words: %d.\n", wordsLength);
        if ("natural".equals(sortingType)) {
            System.out.print("Sorted data: ");
            words.forEach((key, count) -> System.out.print((key + " ").repeat(count)));
        } else if ("byCount".equals(sortingType)) {
            words.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .forEach(entry -> System.out.printf("%s: %d time(s), %d%%%n", entry.getKey(), entry.getValue(), Math.round(entry.getValue() * 100.0 / wordsLength)));
        }
    }
}
