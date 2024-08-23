import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class login {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JPanel loginPanel;
    private JFrame frame;
    private JButton SALIRButton;

    public login() {
        frame = new JFrame("Login");
        frame.setContentPane(loginPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                MongoDatabase database = Conexion.connectToDatabase();
                MongoCollection<Document> users = database.getCollection("users");

                Document user = users.find(new Document("username", username).append("password", password)).first();

                if (user != null) {
                    String role = user.getString("role");
                    if ("admin".equalsIgnoreCase(role)) {
                        new ADMIN();
                    } else if ("student".equalsIgnoreCase(role)) {
                        new ESTUDIANTES(username);
                    }
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "datos incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }


}
