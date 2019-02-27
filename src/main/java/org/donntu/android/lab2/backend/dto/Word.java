package org.donntu.android.lab2.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.donntu.android.lab2.backend.entity.WordEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Word {
    private Long id;
    private String russian;
    private String english;

    public static Word of(WordEntity entity) {
        return new Word(entity.getId(), entity.getRussianTranslate(), entity.getEnglishTranslate());
    }
}
