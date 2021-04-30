package res.ressources.pranks;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

public class PrankJSONParser
{
    final static String PRANKS_PATH = System.getProperty("user.dir") + "/assets/pranks.json";
    public static List<Prank> getPranks()
    {
        LinkedList<Prank> pranksList = new LinkedList<>();
        JSONParser jsonParser = new JSONParser();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(PRANKS_PATH), StandardCharsets.UTF_8)))
        {
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;

            for (Object prank : (JSONArray) jsonObject.get("pranks"))
            {
                pranksList.add(parsePrankObject((JSONObject) prank));
            }

        } catch (IOException | ParseException e)
        {
            e.printStackTrace();
        }
        return pranksList;
    }

    private static Prank parsePrankObject(JSONObject prank)
    {
        return new Prank((String) prank.get("title"), (String) prank.get("body"));
    }
}