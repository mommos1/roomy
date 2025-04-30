package io.toy.roomy.service;

import io.toy.roomy.domain.Accommodation;
import io.toy.roomy.dto.request.AccommodationRequest;

public interface ReservationService {

    Accommodation registerRoom(AccommodationRequest dto);
}
