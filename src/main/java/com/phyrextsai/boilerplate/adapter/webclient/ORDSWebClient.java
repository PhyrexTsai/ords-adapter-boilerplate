package com.phyrextsai.boilerplate.adapter.webclient;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
import com.phyrextsai.boilerplate.adapter.converter.EntityConverter;
import com.phyrextsai.boilerplate.adapter.entity.CollectionItem;
import com.phyrextsai.boilerplate.adapter.exception.ORDSException;
import com.phyrextsai.boilerplate.adapter.parameter.Employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ORDSWebClient {
  private Logger log = LoggerFactory.getLogger(getClass());

  private final String ROOT_ORDS_URL = "http://localhost:8080/ords";

  private WebClient webClient = WebClient.builder()
    .baseUrl(ROOT_ORDS_URL)
    .filter(logRequest())
    .filter(logResponse())
    .build();

  private ORDSWebClient() {
  }

  public static ORDSWebClient client() {
    return ORDSWebClientHolder.INSTANCE;
  }

  private static class ORDSWebClientHolder {
    private static ORDSWebClient INSTANCE = new ORDSWebClient();
  }

  private ExchangeFilterFunction logRequest() {
    return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
      log.info("[REQUEST] {} {}", clientRequest.method(), clientRequest.url());
      clientRequest.headers()
        .forEach((name, values) -> values.forEach(value -> log.info("{}={}", name, value)));
      return Mono.just(clientRequest);
    });
  } 

  private ExchangeFilterFunction logResponse() {
    return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
      if(clientResponse.statusCode() != null && clientResponse.statusCode().isError()) {
        log.info("[ERROR] Status Code: {}", clientResponse.statusCode());
        return clientResponse.bodyToMono(String.class)
          .flatMap(errorBody -> {
            return Mono.error(new ORDSException(errorBody, clientResponse.statusCode()));
          });
      } else {
        log.info("[RESPONSE] Status Code: {}", clientResponse.statusCode());
        return Mono.just(clientResponse);
      }
    });
  }

  /**
   * Get all employees
   * @return employee list
   */
  public List<Employee> loadEmployeeList() {
    Mono<CollectionItem> monoCollectionItem =  webClient.get()
      .uri("/bankee/api/employees/")
      .accept(MediaType.APPLICATION_JSON)
      .exchange()
      .flatMap(resp -> resp.bodyToMono(CollectionItem.class));

    CollectionItem collectionItem = monoCollectionItem.block();

    return EntityConverter.toEmployeeList(collectionItem);
  }

  /**
   * Get employee by id
   * @param id employee id
   * @return employee
   */
  public Employee loadEmployeeById(Integer id) {
    Mono<CollectionItem> monoCollectionItem = webClient.get()
      .uri("/bankee/api/employees/" + id)
      .accept(MediaType.APPLICATION_JSON)
      .exchange()
      .flatMap(resp -> resp.bodyToMono(CollectionItem.class));

    CollectionItem collectionItem = monoCollectionItem.block();

    return (EntityConverter.toEmployeeList(collectionItem).size() > 0) ? EntityConverter.toEmployeeList(collectionItem).get(0) : null;
  }

  /**
   * Create employee
   * 
   * https://stackoverflow.com/questions/46759603/how-to-get-response-body-when-testing-the-status-code-in-webflux-webclient
   * 
   * @param employee
   * @return
   */
  public ClientResponse createEmployee(Employee employee) {
    Mono<ClientResponse> clientResponse = webClient.post()
      .uri("/bankee/api/employees/")
      .contentType(MediaType.APPLICATION_JSON)
      .body(BodyInserters.fromPublisher(Mono.just(employee), Employee.class))
      .exchange();
      /*.retrieve()
      .onStatus(status -> !HttpStatus.OK.equals(status), response -> {
        System.out.println("Error while calling endpoint {} with status code "
        + response.statusCode());
        throw new RuntimeException("Error while calling  accounts endpoint");
      })
      .bodyToMono(ClientResponse.class);*/
      
    return clientResponse.block();
  }

  /**
   * Update employee
   * @param employee
   * @return
   */
  public ClientResponse updateEmployee(Employee employee) {
    Mono<ClientResponse> clientResponse = webClient.put()
      .uri("/bankee/api/employees/")
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON)
      .body(BodyInserters.fromPublisher(Mono.just(employee), Employee.class))
      .exchange();

    return clientResponse.block();
  }

  /**
   * Delete employee by id
   * @param id
   * @return
   */
  public ClientResponse deleteEmployee(Integer id) {
    Mono<ClientResponse> clientResponse = webClient.delete()
      .uri("/bankee/api/employees/" + id)
      .accept(MediaType.APPLICATION_JSON)
      .exchange();

    return clientResponse.block();
  }

}
