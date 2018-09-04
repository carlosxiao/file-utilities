# Excel 操作

## Add dependency

``` xml

<dependency>
      <groupId>com.cc.pub</groupId>
      <artifactId>ts-excel</artifactId>
      <version>0.0.1-SNAPSHOT</version>
</dependency>

```

## ShopDTO

``` java
package com.toolset.file.excel.test;

import com.toolset.file.excel.annotation.ExcelField;
import com.toolset.file.excel.annotation.ExcelSheet;
import org.apache.poi.hssf.util.HSSFColor;

import java.util.Date;

/**
 * Java Object To Excel
 *
 */
@ExcelSheet(name = "商户列表", headColor = HSSFColor.HSSFColorPredefined.LIGHT_GREEN)
public class ShopDTO {

    @ExcelField(name = "是否VIP商户")
    private boolean vip;

    @ExcelField(name = "商户名称")
    private String shopName;

    @ExcelField(name = "分店数量")
    private short branchNum;

    @ExcelField(name = "商户ID")
    private int shopId;

    @ExcelField(name = "浏览人数")
    private long visitNum;

    @ExcelField(name = "当月营业额")
    private float turnover;

    @ExcelField(name = "历史营业额")
    private double totalTurnover;

    @ExcelField(name = "开店时间", dateformat = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;


    public ShopDTO() {
    }

    public ShopDTO(boolean vip, String shopName, short branchNum, int shopId, long visitNum, float turnover, double totalTurnover, Date addTime) {
        this.vip = vip;
        this.shopName = shopName;
        this.branchNum = branchNum;
        this.shopId = shopId;
        this.visitNum = visitNum;
        this.turnover = turnover;
        this.totalTurnover = totalTurnover;
        this.addTime = addTime;
    }

    // ignore get set method....

    @Override
    public String toString() {
        return "ShopDTO{" +
                "vip=" + vip +
                ", shopName='" + shopName + '\'' +
                ", branchNum=" + branchNum +
                ", shopId=" + shopId +
                ", visitNum=" + visitNum +
                ", turnover=" + turnover +
                ", totalTurnover=" + totalTurnover +
                ", addTime=" + addTime +
                '}';
    }
}

```

## using in java 

``` java
public static void main(String[] args) {

        /**
         * Mock数据，Java对象列表
         */
        List<ShopDTO> shopDTOList = new ArrayList<ShopDTO>();
        for (int i = 0; i < 100; i++) {
            ShopDTO shop = new ShopDTO(true, "商户" + i, (short) i, 1000 + i, 10000 + i, (float) (1000 + i), (double) (10000 + i), new Date());
            shopDTOList.add(shop);
        }
        String filePath = "/Users/carlosxiao/Desktop/B2B.xlsx";

        /**
         * Excel导出：Object 转换为 Excel
         */
        ExcelExportUtil.exportToFile(filePath, shopDTOList, shopDTOList);
        /**
         * Excel导入：Excel 转换为 Object
         */
        List<Object> list = ExcelImportUtil.importExcel(BankDto.class, filePath);

        System.out.println(list);

    }

```
