# 產險 API｜實際程式分層

車險與旅平險的報價用例，透過共用服務流程處理請求與回應。

```mermaid
flowchart TB
    C[Controller：接收請求] -->|呼叫 request| B[BaseService：共用處理流程]
    B -->|呼叫 processData| S[個別 Service：用例處理]
    S --> W[Worker：執行對應處理]
    B -->|呼叫 generateResult| R[回應資料]
```

## 請求處理

- 車險與旅平險 Controller 按操作呼叫對應 Service 的 `request`，傳入使用者與輸入資料。
- 車險及旅平險的報價 Service 繼承 `BaseService`，分別實作 `processData` 和 `generateResult`。
- 共用 `request` 在處理流程中呼叫這兩個方法，並處理例外及回應。
- 車險與旅平險的報價 Service 呼叫各自的 Worker，處理各用例所需的工作。
- `Dio` 作為這些 Service 的輸入型別，`Dto` 作為處理結果，`ResponseDto` 包裝回應。

```text
Controller
  └─ service.request(使用者, 輸入)
       ├─ processData(使用者, 輸入) → 對應 Worker
       └─ generateResult(使用者, 處理結果, 開始時間) → ResponseDto
```

不同用例依需要使用 Worker、Mapper、Repository 或外部服務。
