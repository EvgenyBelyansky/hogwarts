package ru.hogwarts.school;

import com.github.javafaker.Faker;
import lombok.Builder;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.entity.Student;

public interface TestHelper<T> {

    Faker faker = new Faker();

    T create();

    default T save(T t){
       return getRepository().save(t);
    }

    default T add() {
        return save(create());
    }

    JpaRepository<T, ?> getRepository();
}
