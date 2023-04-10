package com.crud.tasks.controller;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/trello")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TrelloController {

    private final TrelloClient trelloClient;

    @GetMapping("boards")
    public void getTrelloBoards() {

        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();

        List<TrelloBoardDto> trelloBoardsFiltered = trelloBoards.stream()
                .filter(trelloBoard ->
                        trelloBoard.getName()!=null &&
                        trelloBoard.getId()!=null &&
                        trelloBoard.getName().contains("Kodilla"))
                .collect(Collectors.toList());

        trelloBoardsFiltered.forEach(trelloBoard -> {
            System.out.println(trelloBoard.getId() + " " + trelloBoard.getName());
        });

//        trelloBoards.forEach(trelloBoardDto -> {
//            System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName());
//        });
    }
}
