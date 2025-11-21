package Logging;

import java.io.PrintWriter;

public class LoggerSingleton {

    private static LoggerSingleton instance;
    private PrintWriter writer;

    private LoggerSingleton() {
        try {
            this.writer = new PrintWriter("game.log");
        } catch(Exception e) {}
    }

    public static LoggerSingleton getInstance() {
        if (instance == null) instance = new LoggerSingleton();
        return instance;
    }

    public void log(String type, String message) {
        writer.println("[" + type + "] " + message);
        writer.flush();
    }
}
