package com.mynotesapp.server.mynotesappserver.Entities;

public class SlugNameSetter {
    public static String setSlugByName(String name){
        return name.replace(" ", "_").toLowerCase();
    }
}
