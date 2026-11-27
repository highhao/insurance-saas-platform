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

啟動後開啟 `http://localhost:8080/`，可選擇車險或旅平險，依序查看示範方案、虛構投保人、模擬條件、示範報價與成功／失敗結果。此流程無資料庫、保險公司或金流連線。

也可在另一個終端送出虛構請求：

```bash
curl -X POST http://localhost:8080/demo/quotes/car -H "Content-Type: application/json" -d '{"reference":"demo-sedan"}'
```

旅平險端點為 `/demo/quotes/travel`，示範條件為 `demo-trip`。執行 `mvn test` 可檢查兩條端點與輸入驗證。
