package Configuration;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.testng.annotations.DataProvider;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigDataProviderCSV {

    @DataProvider
    public Object[] ItemsCSV() throws IOException {

        List<Object> objectArrayList = new ArrayList<>();

        CSVParser csvRecords = new CSVParser(

                new FileReader("./src/test/resources/items.csv"),
                CSVFormat.DEFAULT.withFirstRecordAsHeader());

        for (CSVRecord csvRecord : csvRecords) {

            String itemName = csvRecord.get("itemName");
            String quantity = csvRecord.get("quantity");
            String amount = csvRecord.get("amount");
            String date = csvRecord.get("date");
            String month = csvRecord.get("month");
            String year = csvRecord.get("year");
            String remarks = csvRecord.get("remarks");

            objectArrayList.add(new Object[]{itemName, quantity, amount, date, month, year, remarks});
        }

        return objectArrayList.toArray(new Object[0][]);
    }
}
