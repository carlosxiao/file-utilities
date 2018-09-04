package com.toolset.file.excel.test;

import com.toolset.file.excel.annotation.ExcelField;
import com.toolset.file.excel.annotation.ExcelSheet;
import org.apache.poi.hssf.util.HSSFColor;

/**
 * Java Object To Excel
 */
@ExcelSheet(name = "银行列表", headColor = HSSFColor.HSSFColorPredefined.LIGHT_GREEN)
public class BankDto {

    @ExcelField(name = "银行编码")
    private String code;

    @ExcelField(name = "银行名称")
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BankDto(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public BankDto() {
    }

    @Override
    public String toString() {
        return "BankDto{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}