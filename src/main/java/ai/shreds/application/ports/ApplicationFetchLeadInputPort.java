package ai.shreds.application.ports;

import ai.shreds.shared.SharedFetchLeadRequestParams;
import ai.shreds.shared.SharedApiResponse;

/**
 * Interface for input port to fetch leads.
 * This interface defines the contract for fetching leads based on parameters provided.
 */
public interface ApplicationFetchLeadInputPort {
    /**
     * Fetches leads based on provided parameters.
     * @param params The parameters to filter the leads.
     * @return SharedApiResponse indicating the result of the fetch operation.
     */
    SharedApiResponse fetchLeads(SharedFetchLeadRequestParams params);
}