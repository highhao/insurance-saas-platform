# 產險 API 分層 demo

使用虛構資料重新撰寫的車險與旅平險報價架構範例。展示 Controller、Dio／Dto、Service 共用處理流程與 Worker，沒有實際保費計算或外部保險公司連線。

## 呼叫關係

`Controller → Service.request → processData → Worker → generateResult → ResponseDto`

原專案的共用流程另含使用者身分及例外處理；此 demo 刻意簡化。兩種險別使用共用模擬 Worker，並非宣稱原系統使用相同實作。

## 執行

需 JDK 11 以上及 Maven。

```bash
mvn spring-boot:run
```

以另一個終端送出虛構請求：

```bash
curl -X POST http://localhost:8080/demo/quotes/car -H "Content-Type: application/json" -d '{"reference":"demo"}'
```

旅平險端點為 `/demo/quotes/travel`。執行 `mvn test` 可檢查兩條端點與輸入驗證。
