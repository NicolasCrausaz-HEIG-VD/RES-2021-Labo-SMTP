package res.ressources.pranks;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import res.ressources.config.ConfigManager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class PrankJSONParser
{
    public static LinkedList<Prank> getPranks()
    {
        LinkedList<Prank> pranksList = new LinkedList<>();
        JSONParser jsonParser = new JSONParser();

        try (BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + ConfigManager.getInstance().getPranksFile())))
        {

            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject)obj;

            for (Object pers : (JSONArray) jsonObject.get("pranks"))
            {
                pranksList.add(parsePrankObject((JSONObject) pers));
            }

        } catch (IOException | ParseException e)
        {
            e.printStackTrace();
        }
        return pranksList;
    }

    private static Prank parsePrankObject(JSONObject pers)
    {
        return new Prank((String) pers.get("title"), (String) pers.get("body"));
    }
}
