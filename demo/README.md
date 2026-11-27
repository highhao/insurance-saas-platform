# 產險 API 分層 demo

使用虛構資料重新撰寫的車險與旅平險報價架構範例。展示 Controller、Dio／Dto、Service 共用處理流程與 Worker，沒有實際保費計算或外部保險公司連線。

## 呼叫關係

`Controller → Service.request → processData → Worker → generateResult → ResponseDto`

原專案中車險與旅平險各有對應 Controller、Dio／Dto、Service 和 Worker，Service 使用共用處理流程。此 demo 以共用模擬 Worker、虛構欄位及結果簡化展示，沒有還原真實欄位、身分驗證、資料庫或各保險公司規則。

## 執行

需 JDK 11 以上及 Maven。

```bash
mvn spring-boot:run
```

啟動後開啟 `http://localhost:8080/`，可選擇車險或旅平險，依序查看示範方案、虛構投保人、模擬條件、示範報價與成功／失敗結果。此流程無資料庫、保險公司或金流連線。

也可在另一個終端送出虛構請求：

```bash
curl -X POST http://localhost:8080/demo/quotes/car -H "Content-Type: application/json" -d '{"reference":"demo-sedan","vehicleType":"sedan","vehicleYear":2020}'
```

旅平險端點為 `/demo/quotes/travel`，示範 request 可使用 `{"reference":"demo-trip","country":"JP","departureDate":"2027-05-01","returnDate":"2027-05-05"}`。執行 `mvn test` 可檢查兩條端點與輸入驗證。

## Request／Response 範例

以下資料及欄位均為 demo 自行定義，展示 API 層與資料格式。

### 預估查價

`POST /demo/quotes/car`

```json
{ "reference": "demo-sedan", "vehicleType": "sedan", "vehicleYear": 2020 }
```

```json
{
  "data": {
    "category": "car",
    "condition": "demo-sedan",
    "plans": [
      { "provider": "示範公司 A", "plan": "方案 A", "estimatedAmount": 1000 },
      { "provider": "示範公司 B", "plan": "方案 B", "estimatedAmount": 1200 }
    ]
  },
  "startedAt": "2026-01-01T00:00:00Z"
}
```

### 模擬投保結果

`POST /demo/application/submit`

```json
{ "category": "car", "plan": "A", "scenario": "none", "outcome": "success" }
```

```json
{
  "data": {
    "status": "模擬完成",
    "message": "示範投保已完成",
    "demoReference": "DEMO-12345678",
    "category": "car",
    "plan": "A",
    "sampleAmount": 1000
  },
  "startedAt": "2026-01-01T00:00:00Z"
}
```

`ApplicationStatus` 是 demo 的結果 enum，僅區分模擬完成與未完成，並非原系統的完整訂單狀態。範例無持久化與狀態機，畫面步驟只供體驗流程，API 不保證呼叫順序。
