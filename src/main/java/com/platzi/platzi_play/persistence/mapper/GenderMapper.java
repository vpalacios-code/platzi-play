package com.platzi.platzi_play.persistence.mapper;

import com.platzi.platzi_play.domain.Gender;
import org.mapstruct.Named;


public class GenderMapper {

    @Named("stringToGender")
    public static Gender stringToGender(String gender){
        if(gender == null)
            return null;


        return switch (gender.toUpperCase()){
            case "ACCION" -> Gender.ACTION;
            case "COMEDIA" -> Gender.COMEDY;
            case "DRAMA" -> Gender.DRAMA;
            case "ANIMADA" -> Gender.ANIMATED;
            case "TERROR" -> Gender.HORROR;
            case "CIENCIA_FICCION" -> Gender.SCI_FI;
            default -> null;
        };
    }

    @Named("genderToString")
    public static String genderToString(Gender gender){
        if(gender == null)
            return null;

        return switch (gender){
            case ACTION -> "ACCION";
            case COMEDY -> "ACCION";
            case DRAMA -> "ACCION";
            case ANIMATED -> "ACCION";
            case HORROR -> "ACCION";
            case SCI_FI -> "ACCION";
            default -> null;
        };
    }
}
