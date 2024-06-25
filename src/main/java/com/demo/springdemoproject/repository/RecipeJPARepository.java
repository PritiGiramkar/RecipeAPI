package com.demo.springdemoproject.repository;

import java.util.List;
import java.util.Optional;

import com.demo.springdemoproject.model.Recipe;
import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.demo.springdemoproject.model.Recipe;

@Component //it configures the below class as bean
@Repository // it configures that below class has connection to database
// Jparespository is interface which consists of by default crud operations
public interface RecipeJPARepository extends JpaRepository<Recipe, Long>{


//	@Query("SELECT r.title, r.image, r.servings, r.steps FROM Recipe r WHERE r.title LIKE '%?1%'")
//	List<Recipe> findByTitle(String name);

	@Query("SELECT m FROM Recipe m WHERE m.title LIKE %:name%")
	List<Recipe> findByTitle(@Param("name") String name);

	Optional<Recipe> findById(Long id);

	//Users findById(String id);
}
