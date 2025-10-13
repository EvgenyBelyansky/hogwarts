package ru.hogwarts.school.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.RepositoryNotContainsObjectWithIdException;
import ru.hogwarts.school.model.mappers.FacultyMapper;
import ru.hogwarts.school.model.dto.FacultyDto;
import ru.hogwarts.school.model.entity.Faculty;
import ru.hogwarts.school.model.repository.FacultyRepository;
import ru.hogwarts.school.validation.InputValidator;
import ru.hogwarts.school.validation.RepositoryValidator;

import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private RepositoryValidator repositoryValidator;


    public void addFaculty(FacultyDto facultyDto) {
        InputValidator.checkArgumentIsNull(facultyDto);
        InputValidator.checkObjectStringFieldIsBlank("name", facultyDto.getName(), facultyDto);
        InputValidator.checkObjectStringFieldIsBlank("color", facultyDto.getColor(), facultyDto);
        repositoryValidator.checkRepositoryContainsDuplicateObject(
                facultyRepository,
                Faculty.class,
                facultyDto,
                () -> facultyRepository.existsByNameAndColor(facultyDto.getName(), facultyDto.getColor())
        );

        Faculty newFaculty = FacultyMapper.fromDtoToFacultyEntity(facultyDto);

        facultyRepository.save(newFaculty);
    }

    public void removeFacultyById(long id) {
        facultyRepository.deleteById(id);
    }

    public Faculty findFacultyById(long id) {
        return facultyRepository
                .findById(id)
                .orElseThrow(() -> new RepositoryNotContainsObjectWithIdException(id));
    }

    public void updateFaculty(long id, FacultyDto facultyDto) {
        InputValidator.checkArgumentIsNull(facultyDto);
        repositoryValidator.checkRepositoryContainsRequestedKey(facultyRepository, id);
        InputValidator.checkObjectStringFieldIsBlank("name", facultyDto.getName(), facultyDto);
        InputValidator.checkObjectStringFieldIsBlank("color", facultyDto.getColor(), facultyDto);

        final Faculty updatedFaculty = FacultyMapper.fromDtoToFacultyEntity(facultyDto);
        facultyRepository.save(updatedFaculty);
    }

    public Map<Long, Faculty> getFiltredByColorFacultyMap(String color) {
        InputValidator.checkObjectStringFieldIsBlank("color", color);

        return facultyRepository.findAll().stream()
                .filter(f -> f.getColor().equals(color))
                .collect(Collectors.toUnmodifiableMap(Faculty::getId, f -> f));
    }

    public Map<Long, Faculty> getAllFaculty() {

        return facultyRepository.findAll().stream()
                .collect(Collectors.toUnmodifiableMap(Faculty::getId, f -> f));
    }
}