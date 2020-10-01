package application.data.repository;

import application.data.model.Item;
import application.data.model.Product;
import application.model.viewmodel.common.ChartDataVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {
    @Query("select count(i.id) from dbo_item i")
    long getTotalItems();

    @Query("SELECT p FROM dbo_product p " +
            "WHERE (:categoryId IS NULL OR (p.categoryId = :categoryId))" +
            "AND (:productName IS NULL OR UPPER(p.name) LIKE CONCAT('%',UPPER(:productName),'%'))")
    Page<Item> getListItemByCategoryOrProductNameContaining(Pageable pageable,
                                                                  @Param("categoryId") Integer categoryId,
                                                                  @Param("productName") String productName);



    @Query("SELECT p FROM dbo_product p "+
            "INNER JOIN p.orderProductList op "+
            "GROUP BY p.id "+
            "ORDER BY COUNT(p.id) desc "
    )
    List<Product>getListProductMostPopular();

//    @Query("SELECT new application.model.dto.ProductDTO(p.name, p.shortDesc, c.name) " +
//            "FROM dbo_product p " +
//            "INNER JOIN p.category c " +
//            "WHERE UPPER(p.name) LIKE CONCAT('%',UPPER(:name),'%') ")
//    List<ProductDTO> getListByNameContaining(@Param("name")String name);

    @Query("SELECT new application.model.viewmodel.common.ChartDataVM(p.name,SUM(op.amount)) "+
            "FROM dbo_product p "+
            "INNER JOIN p.orderProductList op "+
            "INNER JOIN op.order o "+
            "WHERE MONTH(o.createdDate) = :month "+
            "AND YEAR(o.createdDate) = :year "+
            "GROUP BY p.id "+
            "ORDER BY SUM(op.amount) DESC ")
    List<ChartDataVM> getListFiveHotProducts(Pageable pageable,
                                             @Param("month") Integer month,
                                             @Param("year") Integer year);

    @Query("SELECT new application.model.viewmodel.common.ChartDataVM(p.name,SUM(op.amount)) "+
            "FROM dbo_product p "+
            "INNER JOIN p.orderProductList op "+
            "GROUP BY p.id "+
            "HAVING SUM(op.amount)>0 "+
            "ORDER BY SUM(op.amount) ASC,p.createdDate ASC")
    List<ChartDataVM> getListFiveBadProducts(Pageable pageable);
}
