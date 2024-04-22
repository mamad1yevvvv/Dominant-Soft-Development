package com.example.dominantsoftdevelopment.service.SendSMS;

import com.example.dominantsoftdevelopment.dto.ApiResponse;
import com.example.dominantsoftdevelopment.exceptions.RestException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class SendSMSService {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Value("${app.smsProvider.login}")
    private String login;
    @Value("${app.smsProvider.password}")
    private String password;
    @Value("${app.smsProvider.baseUri}")
    private String baseUri;
    @Value("${app.smsProvider.loginUri}")
    private String loginUri;

    static class SmsProvider {

        private static class BadRequestException extends Exception {
            BadRequestException(String message) {
                super(message);
            }
        }

        private static class ApiResponseNullException extends Exception {
            ApiResponseNullException(String message) {
                super(message);
            }
        }

        private static class TokenNullException extends Exception {
            TokenNullException(String message) {
                super(message);
            }
        }
    }

    private String getSmsToken(String login, String password, String baseUri, String loginUri)
            throws IOException, InterruptedException, SmsProvider.BadRequestException, SmsProvider.ApiResponseNullException, SmsProvider.TokenNullException {


        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUri + loginUri))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("{\"email\":\"" + login + "\",\"password\":\"" + password + "\"}"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200)
            throw new SmsProvider.BadRequestException("Bad request to the SMS provider");

        String jsonProviderToken = response.body();

        if (jsonProviderToken == null || jsonProviderToken.isEmpty())
            throw new SmsProvider.ApiResponseNullException("The token was not returned from the SMS provider");

        ApiResponse apiResponse = objectMapper.readValue(jsonProviderToken, ApiResponse.class);

        if (apiResponse.data == null || apiResponse.data.token == null || apiResponse.data.token.isEmpty())
            throw new SmsProvider.TokenNullException("Error when deserializing token from SMS provider");

        return apiResponse.data.token;
    }


    public Boolean sendSMS(String phoneNumber, String code) {
        try {

            String smsToken = getSmsToken(login, password, baseUri, loginUri);
            System.out.println(smsToken);

            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("mobile_phone", phoneNumber)
                    .addFormDataPart("message", code)
                    .addFormDataPart("from", "4546")
//                    .addFormDataPart("callback_url", "http://0000.uz/test.php")
                    .build();

            Request request = new Request.Builder()
                    .url(baseUri + "/api/message/sms/send")
                    .addHeader("Authorization", "Bearer " + smsToken)
                    .method("POST", body)
                    .build();
            Response response = client.newCall(request).execute();
            System.out.println(response);

            return response.code()==200;
        } catch (Exception e) {
            throw RestException.restThrow("wrong send sms", HttpStatus.BAD_REQUEST);
        }
    }
}
