package project.isseyo.product.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.isseyo.product.dto.ProductDto;

@Mapper
public interface ProductMapper {

	int insertProduct(HashMap<String, Object> map);

	Object productCreate(ProductDto productDto);

	List<ProductDto> selectProductList();

	int selectProductListTotCnt();

	ProductDto selectProduct(ProductDto productDto);

	void insertProductDetail(HashMap<String, Object> hashMap);

	ProductDto productSelect(ProductDto productDto);

	List<ProductDto> selectProductDetailList(ProductDto productDto);

}
