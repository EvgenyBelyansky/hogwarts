package ru.hogwarts.school.model.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.hogwarts.school.validation.InputValidator;

import java.util.Objects;

@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Builder
    public Student(String name, int age) {
        this.name = Objects.requireNonNull(name);
        this.age = Objects.requireNonNull(age);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Student student = (Student) object;
        return age == student.age && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    public void setId(long id) {
        this.id = Objects.requireNonNull(id);
    }

    public void setName(String name) {
        InputValidator.checkObjectStringFieldIsBlank("name", name);
        this.name = name;
    }

    public void setAge(int age) {
        InputValidator.checkObjectNumericFieldIsPositive("age", age);
        this.age = age;
    }

    @Override
    public Student clone() {
        Student copy = new Student();
        copy.setId(this.id);
        copy.setName(this.name);
        copy.setAge(this.age);
        return copy;
    }
}