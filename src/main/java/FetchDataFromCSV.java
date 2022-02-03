import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FetchDataFromCSV {

    private static FetchDataFromCSV instance = null;
    private BufferedReader bufferedReader = null;

    public static FetchDataFromCSV getInstance() {
        if(instance == null){
            instance = new FetchDataFromCSV();
        }
        return instance;
    }

    private FetchDataFromCSV(){}

    public boolean connectToCSV(String path) {
        File file= new File(path);
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<String> getData() throws IOException {

        if(bufferedReader==null){
            return null;
        }

        List<String> rows = new ArrayList<>();
        String val = "";

        while((val = bufferedReader.readLine())!=null){
                rows.add(val);
        }

        return rows;
    }

    public List<String> getData(String path) throws IOException {
        connectToCSV(path);
        return getData();
    }

}
