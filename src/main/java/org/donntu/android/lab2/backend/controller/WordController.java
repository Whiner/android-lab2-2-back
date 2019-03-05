package org.donntu.android.lab2.backend.controller;

import lombok.RequiredArgsConstructor;
import org.donntu.android.lab2.backend.dto.NextWordResponse;
import org.donntu.android.lab2.backend.dto.Word;
import org.donntu.android.lab2.backend.service.WordService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class WordController {
    private final WordService service;

    @GetMapping("/next-word")
    public NextWordResponse nextWord(int answerVersionsCount) throws Exception {
        return service.nextWord(answerVersionsCount);
    }

    @PostMapping("/inc-right-answer")
    public void incRightAnswer(Long wordId) {
        service.incRightAnswer(wordId);
    }

    @PutMapping("/add")
    public void addWord(Word word) throws Exception {
        service.addWord(word);
    }


}
