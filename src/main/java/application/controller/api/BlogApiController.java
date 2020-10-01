package application.controller.api;

import application.data.model.Blog;
import application.data.repository.BlogRepository;
import application.data.service.BlogService;
import application.model.api.DataApiResult;
import application.model.dto.BlogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(path = "/api/blog")
public class BlogApiController {
    @Autowired
    private BlogService blogService;

    @PostMapping("/create")
    public DataApiResult createNewBlog(@RequestBody BlogDTO blogDTO){

        DataApiResult result=new DataApiResult();

        try{
            Blog blog=new Blog();
            blog.setTitle(blogDTO.getTitle());
            blog.setContent(blogDTO.getContent());
            blog.setCreatedDate(new Date());
            blog.setShortDesc((blogDTO.getShortDesc()));

            blogService.createNewBlog(blog);

            result.setData(blog.getId());
            result.setSuccess(true);
            result.setMessage("create blog successfully");

        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("some error when creating blog");
        }

        return result;

    }
}
