package com.crud.tasks.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TrelloValidatorTestSuite {

    @Autowired
    private TrelloValidator trelloValidator;

    @Test
    public void testValidatorTrelloBoards() {
        //Given
        TrelloList trelloList = new TrelloList("1", "trello_list_name", false);
        List<TrelloList> trelloLists = List.of(trelloList);

        TrelloBoard trelloBoard = new TrelloBoard("1", "trello_board_test", trelloLists);
        List<TrelloBoard> trelloBoards = List.of(trelloBoard);
        //When
        List<TrelloBoard> validTrelloBoard = trelloValidator.validateTrelloBoards(trelloBoards);
        //Then
        assertEquals(1, validTrelloBoard.size());
    }

    @Test
    public void testNotValidatorTrelloBoards() {
        //Given
        List<TrelloList> trelloLists = List.of(new TrelloList("1", "test_list", false));
        List<TrelloBoard> trelloBoards = List.of(new TrelloBoard("1", "test", trelloLists));
        //When
        List<TrelloBoard> validBoards = trelloValidator.validateTrelloBoards(trelloBoards);
        //Then
        assertEquals(0, validBoards.size());
    }

}