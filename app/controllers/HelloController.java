package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class HelloController extends Controller {
    Map< Integer,String > map = new HashMap<>();
    public Result doSomething() {
        String message = "Hello Sym. This is your controller!!";
        return ok(message);
    }
    public Result helloUser(String name) {
        String message = "Hello "+name;
        return ok(message);
    }
    public Result helloUserWithDetails() {
        JsonNode requestJson = request().body().asJson();
        String firstName = null;
        String lastName = null;
        if (requestJson.has("first_name")) {
            firstName = requestJson.get("first_name").asText();
        }
        if (requestJson.has("last_name")) {
            lastName = requestJson.get("last_name").asText();
        }
        if (firstName != null && lastName != null) {
            String message = "Hello "+firstName+" "+lastName;
            return ok(message);
        }
        return badRequest("Please provide the first name and last name!!!");
    }
    public Result helloUserWithDetailsMap(){
        JsonNode requestJson = request().body().asJson();
        String userName = null;
        String message = null;
        int userId = 0;
        if(requestJson.has("user_name")){
            userName = requestJson.get("user_name").asText();
        }
        if(requestJson.has("user_id")){
            userId = requestJson.get("user_id").asInt();
        }
        if(userId != 0 && userName != null){
            map.put(userId,userName);
            for (Integer id: map.keySet()){
                String key = id.toString();
                String value = map.get(id).toString();
                message = "\n Your name is "+value+" and your id is "+key;
                return ok(message);
            }
        }
        return badRequest("Please provide user name and user id");
    }
   public Result helloUserWithDetailsMapGet(String userid){
        int uid = Integer.parseInt(userid);
        String message = null;
        for(Integer id : map.keySet()){
            if(id == uid){
                String key = id.toString();
                String value = map.get(id).toString();
                message = "Hello "+value+". Your id is "+key;
            }
        }
        if(message != null){
            return ok(message);
        }
        return badRequest("Please enter a valid  user id");

    }
}
