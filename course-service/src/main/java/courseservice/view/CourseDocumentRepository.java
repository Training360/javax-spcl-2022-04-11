package courseservice.view;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface CourseDocumentRepository extends ElasticsearchRepository<CourseDocument, Long> {

    @Query("""
               {
                  "multi_match" : {"query": "?0", "fields": ["name", "description", "syllabus"]}
                }
                        
            """)
    List<CourseDocument> findByWordUsingCustomQuery(String word);
}
