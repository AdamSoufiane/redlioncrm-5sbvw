package ai.shreds.shared;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;

/**
 * Data Transfer Object for Lead Data.
 * This DTO encapsulates lead information fetched from external platforms.
 *
 * Note: Use Lombok annotations for generating getters, setters, and constructors.
 */
@Data
public class SharedLeadDataDTO {
    /**
     * Unique identifier for the lead.
     */
    private UUID id;

    /**
     * Name of the lead.
     */
    private String name;

    /**
     * Email address of the lead.
     */
    private String email;

    /**
     * Company the lead is associated with.
     */
    private String company;

    /**
     * Position or job title of the lead.
     */
    private String position;

    /**
     * Geographical location of the lead.
     */
    private String location;

    /**
     * Industry sector the lead belongs to.
     */
    private String industry;

    /**
     * Estimated revenue of the company the lead is associated with.
     */
    private BigDecimal revenue;

    /**
     * External platform from which the lead was fetched (e.g., 'LinkedIn').
     */
    private String sourcePlatform;

    /**
     * Timestamp when the lead data was fetched.
     */
    private LocalDateTime fetchedAt;
}