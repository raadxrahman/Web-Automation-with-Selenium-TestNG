package Configuration;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigUserDataCSV {

    @DataProvider
    public Object[] UsersCSV() throws IOException {
        List<Object> objectArrayList = new ArrayList<>();

        CSVParser csvRecords = new CSVParser(

                new FileReader("./src/test/resources/userregistration.csv"),
                CSVFormat.DEFAULT.withFirstRecordAsHeader());

        for (CSVRecord csvRecord : csvRecords) {

            String fName = csvRecord.get("fName");
            String lName = csvRecord.get("lName");
            String email = csvRecord.get("email");
            String password = csvRecord.get("password");
            String phone = csvRecord.get("phone");
            String address = csvRecord.get("address");

            objectArrayList.add(new Object[]{fName, lName, email, password, phone, address});
        }

        return objectArrayList.toArray(new Object[0][]);
    }
}
