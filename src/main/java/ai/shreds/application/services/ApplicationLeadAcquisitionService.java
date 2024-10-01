package ai.shreds.application.services;

import ai.shreds.shared.SharedFetchLeadRequestParams;
import ai.shreds.shared.SharedApiResponse;
import ai.shreds.application.ports.ApplicationFetchLeadInputPort;
import ai.shreds.application.ports.ApplicationExternalApiPort;
import ai.shreds.application.ports.ApplicationSecurityServicePort;
import ai.shreds.application.ports.ApplicationLeadProcessingOutputPort;
import ai.shreds.shared.SharedLeadDataDTO;
import ai.shreds.application.exceptions.ApplicationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ApplicationLeadAcquisitionService implements ApplicationFetchLeadInputPort {
    private final ApplicationExternalApiPort externalApiPort;
    private final ApplicationSecurityServicePort securityServicePort;
    private final ApplicationLeadProcessingOutputPort leadProcessingOutputPort;

    @Override
    public SharedApiResponse fetchLeads(SharedFetchLeadRequestParams params) {
        try {
            log.info("Validating request parameters.");
            validateRequestParams(params);
            log.info("Fetching leads from external API.");
            List<SharedLeadDataDTO> leads = fetchLeadsFromExternalApi();
            log.info("Validating fetched lead data.");
            List<SharedLeadDataDTO> validatedLeads = validateLeadData(leads);
            log.info("Forwarding validated leads to processing service.");
            forwardLeadsToProcessing(validatedLeads);
            return new SharedApiResponse("success", "Lead data fetching and processing initiated.");
        } catch (IllegalArgumentException | ApplicationException e) {
            log.error("Error during lead acquisition process: {}", e.getMessage());
            return new SharedApiResponse("error", e.getMessage());
        }
    }

    private void validateRequestParams(SharedFetchLeadRequestParams params) {
        if (params == null ||
            Optional.ofNullable(params.getIndustry()).isEmpty() ||
            Optional.ofNullable(params.getLocation()).isEmpty() ||
            Optional.ofNullable(params.getCompanySize()).isEmpty() ||
            Optional.ofNullable(params.getPosition()).isEmpty()) {
            throw new IllegalArgumentException("Request parameters cannot be null or empty");
        }
    }

    private List<SharedLeadDataDTO> fetchLeadsFromExternalApi() {
        externalApiPort.connect();
        List<SharedLeadDataDTO> leads = externalApiPort.fetchLeadData();
        if (leads == null || leads.isEmpty()) {
            throw new ApplicationException("Failed to fetch leads from external API");
        }
        return leads;
    }

    private List<SharedLeadDataDTO> validateLeadData(List<SharedLeadDataDTO> leads) {
        if (leads == null) {
            throw new ApplicationException("Leads list cannot be null");
        }
        return leads.stream()
                .filter(lead -> securityServicePort.validateData(lead))
                .collect(Collectors.toList());
    }

    private void forwardLeadsToProcessing(List<SharedLeadDataDTO> leads) {
        if (leads == null) {
            throw new ApplicationException("Leads list cannot be null");
        }
        leadProcessingOutputPort.processLeads(leads);
    }
}