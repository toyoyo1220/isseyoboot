package project.isseyo.stock.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("stockDto")
public class StockDto {

    /**
     * 품목정보 SEQ
     */
    private int pkProductSeq;

    /**
     * 품목명
     */
    private String productName;

    /**
     * 품목코드
     */
    private String productCode;

    /**
     * 규격
     */
    private String standard;

    /**
     * 단위
     */
    private String unit;

    /**
     * 이미지
     */
    private String productImg;

    /**
     * 품목구분
     */
    private String divn;
    /**
     * 삭제 여부
     */
    private boolean delYn;

    private int stock;
    private String etc;
    private String userId;


    /**
     * 등록일자
     */
    private String registDt;

}
