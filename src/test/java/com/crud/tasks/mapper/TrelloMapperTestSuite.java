package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrelloMapperTestSuite {

    private TrelloMapper trelloMapper = new TrelloMapper();

    @Test
    public void mapToBoardsTest() {
        //Given
            List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
            trelloBoardDtoList.add(new TrelloBoardDto("test_id1", "test_board_1", new ArrayList<>()));
            trelloBoardDtoList.add(new TrelloBoardDto("test_id2", "test_board_2", new ArrayList<>()));
            trelloBoardDtoList.add(new TrelloBoardDto("test_id3", "test_board_3", new ArrayList<>()));
        //When
            List<TrelloBoard> resultTrelloBoard = trelloMapper.mapToBoards(trelloBoardDtoList);
        //Then
            assertEquals(trelloBoardDtoList.size(), resultTrelloBoard.size());
            assertEquals(trelloBoardDtoList.get(0).getId(), resultTrelloBoard.get(0).getId());
            assertEquals(trelloBoardDtoList.get(1).getName(), resultTrelloBoard.get(1).getName());
            assertEquals(trelloBoardDtoList.get(2).getLists().size(), resultTrelloBoard.get(2).getLists().size());
    }

    @Test
    public void mapToBoardsDtoTest() {
        //Given
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(new TrelloBoard("test_id1", "test_board1", new ArrayList<>()));
        trelloBoardList.add(new TrelloBoard("test_id2", "test_board2", new ArrayList<>()));
        trelloBoardList.add(new TrelloBoard("test_id3", "test_board3", new ArrayList<>()));
        //When
        List<TrelloBoardDto> resultTrelloBoardDto = trelloMapper.mapToBoardsDto(trelloBoardList);
        //Then
        assertEquals(trelloBoardList.size(), resultTrelloBoardDto.size());
        assertEquals(trelloBoardList.get(0).getId(), resultTrelloBoardDto.get(0).getId());
        assertEquals(trelloBoardList.get(1).getName(), resultTrelloBoardDto.get(1).getName());
        assertEquals(trelloBoardList.get(2).getLists().size(), resultTrelloBoardDto.get(2).getLists().size());
    }

    @Test
    public void mapToListTest() {
        //Given
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(new TrelloListDto("test_id1", "test_list1", false));
        trelloListsDto.add(new TrelloListDto("test_id2", "test_list2", true));
        trelloListsDto.add(new TrelloListDto("test_id3", "test_list3", false));
        //When
        List<TrelloList> resultTrelloList = trelloMapper.mapToList(trelloListsDto);
        //Then
        assertEquals(trelloListsDto.size(), resultTrelloList.size());
        assertEquals(trelloListsDto.get(0).getId(), resultTrelloList.get(0).getId());
        assertEquals(trelloListsDto.get(1).getName(), resultTrelloList.get(1).getName());
        assertEquals(trelloListsDto.get(1).isClosed(), resultTrelloList.get(1).isClosed());
        assertEquals(trelloListsDto.get(2).isClosed(), resultTrelloList.get(2).isClosed());
    }

    @Test
    public void mapToListDtoTest() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("test_id1", "test_list1", false));
        trelloLists.add(new TrelloList("test_id2", "test_list2", true));
        trelloLists.add(new TrelloList("test_id3", "test_list3", false));
        //When
        List<TrelloListDto> resultTrelloListDto = trelloMapper.mapToListDto(trelloLists);
        //Then
        assertEquals(trelloLists.size(), resultTrelloListDto.size());
        assertEquals(trelloLists.get(0).getId(), resultTrelloListDto.get(0).getId());
        assertEquals(trelloLists.get(1).getName(), resultTrelloListDto.get(1).getName());
        assertEquals(trelloLists.get(1).isClosed(), resultTrelloListDto.get(1).isClosed());
        assertEquals(trelloLists.get(2).isClosed(), resultTrelloListDto.get(2).isClosed());
    }

    @Test
    public void mapToCardTest() {
        //Given
        TrelloCard trelloCard = new TrelloCard("test_card1", "test_description1", "pos_test", "list1");
        //When
        TrelloCardDto resultTrelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals(trelloCard.getName(), resultTrelloCardDto.getName());
        assertEquals(trelloCard.getDescription(), resultTrelloCardDto.getDescription());
        assertEquals(trelloCard.getPos(), resultTrelloCardDto.getPos());
        assertEquals(trelloCard.getListId(), resultTrelloCardDto.getListId());
    }

    @Test
    public void mapToCardDtoTest() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test_card1", "test_description1", "pos_test", "list1");
        //When
        TrelloCard resultTrelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals(trelloCardDto.getName(), resultTrelloCard.getName());
        assertEquals(trelloCardDto.getDescription(), resultTrelloCard.getDescription());
        assertEquals(trelloCardDto.getPos(), resultTrelloCard.getPos());
        assertEquals(trelloCardDto.getListId(), resultTrelloCard.getListId());
    }
}
