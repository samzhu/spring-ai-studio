package io.github.samzhu.studio.constants;

/**
 * 工作流程節點類型
 */
public enum NodeType {
    /**
     * 流程起始節點，每個工作流程只會有一個
     */
    START,

    /**
     * 大語言模型節點，處理 AI 對話
     */
    LLM,

    /**
     * 決策節點，根據條件分流
     */
    ROUTER,

    /**
     * 知識庫節點，查詢相關資訊
     */
    KNOWLEDGE,

    /**
     * 流程結束節點
     */
    END;
}
