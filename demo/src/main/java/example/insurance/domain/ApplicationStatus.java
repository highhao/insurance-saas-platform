package example.insurance.domain;

import com.fasterxml.jackson.annotation.JsonValue;

/** 示範用結果分類，並非原系統完整的訂單狀態。 */
public enum ApplicationStatus {
    COMPLETED("模擬完成"),
    NOT_COMPLETED("模擬未完成");

    private final String label;
    ApplicationStatus(String label) { this.label = label; }
    @JsonValue public String getLabel() { return label; }
}
