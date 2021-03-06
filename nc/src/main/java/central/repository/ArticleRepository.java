package central.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import central.model.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

}
