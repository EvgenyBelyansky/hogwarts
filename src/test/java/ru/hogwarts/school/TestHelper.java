package ru.hogwarts.school;

import com.github.javafaker.Faker;

import java.util.function.Function;

public abstract class TestHelper<T> {

    protected final Function<T, T> saveFunction;

    static Faker faker = new Faker();

    public TestHelper(Function<T, T> saveFunction) {
        this.saveFunction = saveFunction;
    }

    public T create() {
        return null;
    }

    public T save(T t){
       return this.saveFunction.apply(t);
    }

    public T add() {
        return save(create());
    }
}
