package ai.shreds.application.ports;

import ai.shreds.shared.SharedLeadDataDTO;

/**
 * Interface for security service operations related to lead data validation and authorization checks.
 */
public interface ApplicationSecurityServicePort {
    /**
     * Validates the lead data against security policies and compliance requirements.
     *
     * @param leadData the lead data to validate
     * @return true if the lead data is valid according to security standards, false otherwise
     */
    boolean validateData(SharedLeadDataDTO leadData);

    /**
     * Checks if the current operation is authorized under the security protocols.
     *
     * @return true if the operation is authorized, false otherwise
     */
    boolean authorizeOperation();
}