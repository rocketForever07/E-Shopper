package application.data.service;

import application.data.model.Category;
import application.data.repository.CategoryRepository;
import application.model.viewmodel.common.CategoryVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public void addNewListCategories(List<Category> categories){
        categoryRepository.save(categories);
    }

    public void addNewCategory(Category category){
        categoryRepository.save(category);
    }

    public Category findOne(int categoryId){
        return categoryRepository.findOne(categoryId);
    }

    public boolean updateCategory(Category category){
        try{
            categoryRepository.save(category);
            return true;
        }catch (Exception e){

        }
        return false;
    }

    public List<Category> getListAllCategories(){
        try{
            return categoryRepository.findAll();
        }catch (Exception e){
            return new ArrayList<>();
        }
    }

    public List<CategoryVM> getListCategories(){
        try{
            return categoryRepository.getListCategories();
        }catch (Exception e){
            return new ArrayList<>();
        }
    }

    public boolean delete(int id){
        try {
            categoryRepository.delete(id);
            return  true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public long getTotalCategories(){
        return categoryRepository.getTotalCategories();
    }

    public Page<Category> getListCategoryByCategoryNameContaining(Pageable pageable, String categoryName){
        return categoryRepository.getListCategoryByCategoryNameContaining(pageable,categoryName);
    }

}
