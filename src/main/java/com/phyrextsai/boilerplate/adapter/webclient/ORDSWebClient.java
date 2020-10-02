package com.phyrextsai.boilerplate.adapter.webclient;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
import com.phyrextsai.boilerplate.adapter.converter.EntityConverter;
import com.phyrextsai.boilerplate.adapter.entity.CollectionItem;

import com.phyrextsai.boilerplate.adapter.exception.ParameterMismatchException;

public class ORDSWebClient {
  private final String ROOT_ORDS_URL = "http://localhost:8080/ords";

  private ORDSWebClient() {
  }

  public static ORDSWebClient client() {
    return ORDSWebClientHolder.INSTANCE;
  }

  private static class ORDSWebClientHolder {
    private static ORDSWebClient INSTANCE = new ORDSWebClient();
  }

}
