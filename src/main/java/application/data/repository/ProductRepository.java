package application.data.repository;

import application.data.model.Product;
import application.model.dto.ProductDTO;
import application.model.viewmodel.common.ChartDataVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query("select count(p.id) from dbo_product p")
    long getTotalProducts();

    @Query("SELECT p FROM dbo_product p " +
            "WHERE (:categoryId IS NULL OR (p.categoryId = :categoryId))" +
            "AND (:productName IS NULL OR UPPER(p.name) LIKE CONCAT('%',UPPER(:productName),'%'))")
    Page<Product> getListProductByCategoryOrProductNameContaining(Pageable pageable,
                                                                  @Param("categoryId") Integer categoryId,
                                                                  @Param("productName") String productName);

//    @Query("SELECT p FROM dbo_product p "+
//            "ORDER BY p.createdDate DESC "+"")
//    List<Product>getListNewProduct();

    @Query(
            value = "SELECT * FROM dbo_product " +
                    "ORDER BY created_date DESC " +
                    "limit 3",
            nativeQuery = true)
    List<Product>getListNewProduct();

//    @Query("SELECT p FROM dbo_product p "+
//            "INNER JOIN p.orderProductList op "+
//            "GROUP BY p.id "+
//            "ORDER BY COUNT(p.id) desc "
//            )
//    List<Product>getListProductMostPopular();

    @Query(
            value = "SELECT * FROM dbo_product p " +
                    "JOIN dbo_order_product op ON p.product_id =  op.product_id " +
                    "GROUP BY p.product_id "+
                    "ORDER BY COUNT(p.product_id) desc "+
                    "limit 3",
            nativeQuery = true)
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
