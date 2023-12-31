package Main;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.LinkedList;

public class RevendeurRepo {
    private static final String FILE_NAME = "Revendeurs.json";
    private static final Gson GSON = new Gson();

    public LinkedList<Revendeur> get() {
        try (FileReader reader = new FileReader(FILE_NAME)) {
            Type listType = new TypeToken<LinkedList<Revendeur>>(){}.getType();
            return GSON.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void put(Revendeur a) {
        LinkedList<Revendeur> revendeurs = get();
        if (revendeurs == null) {
            revendeurs = new LinkedList<>();
        }
        revendeurs.add(a);
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            GSON.toJson(revendeurs, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
