package com.paypal.sellers.service.impl;

import com.hyperwallet.clientsdk.Hyperwallet;
import com.paypal.sellers.infrastructure.configuration.SellersHyperwalletApiConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SellerHyperwalletSDKServiceImplTest {

	private static final String PROGRAM_TOKEN = "programToken";

	private static final String USER_NAME = "userName";

	private static final String PASSWORD = "password";

	private static final String SERVER = "server";

	private static final String HYPERWALLET_PROGRAM = "hyperwalletProgram";

	@Spy
	@InjectMocks
	private SellerHyperwalletSDKServiceImpl testObj;

	@Mock
	private SellersHyperwalletApiConfig sellersHyperwalletApiConfigMock;

	@Test
	void getHyperwalletInstanceByHyperwalletProgram_shouldReturnAnHyperwalletInstance() {
		when(sellersHyperwalletApiConfigMock.getUserStoreTokens())
				.thenReturn(Map.of(HYPERWALLET_PROGRAM, PROGRAM_TOKEN));
		when(sellersHyperwalletApiConfigMock.getUsername()).thenReturn(USER_NAME);
		when(sellersHyperwalletApiConfigMock.getPassword()).thenReturn(PASSWORD);
		when(sellersHyperwalletApiConfigMock.getServer()).thenReturn(SERVER);

		final Hyperwallet result = testObj.getHyperwalletInstanceByHyperwalletProgram(HYPERWALLET_PROGRAM);

		assertThat(result).hasFieldOrPropertyWithValue("programToken", PROGRAM_TOKEN)
				.hasFieldOrPropertyWithValue("apiClient.username", USER_NAME)
				.hasFieldOrPropertyWithValue("url", SERVER + "/rest/v4");
	}

	@Test
	void getHyperwalletInstanceWithProgramToken_shouldReturnAnHyperwalletInstance() {
		when(sellersHyperwalletApiConfigMock.getUsername()).thenReturn(USER_NAME);
		when(sellersHyperwalletApiConfigMock.getPassword()).thenReturn(PASSWORD);
		when(sellersHyperwalletApiConfigMock.getServer()).thenReturn(SERVER);

		final Hyperwallet result = testObj.getHyperwalletInstanceByProgramToken(PROGRAM_TOKEN);

		assertThat(result).hasFieldOrPropertyWithValue("programToken", PROGRAM_TOKEN)
				.hasFieldOrPropertyWithValue("apiClient.username", USER_NAME)
				.hasFieldOrPropertyWithValue("url", SERVER + "/rest/v4");
	}

}
