package org.donntu.android.lab2.backend.service;

import org.donntu.android.lab2.backend.dto.NextWordResponse;
import org.donntu.android.lab2.backend.dto.Word;
import org.donntu.android.lab2.backend.entity.WordEntity;
import org.donntu.android.lab2.backend.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import static org.donntu.android.lab2.backend.utils.StringUtils.stringToDefaultFormat;

@Service
public class WordService {
    private final WordRepository repository;
    private final int maxRightAnswersCount;
    private Random random = new Random();

    public WordService(
            @Autowired WordRepository repository,
            @Value("${app.word.max.right.answer.count}") int maxRightAnswersCount
    ) {
        this.repository = repository;
        this.maxRightAnswersCount = maxRightAnswersCount;
    }

    public NextWordResponse nextWord(int answerVersionsCount) throws Exception {
        List<WordEntity> words = repository.findAllByInArchiveIs(false);
        if (words.size() < answerVersionsCount) {
            throw new Exception("Доступных слов слишком мало, либо они в архиве. Очистите архив или добавьте еще слов.");
        }

        NextWordResponse nextWordResponse = new NextWordResponse();
        nextWordResponse.setRightAnswerIndex(random.nextInt(maxRightAnswersCount));
        List<Integer> wordsIndexes = new ArrayList<>();
        for (int i = 0; i < answerVersionsCount; i++) {
            Integer index;
            do {
                index = random.nextInt(words.size());
            } while (wordsIndexes.contains(index));
            wordsIndexes.add(index);
        }

        List<Word> responseWords =
                wordsIndexes
                        .stream()
                        .map(index -> Word.of(words.get(index)))
                        .collect(Collectors.toList());

        nextWordResponse.setAnswerVersions(responseWords);
        return nextWordResponse;
    }

    public void incRightAnswer(long wordId) {
        Optional<WordEntity> byId = repository.findById(wordId);
        byId.ifPresent(word -> {
            word.incRightAnswerCount();
            if (word.getRightAnswerCount() >= maxRightAnswersCount) {
                word.setInArchive(true);
            }
            repository.save(word);
        });
    }

    public void addWord(Word word) throws Exception {
        String russianTranslate = stringToDefaultFormat(word.getRussian());
        String englishTranslate = stringToDefaultFormat(word.getEnglish());
        Optional<WordEntity> wordEntity = repository
                .findByRussianTranslateOrEnglishTranslate(
                        russianTranslate,
                        englishTranslate
                );
        if (wordEntity.isPresent()) {
            throw new Exception("Такое слово уже существует");
        } else {
            repository.save(new WordEntity(russianTranslate, englishTranslate));
        }
    }


}
