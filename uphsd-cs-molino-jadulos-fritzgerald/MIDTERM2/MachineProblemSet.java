import java.io.*;
import java.util.*;

public class MachineProblemSet {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Prompt user for dataset file path
        System.out.print("Enter the dataset file path: ");
        String filePath = sc.nextLine();

        List<String[]> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Parse CSV data by splitting by comma
                records.add(line.split(","));
            }

            if (records.isEmpty()) {
                System.out.println("The file is empty.");
                return;
            }

            // MP02: Display the first 10 rows
            System.out.println("\n--- MP02: First 10 Rows ---");
            for (int i = 0; i < Math.min(11, records.size()); i++) {
                System.out.println(Arrays.toString(records.get(i)));
            }

            // MP10: Detect duplicate records
            System.out.println("\n--- MP10: Duplicate Records ---");
            Set<String> seen = new HashSet<>();
            boolean foundDuplicate = false;
            for (String[] row : records) {
                String rowString = String.join(",", row);
                if (!seen.add(rowString)) {
                    System.out.println("Duplicate found: " + rowString);
                    foundDuplicate = true;
                }
            }
            if (!foundDuplicate) System.out.println("No duplicates detected.");

            // MP20: Convert CSV dataset into JSON format
            System.out.println("\n--- MP20: JSON Format (Preview) ---");
            String[] headers = records.get(0);
            StringBuilder json = new StringBuilder("[\n");
            for (int i = 1; i < records.size(); i++) {
                json.append("  {");
                for (int j = 0; j < headers.length; j++) {
                    String val = j < records.get(i).length ? records.get(i)[j] : "";
                    json.append("\"").append(headers[j]).append("\": \"").append(val).append("\"");
                    if (j < headers.length - 1) json.append(", ");
                }
                json.append("}").append(i == records.size() - 1 ? "" : ",\n");
            }
            json.append("\n]");
            System.out.println(json.toString());

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}