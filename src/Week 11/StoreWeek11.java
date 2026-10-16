import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class StoreWeek11 {
    public static void main(String[] args) {
        Path report = Path.of("store-order-report.csv");
        System.out.println("Week 11: File handling and CSV");
        try (BufferedWriter writer = Files.newBufferedWriter(report,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            writer.write("OrderId,Product,Quantity,UnitPrice,Total"); writer.newLine();
            writer.write("1001,Notebook,2,55.00,110.00"); writer.newLine();
            writer.write("1002,Pen,3,15.00,45.00"); writer.newLine();
            System.out.println("CSV report created at: " + report.toAbsolutePath());
            System.out.println("Rows written: " + Files.readAllLines(report).size());
        } catch (IOException e) {
            System.out.println("Could not create the report: " + e.getMessage());
        }
    }
}
