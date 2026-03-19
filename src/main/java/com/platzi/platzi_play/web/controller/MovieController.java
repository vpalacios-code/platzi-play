package com.platzi.platzi_play.web.controller;

import com.platzi.platzi_play.domain.dto.MovieDto;
import com.platzi.platzi_play.domain.dto.SuggestRequestDto;
import com.platzi.platzi_play.domain.dto.UpdateMovieDto;
import com.platzi.platzi_play.domain.service.MovieService;
import com.platzi.platzi_play.domain.service.PlatziPlayAiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@Tag(name = "Movies", description = "Operation about movies of PlatziPlay")
public class MovieController {

    private final MovieService movieService;
    private final PlatziPlayAiService aiService;

    public MovieController(MovieService movieService, PlatziPlayAiService aiService) {
        this.movieService = movieService;
        this.aiService = aiService;
    }


    @GetMapping
    public ResponseEntity<List<MovieDto>> getAll(){
        return ResponseEntity.ok(this.movieService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get a movie by Id",
            description = "Return all info a movie",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Película encontrada"),
                    @ApiResponse(responseCode = "400", description = "Movie not found")
            }
    )
    public ResponseEntity<MovieDto> getById(@Parameter(description = "Id the movie", example = "9") @PathVariable long id){
        MovieDto movieDto = this.movieService.getById(id);

        if(movieDto == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(movieDto);
    }

    @PostMapping("/suggest")
    public ResponseEntity<String> generateMovieSuggestion(@RequestBody SuggestRequestDto suggestRequestDto){
        return ResponseEntity.ok(this.aiService.generateMovieSuggestions(suggestRequestDto.userPreferences()));
    }

    @PostMapping
    public ResponseEntity<MovieDto> add(@RequestBody @Valid MovieDto movieDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.movieService.add(movieDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDto> update(@PathVariable Long id, @RequestBody @Valid UpdateMovieDto updateMovieDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.movieService.update(id,updateMovieDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        this.movieService.delete(id);
        return ResponseEntity.ok().build();
    }

}
