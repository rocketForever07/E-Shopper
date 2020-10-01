package application.data.service;

import application.data.model.Item;
import application.data.model.Product;
import application.data.repository.ItemRepository;
import application.data.repository.ProductRepository;
import org.hibernate.boot.jaxb.hbm.internal.ImplicitResultSetMappingDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public void addNewItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public void addNewListItems(List<Item> listItems) {
        itemRepository.save(listItems);
    }

    public Item findOne(int itemId) {
        return itemRepository.findOne(itemId);
    }


    public boolean updatePItem(Item item) {
        try {
            itemRepository.save(item);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deletItem(int itemId) {
        try {
            itemRepository.delete(itemId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Item> getListAllItems() {
        try {
            return itemRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public long getTotalItems(){
        return itemRepository.getTotalItems();
    }

    public Page<Item> getPage(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }

//    public Page<Item> getListProductByCategoryOrProductNameContaining(Pageable pageable, Integer categoryId, String productName){
//        return itemRepository.getListItemByCategoryOrProductNameContaining(pageable,categoryId,productName);
//    }

//    public List<Product> getListProductMostPopular(){
//        return productRepository.getListProductMostPopular();
//    }

//    public List<Product> getListNewProduct(){
//        return productRepository.getListNewProduct();
//    }


}
