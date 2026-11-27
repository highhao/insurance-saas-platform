# 產險 API｜分層範例

以下以虛構資料重新整理實際專案中的分層概念，供閱讀架構使用；流程與類別名稱經簡化。

```text
Controller → Service → Worker／WebService → 外部系統
                    └→ Mapper／Repository → 資料庫
         DTO 定義請求與回應邊界
```

- **Controller** 接收請求並交由 Service 處理。
- **Service** 編排用例；共用基底將處理與結果轉換拆成擴充點。
- **Worker／WebService** 封裝外部通訊；**Mapper／Repository** 負責資料讀寫。
- **DTO** 承載跨層資料，避免將外部格式直接擴散到入口。

```java
// 簡化示意：方法與資料類型均為虛構
class QuoteController {
    private final QuoteService service;

    QuoteResponse quote(QuoteRequest request) {
        return service.execute(request);
    }
}

abstract class BaseService<I, D, O> {
    final O execute(I input) {
        D data = process(input);
        return toResponse(data);
    }

    protected abstract D process(I input);
    protected abstract O toResponse(D data);
}

class QuoteService extends BaseService<QuoteRequest, QuoteData, QuoteResponse> {
    private final ProviderGateway provider;

    protected QuoteData process(QuoteRequest request) {
        return provider.fetch(request);
    }

    protected QuoteResponse toResponse(QuoteData data) {
        return QuoteResponse.from(data);
    }
}
```

此片段展示職責與依賴方向，省略建構子、型別定義及系統設定，並非可執行範例。實際系統會依用例使用不同服務與資料存取方式。
