package ai.shreds.infrastructure.external_services;

import ai.shreds.domain.entities.DomainLeadEntity;
import ai.shreds.domain.ports.DomainLeadExternalProcessingPort;
import ai.shreds.infrastructure.logging.InfrastructureLogger;
import ai.shreds.infrastructure.exceptions.InfrastructureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.beans.factory.annotation.Value;

/**
 * InfrastructureLeadProcessingServiceClient class implements the DomainLeadExternalProcessingPort interface.
 * This class is responsible for handling the processing of lead data within an external service infrastructure.
 */
@Slf4j
@RequiredArgsConstructor
public class InfrastructureLeadProcessingServiceClient implements DomainLeadExternalProcessingPort {

    private final InfrastructureLogger logger;
    private final JmsTemplate jmsTemplate;

    @Value("${jms.queue.destination}")
    private String destinationQueue;

    /**
     * Processes the given lead data.
     *
     * @param lead the lead entity to be processed
     */
    @Override
    public void processLead(DomainLeadEntity lead) {
        try {
            // Processing logic could involve calling external services, applying business rules, etc.
            logger.logInfo(String.format("Processing lead: %s", lead.getId()));
            // Publish message to JMS
            jmsTemplate.convertAndSend(destinationQueue, lead);
        } catch (IllegalArgumentException e) {
            logger.logError(String.format("Invalid argument for lead: %s, Error: %s", lead.getId(), e.getMessage()));
            throw new InfrastructureException("Invalid argument for lead", e);
        } catch (Exception e) {
            logger.logError(String.format("Error processing lead: %s, Error: %s", lead.getId(), e.getMessage()));
            throw new InfrastructureException("Failed to process lead", e);
        }
    }
}