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

        try (BufferedReader reader = new BufferedReader(new FileReader(ConfigManager.getInstance().getPranksFile())))
        {

            Object jsonObject = jsonParser.parse(reader);
            JSONArray pranks = (JSONArray) jsonObject;

            for (Object pers : pranks)
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
        JSONObject persObject = (JSONObject) pers.get("pranks");
        return new Prank((String) persObject.get("subject"), (String) persObject.get("body"));
    }
}
