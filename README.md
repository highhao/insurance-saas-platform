# 產險後端 API：車險與旅平險

依實際 Controller 與 Service 整理兩種險種的投保步驟及後端設計；不公開承保、計價等業務規則。

## 投保流程

### 車險

1. 查詢或填寫車輛資料。
2. 依投保路徑填寫車況問答，或直接查詢報價。
3. 查看報價與方案，選擇投保內容。
4. 建立訂單。
5. 視需要更新投保日期、車輛、要保人、被保險人或方案資料。
6. 依訂單狀態送出、簽署或付款，並查詢訂單狀態。

### 旅平險

1. 填寫旅遊資料。
2. 查詢並比較方案，再取得所選方案報價。
3. 建立訂單。
4. 視需要更新旅客及訂單資料。
5. 依訂單狀態送出、簽署或付款，並查詢訂單狀態。

以上依 Controller 提供的 API 功能排序；各項操作是不同端點，實際順序會依險種及訂單狀態調整。ZIP 中未包含前端頁面，這裡呈現的是 API 對應的操作順序，不含前端元件。

## 後端請求流程

1. **身份驗證：** Spring Security Filter 驗證請求；Controller 取得已驗證的使用者資訊。
2. **接收資料：** 車險或旅平險 Controller 接收 DIO 與使用者上下文，交給對應的用例 Service。
3. **執行用例：** Service 繼承 `BaseService`；共用 `request()` 呼叫 `processData()`，並處理共通例外流程。
4. **協作與存取：** `processData()` 依端點使用 Worker；部分 Service 直接使用 Mapper 或 Repository，並非固定的單一路徑。
5. **組裝回應：** `generateResult()` 將處理結果包裝成 DTO／`ResponseDto`。

## 設計考量

從程式結構推估，這個模組在同一後端支援車險與旅平險兩種 API，同時共用身份驗證、請求生命週期與回應格式；險種差異則留在各自的 Controller、用例 Service 與協作者。

- `CarInsuranceController` 與 `TravelController` 分開承接險種端點，避免將不同輸入與用例依賴塞進單一入口。
- `BaseService` 收斂共通請求處理；用例 Service 保留各自的 `processData()` 與 `generateResult()` 實作。
- Worker 與資料存取依用例選用。這保留用例彈性，但各 Service 的資料存取邊界並非完全一致。

## 技術

- **語言與框架：** Java 11、Spring Boot 2.7.8、Spring Web
- **API 與驗證：** Spring Security、JWT、Bean Validation、Swagger/OpenAPI
- **資料與整合：** PostgreSQL、JPA、JDBC、MyBatis、Spring Batch、WebService、Google Cloud Storage
- **維運：** 排程、Spring Boot Actuator、Maven
