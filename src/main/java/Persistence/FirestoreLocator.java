package Persistence;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FirestoreLocator {

    private static FirestoreLocator instance = new FirestoreLocator();
    private static Boolean visitado = false;

    public FirestoreLocator() {
    }

    public static FirestoreLocator getInstance() {
        return instance;
    }

    public void getEscrita() throws IOException {
        if (!this.visitado) {
            conectaEscrita();
            visitado = true;
        }
    }

    private void conectaEscrita() throws FileNotFoundException, IOException {
        FileInputStream serviceAccount = new FileInputStream("./src/main/resources/client_secret.json.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setStorageBucket("readergameserver.appspot.com")
                .build();

        FirebaseApp.initializeApp(options);
    }

}
