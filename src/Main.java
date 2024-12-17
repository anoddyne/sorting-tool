import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ArgsParser parser = new ArgsParser();
        Map<String, String> params = parser.parseArgs(args);


        String outputFile = params.get("outputFile");
        PrintStream originalOut = System.out;

        if (outputFile != null) {
            try {
                PrintStream fileOut = new PrintStream(new FileOutputStream(outputFile));
                System.setOut(fileOut);
            } catch (FileNotFoundException e) {
                System.out.println("Error opening output file: " + e.getMessage());
                System.exit(1);
            }
        }

        try {
            String dataType = params.getOrDefault("dataType", "word");
            String sortingType = params.getOrDefault("sortingType", "natural");
            AbstractDataProcessor processor;

            switch (dataType) {
                case "long" -> processor = new LongDataProcessor(sortingType);
                case "line" -> processor = new LineDataProcessor(sortingType);
                case "word" -> processor = new WordDataProcessor(sortingType);
                case null -> throw new RuntimeException("No sorting type defined!");
                default -> throw new RuntimeException("Unknown data type: " + dataType);
            }

            List<String> inputData = parser.getInputData(params);
            processor.process(inputData);
            processor.printResult();
        } finally {
            System.setOut(originalOut);
        }

    }
}