package application.data.service;

import application.data.model.Blog;
import application.data.repository.BlogRepository;
import application.model.dto.BlogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;
    public boolean createNewBlog(Blog blog){
        try{
            blogRepository.save(blog);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
