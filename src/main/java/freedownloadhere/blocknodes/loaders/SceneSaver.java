package freedownloadhere.blocknodes.loaders;

import com.google.gson.Gson;
import freedownloadhere.blocknodes.node.NodeScene;

import java.io.File;
import java.io.FileWriter;

public class SceneSaver
{
    private SceneSaver() { }

    public static boolean SaveScene(NodeScene scene)
    {
        Gson gson = new Gson();
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
                return false;
        }

        return true;
    }
}
