package com.epam.poc.kinvey.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Token {
    @SerializedName("issued_at")
    @Expose
    private String issuedAt;
    @SerializedName("scope")
    @Expose
    private String scope;
    @SerializedName("user_company_id")
    @Expose
    private String userCompanyId;
    @SerializedName("expires_in")
    @Expose
    private String expiresIn;
    @SerializedName("user_email")
    @Expose
    private String userEmail;
    @SerializedName("organization_id")
    @Expose
    private String organizationId;
    @SerializedName("token_type")
    @Expose
    private String tokenType;
    @SerializedName("client_id")
    @Expose
    private String clientId;
    @SerializedName("access_token")
    @Expose
    private String accessToken;
    @SerializedName("organization_name")
    @Expose
    private String organizationName;
    @SerializedName("application_id")
    @Expose
    private String applicationId;

    public String getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(String issuedAt) {
        this.issuedAt = issuedAt;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUserCompanyId() {
        return userCompanyId;
    }

    public void setUserCompanyId(String userCompanyId) {
        this.userCompanyId = userCompanyId;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

}
