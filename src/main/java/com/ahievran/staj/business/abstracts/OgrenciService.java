package com.ahievran.staj.business.abstracts;

import com.ahievran.staj.core.result.DataResult;
import com.ahievran.staj.core.result.Result;
import com.ahievran.staj.dto.auth.ogrenci.OgrenciRegisterRequest;

public interface OgrenciService {
	Result register(OgrenciRegisterRequest ogrenciRegisterReguest);

	DataResult<?> findByUserIdForOtherService(Long id);
}
