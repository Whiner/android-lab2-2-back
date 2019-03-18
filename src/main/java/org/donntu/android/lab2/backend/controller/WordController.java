package org.donntu.android.lab2.backend.controller;

import lombok.RequiredArgsConstructor;
import org.donntu.android.lab2.backend.dto.FullWordInfo;
import org.donntu.android.lab2.backend.dto.NextWordResponse;
import org.donntu.android.lab2.backend.dto.Word;
import org.donntu.android.lab2.backend.service.WordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public void addWords(@RequestBody List<Word> words) {
        service.addWords(words);
    }

    @GetMapping("/av-words-count")
    public Integer getAvailableWordsCount() {
        return service.getAvailableWordsCount();
    }

    @GetMapping("/all")
    public List<FullWordInfo> getAllWords() {
        return service.getAllWords();
    }

    @PostMapping("/refresh")
    public void refreshArchive() {
        service.refreshArchive();
    }
}
