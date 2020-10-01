package application.data.repository;

import application.data.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BlogRepository  extends JpaRepository<Blog,Integer> {

}
