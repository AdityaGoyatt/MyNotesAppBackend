package com.mynotesapp.server.mynotesappserver.Repositories;

import com.mynotesapp.server.mynotesappserver.Entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Integer> {

}
