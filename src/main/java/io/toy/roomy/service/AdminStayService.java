package io.toy.roomy.service;

import io.toy.roomy.domain.Stay;
import io.toy.roomy.dto.request.StayRequest;

public interface AdminStayService {

    Stay regStay(StayRequest dto);
}
