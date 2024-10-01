package ai.shreds.domain.ports;

import ai.shreds.domain.entities.DomainLeadEntity;

/**
 * Interface for security services within the domain layer.
 * Implementations are expected to provide security checks and validations for lead data.
 * This interface ensures that all lead data complies with security protocols before being processed further.
 * Note: Implementations should handle exceptions and logging appropriately.
 */
public interface DomainSecurityServicePort {
    /**
     * Validates the lead data against security policies and compliance requirements.
     * @param lead The lead entity to validate.
     * @return true if the lead data is valid according to security standards, false otherwise.
     */
    boolean validateLeadData(DomainLeadEntity lead);
}