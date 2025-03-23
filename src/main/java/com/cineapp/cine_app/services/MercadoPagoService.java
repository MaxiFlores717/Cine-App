package com.cineapp.cine_app.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferencePaymentMethodRequest;
import com.mercadopago.client.preference.PreferencePaymentMethodsRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.core.MPRequestOptions;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.preference.Preference;



@Controller
public class MercadoPagoService {
        private static final String INTEGRATOR_ID = "dev_24c65fb163bf11ea96500242ac130004";


    @GetMapping("/mercado")
    public String crearPreferenciaPago(@RequestParam String titulo, @RequestParam double precio, Model model)
            throws MPException, MPApiException {
        MercadoPagoConfig.setAccessToken("APP_USR-7426806883583257-070800-78a2cf07e165ab9d6e23a9375bdf1647-1889902603");

        PreferenceBackUrlsRequest backUrls =
                // ...
                PreferenceBackUrlsRequest.builder().success("http://localhost:8080/")
                        .pending("http://localhost:8080/peliculas/cartelera").failure("http://localhost:8080/funciones/listado").build();

        // ...

        PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                .id("1234")
                .title(titulo)
                .description("Dispositivo de tienda móvil de comercio electrónico")
                .pictureUrl("http://picture.com/PS5")
                .categoryId("tickets")
                .quantity(1)
                .currencyId("ARS")
                .unitPrice(new BigDecimal(10000))
                .build();



  
        PreferencePaymentMethodsRequest paymentMethods = PreferencePaymentMethodsRequest.builder()
        .installments(6)
        .excludedPaymentMethods(List.of(
                PreferencePaymentMethodRequest.builder().id("visa").build()
        )) 
        .build();



        List<PreferenceItemRequest> items = new ArrayList<>();
        items.add(itemRequest);
        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .items(items)
                .backUrls(backUrls)
                .paymentMethods(paymentMethods)
                .autoReturn("approved")
                //gnrok
                .notificationUrl(titulo)
                .build();

                
        
        PreferenceClient client = new PreferenceClient();
        Preference preference = client.create(preferenceRequest);

         MPRequestOptions requestOptions = MPRequestOptions.builder()
                .customHeaders(Map.of("X-Integrator-Id", INTEGRATOR_ID))  // 🔥 Integrator ID agregado como Header
                .build();



        String urlPago = preference.getSandboxInitPoint();  // URL de prueba

        // Pasar la URL a la vista
        model.addAttribute("urlPago", urlPago);
        return "redirect:" + urlPago;  // Redirigir directamente a Mercado Pago
    }

}
