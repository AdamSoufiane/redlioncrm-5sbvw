package ai.shreds.external;

import java.util.UUID;

/**
 * Interface for external security services.
 * Implementations are expected to provide methods for validating lead data and checking authorization.
 */
public interface ExternalSecurityService {

    /**
     * Validates the lead data against security policies and compliance requirements.
     *
     * @param id      The unique identifier of the lead.
     * @param email   The email address of the lead.
     * @param company The company the lead is associated with.
     * @return true if the lead data is valid according to security standards, false otherwise.
     */
    boolean validateLeadData(UUID id, String email, String company);

    /**
     * Checks if the current operation is authorized under the security protocols.
     *
     * @return true if the operation is authorized, false otherwise.
     */
    boolean checkAuthorization();
}