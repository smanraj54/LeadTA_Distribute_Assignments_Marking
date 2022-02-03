import javax.mail.MessagingException;
import java.io.IOException;
import java.util.*;

public class Main {

    public static String ASSIGNMENT_NAME = "Assignment2";
    public static final String SENDER_EMAIL = "software5408group15@gmail.com";
    public static final String PASSWORD = "software5408group15123";

    public static void main(String arr[]) throws IOException, MessagingException {
        WriteToFile.getInstance();
        Map<String, String> TAnameEmail = new HashMap<>();
        List<String> TAnames =  FetchDataFromCSV.getInstance().getData("./Data/Assignments/TA_Details/TA_Details.csv");
        List<String> Students =  FetchDataFromCSV.getInstance().getData("./Data/Assignments/Students/CSCI5408_Students_List.csv");
        String first = Students.get(0);
        Students.remove(0);

        System.out.println(Students.size());
        Set<String> allData = new HashSet<>();

        allData.addAll(Students);   //Testing

        List<String> TAs = new ArrayList<>();

        for(String name:TAnames){
            String val[] = name.split(",");
            TAnameEmail.put(val[0], val[1]);
            TAs.add(val[0]);

        }

        Map<String, List<String>> TAs_Students = SplitStudents.getInstance().getStudentsPerTA(Students, TAs);

        System.out.println(TAs_Students);
        for(String TA : TAs){
            allData.removeAll(TAs_Students.get(TA));                    //Testing
            System.out.println(TA+"\t"+TAs_Students.get(TA).size());
            String path = "./Data/Assignments/"+Main.ASSIGNMENT_NAME+"/"+Main.ASSIGNMENT_NAME+"_"+TA.replace(" ", "_")+".csv";
            WriteToFile.getInstance().writeToFile(first,path, TAs_Students.get(TA));
            sendEmailToTA(TAnameEmail.get(TA), TA, path);
        }
        System.out.println(allData.size());             //Testing




    }

    private static void sendEmailToTA(String To, String TA, String path){
        //String to = "mn697903@dal.ca";
        String title = "Data-5408 "+Main.ASSIGNMENT_NAME+" Assigned Students for marking!!!";
        String body = "Hi "+TA+",\n<br/>"+"Please find the students list for "+Main.ASSIGNMENT_NAME+" Attached to this email. \n<br/>Happy Marking!!!\n\n<br/><br/>Thanks & regards,\n<br/>Manraj Singh";

        try {
            EmailFile.getInstance().sendEmail(To, title, body, path);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

}
