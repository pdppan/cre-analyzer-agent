package com.wf.hackathon.cre.model;

import java.time.OffsetDateTime;

public record DealMemoResponse(
    String dealId,
    OffsetDateTime generatedAt,
    DealMemo memo
) {}
