package freedownloadhere.blocknodes.loaders;

import com.google.gson.Gson;
import freedownloadhere.blocknodes.node.NodeScene;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SceneLoader
{
    private SceneLoader() { }

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
            Gson gson = new Gson();

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
}
