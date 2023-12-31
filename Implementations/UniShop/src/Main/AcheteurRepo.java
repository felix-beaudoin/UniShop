package Main;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedList;

public class AcheteurRepo {
    private static final String FILE_NAME = "Acheteurs.json";
    private static final Gson GSON = new Gson();

    public LinkedList<Acheteur> get() {
        try (FileReader reader = new FileReader(FILE_NAME)) {
            Type listType = new TypeToken<LinkedList<Acheteur>>(){}.getType();
            return GSON.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void put(Acheteur a) {
        LinkedList<Acheteur> acheteurs = get();
        if (acheteurs == null) {
            acheteurs = new LinkedList<>();
        }
        acheteurs.add(a);
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            GSON.toJson(acheteurs, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
