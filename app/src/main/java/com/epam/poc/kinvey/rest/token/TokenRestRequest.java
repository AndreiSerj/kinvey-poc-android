package com.epam.poc.kinvey.rest.token;

import com.epam.poc.kinvey.rest.RestRequest;

import java.util.HashMap;
import java.util.Map;

class TokenRestRequest extends RestRequest {
    final private static Map<String, String> DATA = new HashMap<String, String>() {{
        put("grant_type", "client_credentials");
    }};

    final  private static Map<String, String> HEADERS = new HashMap<String, String>() {{
       put("Authorization", "Basic bDVHQzRQVFUxR0FnWTNmR3drZktYUDR2dGpZS0ZPVVo6dlZydlNKd09qUk1DM25IZA==");
       put("Sync-App-Token", "Yn+Miw8OITEfImNah3Yr8Mm+EBzbFe+jlpxOyTxI+6ls2jPT9fL1D2p21HUNr+bs");
     }};

    TokenRestRequest() {
        super(RestMethod.POST, RestAction.GET_OAUTH_TOKEN.getPath(), HEADERS, DATA);
    }
}
