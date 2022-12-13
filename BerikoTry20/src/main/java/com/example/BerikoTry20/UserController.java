package com.example.BerikoTry20;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepsitory userRepsitory;
    @GetMapping
    public List<User>getAllUser(){
        return userRepsitory.findAll();
    }
    @PostMapping("/login")
    public String login(@RequestParam Map<String,String> params){
        String emailId=params.get("email");
        String password = params.get("pwd");
        if(userRepsitory.loginRoute(emailId,password)!=null){
            return userRepsitory.loginRoute(emailId,password);
        }
        else
            return "NOTFOUND";


    }
    @PostMapping("/signup")
    public ResponseEntity createUser(@RequestBody User user)throws URISyntaxException{
        User savedUser = userRepsitory.save(user);
        return ResponseEntity.created(new URI("/user/"+savedUser.getEmailId())).body(savedUser);
    }
    @GetMapping("/api")
    public ResponseEntity getApiResponse(@RequestParam String phrase) throws URISyntaxException, IOException, InterruptedException {
        String strng = phrase;
        strng = strng.replace(" " ,"%20");
        phrase = "text="+strng;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://text-sentiment.p.rapidapi.com/analyze"))
                .header("content-type", "application/x-www-form-urlencoded")
                .header("X-RapidAPI-Key", "d1869f472amsh706c8dbcf4cf939p147e3bjsn048c5e14b83e")
                .header("X-RapidAPI-Host", "text-sentiment.p.rapidapi.com")
                .method("POST", HttpRequest.BodyPublishers.ofString(phrase))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return ResponseEntity.status(HttpStatus.OK)
                .body(response.body());

//        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();

    }


}
