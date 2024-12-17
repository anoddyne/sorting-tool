import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ArgsParser {

    public Map<String, String> parseArgs(String[] args) {
        final String[] ALLOWED_DATA_TYPES = {"line", "word", "long"};
        final String[] ALLOWED_SORTING_TYPES = {"byCount", "natural"};
        Map<String, String> params = new HashMap<>();


        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-dataType":
                    if (i + 1 < args.length && Arrays.asList(ALLOWED_DATA_TYPES).contains(args[++i])) {
                        params.put("dataType", args[i]);
                    } else {
                        System.out.println("Error: No valid data type defined! Allowed values: " + Arrays.toString(ALLOWED_DATA_TYPES));
                        System.exit(1);
                    }
                    break;
                case "-sortingType":
                    if (Arrays.asList(ALLOWED_SORTING_TYPES).contains(args[++i]) && i + 1 < args.length) {
                        params.put("sortingType", args[i]);
                    } else {
                        System.out.println("Error: No valid sorting type defined! Allowed values: " + Arrays.toString(ALLOWED_SORTING_TYPES));
                        System.exit(1);
                    }
                    break;
                case "-inputFile":
                    if (i + 1 < args.length) params.put("inputFile", args[++i]);
                    break;
                case "-outputFile":
                    if (i + 1 < args.length) params.put("outputFile", args[++i]);
                    break;
                default:
                    System.out.printf("Unknown argument: %s.\n", args[i]);
            }

        }
        return params;
    }


    public List<String> getInputData(Map<String, String> params) {
        List<String> data = new ArrayList<>();
        String inputFile = params.get("inputFile");

        if (inputFile != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    data.add(line);
                }
            } catch (IOException e) {
                System.err.println("Error reading the input file: " + e.getMessage());
                System.exit(1);
            }
        } else {
            Scanner sc = new Scanner(System.in);
            while (sc.hasNextLine()) {
                data.add(sc.nextLine());
            }
            sc.close();
        }

        return data;
    }

}
