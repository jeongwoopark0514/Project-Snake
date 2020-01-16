package database;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

/**
 * A Session manager that offers the ability to store, retrieve and create cookies.
 * With these cookies the program can keep track of a user, meaning he/she doesn't have to login.
 * These cookies are saved in the database.
 * This manager also makes the username available to the rest of the program.
 */
public class SessionManager {
    private static SessionManager INSTANCE;
    @Getter
    @Setter
    private DBconnect dbconnect = DBconnect.getInstance();
    @Getter
    private String username;

    /**
     * Get the instance of the SessionManager,
     * if there is none one will be created.
     *
     * @return the instance that is to be used.
     */
    public static SessionManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SessionManager();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }

    /**
     * Create a cookie, which is based on a random UUID.
     *
     * @param writer the writer that writes to the file.
     * @return the string representing the value of the cookie.
     */
    public String createCookie(PrintWriter writer) {
        String cookie = UUID.randomUUID().toString();
        writer.println(cookie);
        writer.close();
        return cookie;
    }

    /**
     * Save a username to the database, together with a cookie.
     * The method creates a cookie, stores this is in a file and puts it in the database.
     *
     * @param writer   the writer to write the data with.
     * @param username the username associated with this cookie.
     * @return true if saved, false if not.
     */
    public boolean saveCookie(PrintWriter writer, String username) {
        String cookie = createCookie(writer);
        if (cookie == null) {
            return false;
        }
        return dbconnect.storeCookie(username, cookie);
    }

    /**
     * Retrieve user data from the database using a previously created cookie.
     * This will read the cookie value from the file and send this to the database,
     * there the value will be checked and if there is an entry there exists a session.
     *
     * @param reader the reader that reads the file and provides the data.
     * @return the username if found or null if there was no session or error.
     */
    public String retrieveUserData(BufferedReader reader) {
        try {
            String cookie = reader.readLine();
            reader.close();
            String username = dbconnect.getUsername(cookie);
            this.username = username;
            return username;
        } catch (Exception e) {
            System.out.println("No cookie found");
            return null;
        }
    }
}
