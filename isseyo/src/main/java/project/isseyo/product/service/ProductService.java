package project.isseyo.product.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.isseyo.product.dto.ProductDto;
import project.isseyo.product.mapper.ProductMapper;

@Service
public class ProductService {
	
	@Autowired
	private ProductMapper productMapper;
	
	/**
	 * @return 
	 * 품목 정보를 삽입한다.
	 * @param Map<String, Object> map
	 * @return
	 * @exception
	 */
	public int insertProduct(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return productMapper.insertProduct(map);
	}
	/**
	 * @return 
	 * @return 
	 * 품목 정보를 삽입한다.
	 * @param productVO
	 * @return
	 * @exception
	 */
	public Object productCreate(ProductDto productDto) {
		// TODO Auto-generated method stub
		return productMapper.productCreate(productDto);
	}
	
	/**
	 * 품목 정보를 조회한다.
	 * @param searchVO
	 * @return List<?>
	 * @exception
	 */
	public List<ProductDto> selectProductList(ProductDto productDto) {
		return productMapper.selectProductList(productDto);
	}
	
	/**
	 * 품목 정보를 총 갯수를 조회한다.
	 * @param searchVO
	 * @return int
	 * @exception
	 */
	public int selectProductListTotCnt() {
		return productMapper.selectProductListTotCnt();
	}

	/**
	 * 품목 정보를 조회 한다.
	 * @param bizApiKey, productId
	 * @return ProductVO
	 * @exception
	 */
	public ProductDto selectProduct(ProductDto productDto) {
		return productMapper.selectProduct(productDto);
	}
	
	/**
	 * @return 
	 * 품목 상세 정보를 삽입한다.
	 * @param hashMap
	 * @return
	 * @exception
	 */
	public void insertProductDetail(HashMap<String, Object> hashMap) {
		// TODO Auto-generated method stub
		productMapper.insertProductDetail(hashMap);
		return;
	}
	
	/**
	 * 품목 정보를 조회 한다.
	 * @param productVO
	 * @return ProductVO
	 * @exception
	 */
	public ProductDto productSelect(ProductDto productDto) {
		return productMapper.productSelect(productDto);
	}
	
	/**
	 * 품목 상세 정보를 조회 한다.
	 * @param productVO
	 * @return ProductVO
	 * @exception
	 */
	public List<ProductDto> selectProductDetailList(ProductDto productDto) {
		return productMapper.selectProductDetailList(productDto);
	}
	/**
	 * @return 
	 * 품목 상세 정보를 갱신한다.
	 * @param hashMap
	 * @return
	 * @exception
	 */
	public void updateProduct(HashMap<String, Object> productMap) {
		productMapper.updateProduct(productMap);
		return;
		
	}
	/**
	 * @return 
	 * 품목 상세 정보를 삭제한다.
	 * @param int pkProductSeq
	 * @return
	 * @exception
	 */
	public void deleteProductDetail(int pkProductSeq) {
		// TODO Auto-generated method stub
		productMapper.deleteProductDetail(pkProductSeq);
		return;
	}
	/**
	 * @return 
	 * 품목 정보를 삭제한다.
	 * @param int pkProductSeq
	 * @return
	 * @exception
	 */
	public void deleteProduct(int pkProductSeq) {
		// TODO Auto-generated method stub
		productMapper.deleteProduct(pkProductSeq);
		return;
	}
	public int seletProductCount(ProductDto productDto) {
		// TODO Auto-generated method stub
		return productMapper.seletProductCount(productDto);
	}
}
