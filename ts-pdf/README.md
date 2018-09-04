# Java export PDF

## Add denpendency

``` xml
<dependency>
      <groupId>com.cc.pub</groupId>
      <artifactId>ts-pdf</artifactId>
      <version>0.0.1-SNAPSHOT</version>
</dependency>

```

## Usage

``` java
public static void main(String[] args) {
        String filePath = "/Users/carlosxiao/Downloads/file.pdf";
        String content = "<!DOCTYPE html><html><meta charset='utf-8'/><head></head><body style='margin:0; padding:0px; color:#666; font-family:SimSun; font-size:14px;'><h1>hello</h1></body></html>";
        PdfExportUtil.exportToFile(filePath, content);


```
