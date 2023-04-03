package exercise.controller;

import exercise.model.Course;
import exercise.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping(path = "")
    public Iterable<Course> getCourses() {
        return courseRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Course getCourse(@PathVariable long id) {
        return courseRepository.findById(id);
    }

    // BEGIN
    @GetMapping(path = "/{id}/previous")
    public Iterable<Course> getPreviousCourses(@PathVariable long id) {
        Course currentCourse = courseRepository.findById(id);
        List<Course> parentCourses = new ArrayList<>();
        String parentsPath = currentCourse.getPath();
        if (parentsPath != null) {
            List<Long> parentIdList = Arrays.stream(parentsPath.split("\\."))
                .map(Long::parseLong)
                .collect(Collectors.toList());
            parentIdList.stream()
                .map(parentId -> courseRepository.findById(parentId))
                .forEach(course -> parentCourses.add(course.orElse(null)));
            parentCourses.sort(Comparator.comparing(Course::getId));
        }
        return parentCourses;
    }
    // END

}
