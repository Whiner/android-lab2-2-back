package org.donntu.android.lab2.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.donntu.android.lab2.backend.entity.WordEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FullWordInfo {
    private Long id;
    private String russianTranslate;
    private String englishTranslate;
    private boolean isInArchive;
    private int correctAnswersCount = 0;

    public static FullWordInfo of(WordEntity entity) {
        return new FullWordInfo(
                entity.getId(),
                entity.getRussianTranslate(),
                entity.getEnglishTranslate(),
                entity.isInArchive(),
                entity.getRightAnswerCount()
        );
    }
}
