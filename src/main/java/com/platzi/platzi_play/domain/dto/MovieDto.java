package com.platzi.platzi_play.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.platzi.platzi_play.domain.Gender;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MovieDto(
        Long id,
        @NotBlank(message = "Title is required")
        String title,
        @Positive(message = "The duration of the film must be greater than 0")
        Integer duration,
        @NotNull(message = "The Gender is required")
        Gender gender,
        LocalDate dateRelease,
        @Min(value = 0, message = "The classification of be greater than 0")
        @Max(value = 5, message = "The classification 5")
        BigDecimal classification,
        Boolean status
) {
}
