package ru.hogwarts.school.validation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import ru.hogwarts.school.exception.RepositoryContainsDuplicateObjectException;
import ru.hogwarts.school.exception.RepositoryNotContainsObjectWithIdException;

import java.util.function.BooleanSupplier;

@Component
public class RepositoryValidator {

    public void checkRepositoryContainsRequestedKey(JpaRepository<?, Long> repository, long id) {
        if (!repository.existsById(id)) {
            throw new RepositoryNotContainsObjectWithIdException(id);
        }
    }

    public <T, O> void checkRepositoryContainsDuplicateObject(
            JpaRepository<T, Long> repository,
            O object,
            BooleanSupplier duplicateChecker) {
        if (duplicateChecker.getAsBoolean()) {
            throw new RepositoryContainsDuplicateObjectException(object);
        }
    }
}
