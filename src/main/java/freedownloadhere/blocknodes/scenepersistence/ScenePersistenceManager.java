package freedownloadhere.blocknodes.scenepersistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import freedownloadhere.blocknodes.node.NodeScene;
import freedownloadhere.blocknodes.node.action.KeyInputAction;
import freedownloadhere.blocknodes.node.action.MouseInputAction;
import freedownloadhere.blocknodes.node.action.NodeAction;
import freedownloadhere.blocknodes.utils.gson.RuntimeTypeAdapterFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class ScenePersistenceManager
{
    // Funny static initializer. Hoho. Comical.
    public static RuntimeTypeAdapterFactory<NodeAction> Adapter =
            RuntimeTypeAdapterFactory.of(NodeAction.class)
            .registerSubtype(NodeAction.class)
            .registerSubtype(KeyInputAction.class)
            .registerSubtype(MouseInputAction.class);

    private ScenePersistenceManager() { }

    public static NodeScene LoadScene(String sceneName)
    {
        NodeScene loadedScene;

        try
        {
            File inputFile = new File(sceneName + ".json");
            if(!inputFile.exists())
                return null;

            Scanner fileReader = new Scanner(inputFile);
            fileReader.useDelimiter("\\Z");
            String json = fileReader.next();

            Gson gson =
                    new GsonBuilder()
                    .setPrettyPrinting()
                    .registerTypeAdapterFactory(Adapter)
                    .create();

            loadedScene = gson.fromJson(json, NodeScene.class);
            fileReader.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Could not find the file!");
            return null;
        }

        return loadedScene;
    }

    public static void SaveScene(NodeScene scene)
    {
        Gson gson =
                new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapterFactory(Adapter)
                .create();

        String json = gson.toJson(scene);

        try
        {
            String pathName = scene.getName() + ".json";
            File outputFile = new File(pathName);
            FileWriter fileWriter = new FileWriter(pathName);

            outputFile.createNewFile();

            fileWriter.write(json);
            fileWriter.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getCause().getMessage());
        }
    }
}
