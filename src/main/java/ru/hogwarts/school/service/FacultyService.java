package ru.hogwarts.school.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

    private InputValidator inputValidator;
    private RepositoryValidator repositoryValidator;

    private long count = 1;

    public void addFaculty(FacultyDto facultyDto) {
        inputValidator.checkArgumentIsNull(facultyDto);
        inputValidator.checkObjectStringFieldIsBlank("name", facultyDto.getName(), facultyDto);
        inputValidator.checkObjectStringFieldIsBlank("color", facultyDto.getColor(), facultyDto);
        repositoryValidator.checkRepositoryContainsDuplicateObject(
                facultyRepository,
                facultyDto,
                () -> facultyRepository.existsByNameAndColor(facultyDto.getName(), facultyDto.getColor())
        );

        Faculty newFaculty = Faculty.builder()
                .name(facultyDto.getName())
                .color(facultyDto.getColor())
                .build();

        facultyRepository.save(newFaculty);
    }

    public Faculty removeFacultyById(long id) {

        final Faculty removedFaculty = findFacultyById(id);

        facultyRepository.deleteById(id);
        return removedFaculty;
    }

    public Faculty findFacultyById(long id) {
        repositoryValidator.checkRepositoryContainsRequestedKey(facultyRepository, id);

        final Faculty faculty = facultyRepository.findById(id).get();
        return faculty;
    }

    public Faculty updateFaculty(Faculty faculty) {
        inputValidator.checkArgumentIsNull(faculty);
        repositoryValidator.checkRepositoryContainsRequestedKey(facultyRepository, faculty.getId());
        inputValidator.checkObjectStringFieldIsBlank("name", faculty.getName(), faculty);
        inputValidator.checkObjectStringFieldIsBlank("color", faculty.getColor(), faculty);

        final Faculty updatedFaculty = facultyRepository.save(faculty);
        faculty.setColor(faculty.getColor());
        faculty.setName(faculty.getName());
        return updatedFaculty;
    }

    public Map<Long, Faculty> getFiltredByColorFacultyMap(String color) {
        inputValidator.checkObjectStringFieldIsBlank("color", color);

        final Map<Long, Faculty> facultyMap = facultyRepository.findAll().stream()
                .filter(f -> f.getColor().equals(color))
                .collect(Collectors.toUnmodifiableMap(Faculty::getId, f -> f));
        return facultyMap;
    }

    public Map<Long, Faculty> getAllFaculty() {

        final Map<Long, Faculty> facultyMap = facultyRepository.findAll().stream()
                .collect(Collectors.toUnmodifiableMap(Faculty::getId, f -> f));
        return facultyMap;
    }
}