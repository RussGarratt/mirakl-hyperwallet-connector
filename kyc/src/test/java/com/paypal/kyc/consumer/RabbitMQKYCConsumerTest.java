package com.paypal.kyc.consumer;

import com.hyperwallet.clientsdk.model.HyperwalletWebhookNotification;
import com.paypal.kyc.service.KYCUserNotificationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RabbitMQKYCConsumerTest {

	@InjectMocks
	private RabbitMQKYCConsumer testObj;

	@Mock
	private KYCUserNotificationService KYCUserNotificationServiceMock;

	@Mock
	private HyperwalletWebhookNotification hyperwalletWebhookNotificationMock;

	@Test
	void sendKycNotification_shouldCallKyCUserNotificationService() {

		testObj.sendKycNotification(hyperwalletWebhookNotificationMock);

		verify(KYCUserNotificationServiceMock).updateUserKYCStatus(hyperwalletWebhookNotificationMock);
		verify(KYCUserNotificationServiceMock).updateUserDocumentsFlags(hyperwalletWebhookNotificationMock);
	}

}
