# 產險 API 模組架構

本頁依據 `insurance` Maven 模組的程式套件與類別整理。車險與旅平險的端點呼叫案例見[獨立說明](https://github.com/highhao/vehicle-travel-insurance-api)。

## 技術堆疊

| 分類 | 技術／元件 |
|---|---|
| 語言與框架 | Java 11、Spring Boot 2.7.8、Spring Web |
| API 與驗證 | Spring Security、JWT、Bean Validation、Swagger/OpenAPI |
| 資料庫與存取 | PostgreSQL、Spring Data JPA、JDBC、MyBatis |
| 背景處理 | Spring Batch、排程工作 |
| 外部整合 | 按服務商分組的 WebService 元件、HTTP Client、Google Cloud Storage |
| 監控與建置 | Spring Boot Actuator、Maven |

## 程式模組責任

| 套件／類別 | 程式中的責任 |
|---|---|
| `controller` | REST 入口；接收 request DIO，取得已驗證的使用者上下文，委派對應 Service |
| `service` | 以用例為單位；繼承 `generic BaseService`，實作 `processData` 與 `generateResult` |
| `worker` | 用例協作元件，包含驗證、資料／訂單協調、通知等；部分 Worker 會再呼叫其他元件 |
| `webservice` | 按外部服務商組織的整合元件及其資料契約 |
| `dao` | Spring Data Repository、MyBatis Mapper、Entity／DO 等資料存取元件 |
| `configuration`、`base`、`aop` | Spring Security、共用回應／例外與日誌等基礎處理 |

## 實際呼叫關係

```mermaid
flowchart TB
    Client[HTTP Client] --> Security[Spring Security / JWT Filter]
    Security --> Controller[Controller\nDIO + AuthenticatedMember]
    Controller -->|service.request(member, input)| UseCase[具體用例 Service\nextends generic BaseService]
    UseCase -->|BaseService 呼叫覆寫方法| Process[processData]
    Process -->|依用例協調| Workers[Worker 元件\n驗證 / 流程協調 / 通知]
    Process -.部分 Service 直接使用.-> DataAccess[Repository / Mapper]
    Workers -.依用例存取.-> DataAccess
    Workers -.需要外部服務時.-> WebService[WebService 整合元件]
    DataAccess --> Models[Entity / DO]
    Models --> DB[(PostgreSQL)]
    Process -->|輸出資料| Generate[generateResult]
    Generate --> Response[ResponseDto\nOutputDTO + MetaDto]
    Response --> Controller
    Controller --> Client
    CrossCutting[共用例外 / AOP 日誌] -.橫切處理.-> UseCase
```

這是依程式碼整理的協作關係，不代表每個 API 都會呼叫所有 Worker、Mapper、Repository 或外部服務。`Service` 與持久化層也不是固定的單一路徑；實際依賴由各用例決定。

English: The module uses per-use-case services, shared service flow, worker collaborators, and both repository and mapper persistence components.
