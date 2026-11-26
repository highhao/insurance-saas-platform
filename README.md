# 產險 SaaS 後端平台

**技術：** Java 17、Spring Boot 3.2.5、PostgreSQL、JPA/JDBC、MyBatis、Spring Security。

## 專案定位

聚焦產險 SaaS 共用後端：API 驗證、應用服務、資料存取、安全及外部服務整合。車險與旅平險 API 的細部契約另見[車險與旅平險 API](https://github.com/highhao/vehicle-travel-insurance-api)。

## 架構重點

Controller／DTO → Service → DAO／Repository；Worker 封裝外部整合，Security Filter 與 Base／AOP 提供共用處理。

## 詳細文件

- [系統架構與元件責任](docs/architecture.md)
- [API 請求流程](docs/workflow.md)

## 後續優化方向（設計建議）

- 依領域界定 JPA、JDBC 與 MyBatis 的使用範圍，並為外部 Worker 補上契約測試與統一錯誤映射。

English: An insurance SaaS backend overview focused on shared API, security, persistence, and integration layers.
