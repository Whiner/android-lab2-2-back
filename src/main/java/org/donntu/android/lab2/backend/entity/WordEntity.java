package org.donntu.android.lab2.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "words")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "russian")
    private String russianTranslate;

    @Column(name = "english")
    private String englishTranslate;

    @Column(name = "is_in_archive")
    private boolean inArchive;

    @Column(name = "right_answer_count")
    private int rightAnswerCount;

    public void incRightAnswerCount() {
        this.rightAnswerCount++;
    }

    public WordEntity(String russianTranslate, String englishTranslate) {
        this.russianTranslate = russianTranslate;
        this.englishTranslate = englishTranslate;
    }
}
