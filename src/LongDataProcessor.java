import java.util.*;

public class LongDataProcessor extends AbstractDataProcessor {

    private final List<Long> numbers = new ArrayList<>();


    public LongDataProcessor(String sortingType) {
        super(sortingType);
    }

    @Override
    public void process(List<String> inputData) {
        for (String item : inputData) {
            String[] parts = item.split("\\s+");
            for (String part : parts) {
                try {
                    numbers.add(Long.parseLong(part));
                } catch (NumberFormatException e)
                {
                    System.out.printf("\"%s\" is not a long. It will be skipped.\n", item);
                }
            }

        }
    }

    @Override
    public void printResult() {
        int numsLength = numbers.size();
        System.out.printf("Total numbers: %d.\n", numsLength);
        if ("natural".equals(sortingType)) {
            Collections.sort(numbers);
            System.out.print("Sorted data: ");
            numbers.forEach(elem -> System.out.print(elem + " "));
        } else if ("byCount".equals(sortingType)) {
            Map<Long, Integer> sortedInput = new TreeMap<>();
            for (Long number : numbers) {
                sortedInput.put(number, sortedInput.getOrDefault(number, 0) + 1);
            }
            sortedInput.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .forEach(entry -> System.out.printf("%s: %d time(s), %d%%%n", entry.getKey(), entry.getValue(), Math.round(entry.getValue() * 100.0 / numsLength)));
        }
    }

}

