package com.example.wordsearchgame.Controller;

import com.example.wordsearchgame.Exceptions.CustomException;
import com.example.wordsearchgame.Service.GridService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class GridController {

    Logger logger= LoggerFactory.getLogger(GridController.class);

    @Autowired
    GridService gridService;

    @GetMapping("/grid")
    public ResponseEntity<String> generateGrid(@RequestParam int gridSize, @RequestParam String wordList){

        logger.info("Grid api called");
        List<String> words = Arrays.asList(wordList.split(","));

        for(String word:words){
            if(word.length()>gridSize) {
                logger.info("One or more words exceed the length of the grid size");
                throw new CustomException("One or more words exceed the length of the grid size");
            }
        }

        logger.info("Generating the grid by calling the createGrid method in GridService class");
        char[][] grid = gridService.createGrid(words,gridSize);

        //Converting the character array to a string
        StringBuilder gridToString = new StringBuilder("");

        for (int i = 0; i < gridSize; i++) {

            for (int j = 0; j < gridSize-1; j++) {

                gridToString.append(grid[i][j]) ;

                gridToString.append(" ");

            }

            gridToString.append(grid[i][gridSize-1]);

            if(i!=gridSize-1)

                gridToString.append("\r\n");
        }

        logger.info("Returning the word grid as a String");
        return new ResponseEntity<>(gridToString.toString(), HttpStatus.OK);

    }

}
