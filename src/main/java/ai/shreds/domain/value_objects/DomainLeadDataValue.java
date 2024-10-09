package ai.shreds.domain.value_objects;

import ai.shreds.domain.entities.DomainLeadEntity;
import lombok.Getter;
import java.util.UUID;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DomainLeadDataValue is a value object that encapsulates DomainLeadEntity.
 * It acts as a wrapper or decorator to DomainLeadEntity, providing an additional layer
 * of abstraction and potentially enhancing the domain model with more behavior or constraints
 * specific to the business logic.
 */
@Getter
public class DomainLeadDataValue {

    private final DomainLeadEntity leadEntity;

    /**
     * Constructor to initialize the lead entity.
     *
     * @param leadEntity The lead entity to be encapsulated.
     */
    public DomainLeadDataValue(DomainLeadEntity leadEntity) {
        if (leadEntity == null) {
            throw new IllegalArgumentException("Lead entity cannot be null.");
        }
        this.leadEntity = leadEntity;
    }

    public UUID getId() {
        return leadEntity.getId();
    }

    public String getName() {
        return leadEntity.getName();
    }

    public String getEmail() {
        return leadEntity.getEmail();
    }

    public String getCompany() {
        return leadEntity.getCompany();
    }

    public String getPosition() {
        return leadEntity.getPosition();
    }

    public String getLocation() {
        return leadEntity.getLocation();
    }

    public String getIndustry() {
        return leadEntity.getIndustry();
    }

    public BigDecimal getRevenue() {
        return leadEntity.getRevenue();
    }

    public String getSourcePlatform() {
        return leadEntity.getSourcePlatform();
    }

    public LocalDateTime getFetchedAt() {
        return leadEntity.getFetchedAt();
    }
}