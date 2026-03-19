package com.platzi.platzi_play.persistence.mapper;

import org.mapstruct.Named;

public class StatusMapper {

    @Named("stringToBoolean")
    public static Boolean stringToBoolean(String status){
        if(status == null) return null;

        return switch (status.toUpperCase()){
            case "D" -> true;
            case "N" -> false;
            default -> null;
        };
    }
}
