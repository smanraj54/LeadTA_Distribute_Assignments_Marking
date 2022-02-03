import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class WriteToFile {

    private static WriteToFile instance = null;
    private WriteToFile(){

        try {
            File file = new File("./Data/Assignments/"+Main.ASSIGNMENT_NAME);
            deleteDir(file);
            Files.createDirectories(Paths.get("./Data/Assignments/"+Main.ASSIGNMENT_NAME));
            Files.createDirectories(Paths.get("./Data/Assignments/TA_Details"));
            Files.createDirectories(Paths.get("./Data/Assignments/Students"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static WriteToFile getInstance() {
        if(instance==null){
            instance = new WriteToFile();
        }
        return instance;
    }

    public void writeToFile(String first, String path, List<String> Students){
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = ConnectToFile(path);
            bufferedWriter.write(first+"\n");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        for(String student : Students){
            try {
                bufferedWriter.write(student+"\n");
            } catch (IOException ioException) {

            }
        }
        try {
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private BufferedWriter ConnectToFile(String path) throws IOException {
        FileWriter fileWriter = new FileWriter(new File(path), true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        return bufferedWriter;
    }

    private void deleteDir(File dir) {
        File[] files = dir.listFiles();
        if(files != null) {
            for (final File file : files) {
                deleteDir(file);
            }
        }
        dir.delete();
    }
}
