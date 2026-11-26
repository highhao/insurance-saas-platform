# 產險後端 API：車險與旅平險

依實際 Controller 與 Service 整理兩種險種的投保步驟及 API 分層；不公開承保、計價等業務規則。

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

以上依 Controller 提供的 API 功能排序；各項操作是不同端點，實際順序會依險種及訂單狀態調整。流程不包含前端元件。

## API 分層

`Security Filter → Controller → BaseService.request() → processData() → Worker 和／或 Mapper、Repository → generateResult() → ResponseDto`

- Controller 接收 DIO 與已驗證的使用者資訊；Service 繼承 `BaseService` 共用請求流程。
- 各用例的 Worker 與資料存取方式不同；部分 Service 直接使用 Mapper 或 Repository。

## 設計考量

從程式結構推估，這個模組要在同一後端支援車險與旅平險兩種 API，同時共用身份驗證、請求生命週期與回應格式；險種差異則留在各自的 Controller、用例 Service 與協作者。

- **按險種分開 API 入口：** `CarInsuranceController` 與 `TravelController` 各自承接端點，再交給對應用例 Service，讓車險與旅平險的輸入和協作依賴保持在各自範圍。
- **抽出共用請求生命週期：** 用例 Service 繼承 `BaseService`。基底類別統一呼叫 `processData()`、處理共通例外流程，再呼叫 `generateResult()`；各用例只實作自己的處理與回應組裝。
- **依用例選擇協作者：** Service 會依需求使用 Worker，也有直接依賴 Mapper 或 Repository 的案例。因此實際結構不是固定的 Service → Worker → DAO 直線；這保留了用例彈性，但各 Service 的資料存取邊界並非完全一致。

## 技術

- **語言與框架：** Java 11、Spring Boot 2.7.8、Spring Web
- **API 與驗證：** Spring Security、JWT、Bean Validation、Swagger/OpenAPI
- **資料與整合：** PostgreSQL、JPA、JDBC、MyBatis、Spring Batch、WebService、Google Cloud Storage
- **維運：** 排程、Spring Boot Actuator、Maven

## English

A code-based overview of car and travel insurance API flows and backend layers.
