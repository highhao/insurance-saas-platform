# 產險 SaaS 後端平台

**技術：** Java 17、Spring Boot 3.2.5、PostgreSQL、JPA/JDBC、MyBatis、Spring Security。

## 專案情境

整理產險 SaaS 共用後端的 API 驗證、應用服務、資料存取、安全與外部整合。車險與旅平險 API 的細部契約另列於[獨立案例](https://github.com/highhao/vehicle-travel-insurance-api)。

## 架構摘要

Controller／DTO → Service → DAO／Repository；Worker 封裝外部整合，Security Filter 與 Base／AOP 處理共用職責。

## 架構與流程

- [系統架構與元件責任](docs/architecture.md)
- [API 請求流程](docs/workflow.md)

## 後續優化建議

依領域界定 JPA、JDBC 與 MyBatis 的使用範圍，並為外部 Worker 補上契約測試與一致的錯誤映射。

**English:** Shared API, security, persistence, and integration layers for an insurance SaaS backend.
