package com.toolset.file.excel.test;

import com.toolset.file.excel.poi.ExcelImportUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * FUN Test
 */
public class Test {

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
        // ExcelExportUtil.exportToFile(filePath, shopDTOList, shopDTOList);
        /**
         * Excel导入：Excel 转换为 Object
         */
        List<Object> list = ExcelImportUtil.importExcel(BankDto.class, filePath);

        System.out.println(list);

    }

}
