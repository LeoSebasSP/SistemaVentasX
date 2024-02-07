package com.ventasx.SistemaVentas.Persistence.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@RequiredArgsConstructor
public enum TypeContributor {

    NATURAL_PERSON("{typeContributor.naturalPerson}"),
    JURIDIC_PERSON("{typeContributor.juridicPerson}"),
    OTHERS("{typeContributor.others}")
    ;

    private final String typeContributorMeaning;
}
