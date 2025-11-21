package Logging;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class LoggerSingleton {
    private static LoggerSingleton instance;
    private PrintWriter writer;
    private static final String LOG_FILE = "C:\\Users\\user\\Desktop\\undercover\\jeu-Undercover\\game.log";
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private LoggerSingleton() {
        try {
            // Crée le dossier si nécessaire
            File file = new File(LOG_FILE);
            File parent = file.getParentFile();
            if (!parent.exists()) {
                parent.mkdirs();
            }
            // Crée le writer pour écrire dans le fichier
            this.writer = new PrintWriter(new FileWriter(file, true));
            System.out.println("Logger créé : " + LOG_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static LoggerSingleton getInstance() {
        if (instance == null) {
            instance = new LoggerSingleton();
        }
        return instance;
    }
    public void log(String type, String message) {
        String timestamp = LocalDateTime.now().format(dtf);
        writer.println("[" + timestamp + "][" + type + "] " + message);
        writer.flush();
    }
    public void close() {
        if (writer != null) writer.close();
    }
}
