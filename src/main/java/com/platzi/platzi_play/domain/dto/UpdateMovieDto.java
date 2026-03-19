package com.platzi.platzi_play.domain.dto;

import com.platzi.platzi_play.domain.Gender;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdateMovieDto(
        @NotBlank(message = "Title es required")
        String title,
        @PastOrPresent(message = "La fecha de lanzamiento debe ser anterior a la fecha actual")
        LocalDate dateRelease,
        @Min(value = 0, message = "Classificacion debe ser mayor a 0")
        @Max(value = 5, message = "Classificacion debe ser max. 5")
        BigDecimal classification
) {
}
