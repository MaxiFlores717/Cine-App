package com.cineapp.cine_app.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;



@Controller
public class MercadoPagoService {

    @GetMapping("/mercado")
    public String crearPreferenciaPago(@RequestParam String titulo, @RequestParam double precio, Model model)
            throws MPException, MPApiException {
        MercadoPagoConfig.setAccessToken("TEST-8375554891244595-070723-9bba8c34bf20623643c4ce7831bfd377-610012598");

        PreferenceBackUrlsRequest backUrls =
                // ...
                PreferenceBackUrlsRequest.builder().success("http://localhost:8080/")
                        .pending("http://localhost:8080/").failure("http://localhost:8080/").build();

        // ...

        PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                .id("1234")
                .title(titulo)
                .description("boleto")
                .pictureUrl("http://picture.com/PS5")
                .categoryId("tickets")
                .quantity(1)
                .currencyId("ARS")
                .unitPrice(new BigDecimal(precio))
                .build();


        List<PreferenceItemRequest> items = new ArrayList<>();
        items.add(itemRequest);
        PreferenceRequest preferenceRequest = PreferenceRequest.builder().items(items).backUrls(backUrls).build();
        
        PreferenceClient client = new PreferenceClient();
        Preference preference = client.create(preferenceRequest);
        String urlPago = preference.getSandboxInitPoint();  // URL de prueba

        // Pasar la URL a la vista
        model.addAttribute("urlPago", urlPago);
        return "redirect:" + urlPago;  // Redirigir directamente a Mercado Pago
    }

}
