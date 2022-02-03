import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplitStudents {

    private static SplitStudents instance = null;
    private SplitStudents(){}

    public static SplitStudents getInstance() {
        if(instance ==null){
            instance = new SplitStudents();
        }
        return instance;
    }

    public Map<String, List<String>> getStudentsPerTA(List<String> Students, List<String> TAs){

        //int studentsPerTA[] = new int[TAs.size()];
        Map<String, List<String>> StudentsPerTA = new HashMap<>();

        int countPerTA = (int)(Students.size()/TAs.size());
        int random = 0, iter = 0;

        for(iter = 0; iter<countPerTA*TAs.size(); iter++){
            for(;;) {
                random = ((int) (Math.random() * 10000)) % TAs.size();
                if (!StudentsPerTA.containsKey(TAs.get(random)) || StudentsPerTA.get(TAs.get(random)).size() < countPerTA) {
                    break;
                }
            }
                if(!StudentsPerTA.containsKey((TAs.get(random)))){
                    StudentsPerTA.put(TAs.get(random), new ArrayList<>());
                }

                StudentsPerTA.get(TAs.get(random)).add(Students.get(iter));

        }

        for(; iter<Students.size(); iter++){
            random = ((int) (Math.random() * 10000)) % TAs.size();
            StudentsPerTA.get(TAs.get(random)).add(Students.get(iter));
        }

        return StudentsPerTA;

    }



}
