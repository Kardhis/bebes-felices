package com.bebesfelices.api.dto.shared;

import java.util.List;

public record TrustAuthority(
        String howWeSelect,
        List<String> analysisCriteria,
        List<String> editorialTransparency
) {
}
