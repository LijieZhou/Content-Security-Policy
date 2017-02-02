package contentparser;


import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.lang.String;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 *
 * @author lijie
 */


public class ContentParser {

    
    public static void main(String[] args)  {
     
        try{
            
            //read the json File
//            FileReader reader = new FileReader("/Users/lijie/Desktop/facebook.har");
            FileReader reader = new FileReader("/Users/lijie/NetBeansProjects/contentParser/src/contentparser/facebook.har");
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
//            System.out.println(jsonObject);
            
            //Handle a structure into the json object 
            JSONObject structure =  (JSONObject) jsonObject.get("log");
//            System.out.println("Into log structure, version: " + structure.get("version"));
            
            //Get a jsonArray "pages" from the structure
            JSONArray pages = (JSONArray) structure.get("pages");
//            System.out.println(pages);
            
            //Get a jsonArray "entries" from the structure
            JSONArray entries = (JSONArray) structure.get("entries");
//            System.out.println(entries);
            
            //Get a jsonObject from the entries
            int len = entries.size();
            System.out.println(len);//There are 102 elements in the entries array
            
            //Take each value from the json arrray seperately
            Iterator i = entries.iterator();
            
            while(i.hasNext()){
                JSONObject innerObj = (JSONObject) i.next();

                //Get all the "responses" in the "Entries"
                JSONObject responses = (JSONObject) innerObj.get("response");
                
                //Get all the "headers" in the "response"
                JSONArray headers = (JSONArray) responses.get("headers");
//                System.out.println(headers);
                
                //Get the content-security-policy
                if(headers.get(0) != null){
                    
                    JSONObject innerHeader = (JSONObject) headers.get(0);
//                    System.out.println(innerHeader);

                    //Get a String from the JSON Object
                    String name = (String) innerHeader.get("name");
                    if (name.equals("content-security-policy")){
                        System.out.println(innerHeader.get("value"));
                    }
                }
                
                
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        
 
    }
}
