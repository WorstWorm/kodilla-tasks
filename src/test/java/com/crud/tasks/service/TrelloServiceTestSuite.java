package com.crud.tasks.service;


import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TrelloServiceTestSuite {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Mock
    private AdminConfig adminConfig;

    @Test
    public void testFetchTrelloBoards() {
        //Given
        List<TrelloListDto> trelloListDto = List.of(new TrelloListDto("1", "test_name", false));
        List<TrelloBoardDto> trelloBoardDtoList = List.of(new TrelloBoardDto("test_id", "test_name", trelloListDto));

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtoList);
        //When
        List<TrelloBoardDto> resultTrelloBoardList = trelloService.fetchTrelloBoards();
        //Then
        assertEquals(1, resultTrelloBoardList.size());
    }

    @Test
    public void testCreateNullTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test_cart_name", "test_card_description", "top", "test_idList");
        CreatedTrelloCardDto createdTrelloCardDto = null;

        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        //When
        CreatedTrelloCardDto resultCreatedTrelloCardDto = trelloService.createTrelloCardDto(trelloCardDto);
        //Then
        assertNull(resultCreatedTrelloCardDto);
    }

    @Test
    public void testCreateTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name_test", "description", "tom", "1");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("2", "name", "shortUrl");
        when(adminConfig.getAdminMail()).thenReturn("test@test.com");
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        //When
        CreatedTrelloCardDto resultTrelloCardDto = trelloService.createTrelloCardDto(trelloCardDto);
        //Then
        assertEquals("2", resultTrelloCardDto.getId());
        assertEquals("name", resultTrelloCardDto.getName());
        assertEquals("shortUrl", resultTrelloCardDto.getShortUrl());

        verify(emailService, times(1)).send(any());
    }
}