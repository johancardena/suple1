
import javax.swing.*;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.util.List;

public class ESTUDIANTES {

    public ESTUDIANTES(String username) {
        JFrame frame = new JFrame("datos estudiante ");
        frame.setContentPane(studentPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        loadGrades(username);
    }

    private void loadGrades(String username) {
        MongoDatabase database = Conexion.connectToDatabase();
        MongoCollection<Document> students = database.getCollection("estudiantes");

        Document student = students.find(new Document("username", username)).first();
        if (student != null) {
            List<Document> grades = (List<Document>) student.get("NOTAS" );
            StringBuilder sb = new StringBuilder();
            for (Document grade : grades) {
                sb.append(grade.getString("course")).append(": ").append(grade.getString("nota")).append("\n");
            }
            gradesArea.setText(sb.toString());
        }
    }
}
