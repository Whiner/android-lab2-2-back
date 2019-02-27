package org.donntu.android.lab2.backend.repository;

import org.donntu.android.lab2.backend.entity.WordEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface WordRepository extends CrudRepository<WordEntity, Long> {
    List<WordEntity> findAllByInArchiveIs(boolean isInArchive);

    Optional<WordEntity> findByRussianTranslateOrEnglishTranslate(
            String russianTranslate,
            String englishTranslate
    );
}
