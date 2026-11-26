# 產險 SaaS 後端模組概觀

**主要技術：** Java 11、Spring Boot 2.7.8。此頁整理產險 API 模組的程式組織；車險與旅平險請求路徑另有[專項架構案例](https://github.com/highhao/vehicle-travel-insurance-api)。

## 模組結構

程式依 `controller`、`service`、`worker`、`webservice`、`dao`、`configuration` 等套件分工。具體 Service 繼承共用 `BaseService`；其 `processData` 依用例協調 Worker，部分流程也直接使用 Mapper 或 Repository。

## 架構與流程

- [模組責任與技術堆疊](docs/architecture.md)
- [實際請求處理流程](docs/workflow.md)

English: An overview of the insurance API module and its actual Spring service flow.
