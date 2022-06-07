package com.example.monolithic.domain.alert.service;

import com.example.monolithic.domain.alert.dto.AlertPostRequest;
import com.example.monolithic.domain.alert.entity.Alert;
import org.springframework.lang.NonNull;

public interface AlertService {

	Alert register(@NonNull final AlertPostRequest alertPostRequest);
}
