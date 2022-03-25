package com.revature.controllers;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import com.revature.models.JsonResponse;
import com.revature.models.User;
import org.eclipse.jetty.server.Authentication;
import com.revature.services.UserService;

public class UserController {
    private UserService userService;

    public UserController(){
        this.userService = new UserService();
    }

    public UserController(UserService userService){

        this.userService = userService;
    }

    public void createUser(Context context){

        JsonResponse jsonResponse;
        //get user from json string
        User user = context.bodyAsClass(User.class);
        if(userService.createUser(user)){
            jsonResponse = new JsonResponse(true, "User:" + user.getUsername() + " , ID:" + user.getuserId() + " has been created", null);
        }else{
            jsonResponse = new JsonResponse(false, "Username already exists", null);
        }

        context.json(jsonResponse);
    }


}
