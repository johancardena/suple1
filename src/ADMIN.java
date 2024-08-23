
import javax.swing.*;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class ADMIN {


    public ADMIN() {
        JFrame frame = new JFrame("Admin Dashboard");
        frame.setContentPane(adminPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        createCourseButton.addActionListener(e -> createCourse());
        // Implementar acciones para los otros botones
    }

    private void createCourse() {
        MongoDatabase database = Conexion.connectToDatabase();
        MongoCollection<Document> courses = database.getCollection("courses");

        String courseName = JOptionPane.showInputDialog("Enter course name:");
        Document course = new Document("name", courseName);
        courses.insertOne(course);

        JOptionPane.showMessageDialog(null, "Course created successfully!");
    }


}
