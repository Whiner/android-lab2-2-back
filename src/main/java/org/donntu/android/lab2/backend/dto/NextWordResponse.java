package org.donntu.android.lab2.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NextWordResponse {
    private int rightAnswerIndex;
    private List<Word> answerVersions = new ArrayList<>();
}
