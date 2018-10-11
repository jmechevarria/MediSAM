package medisam;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceResponse {

    private String authenticationResultCode;
    private String brandLogoUri;
    private String copyright;
    List<ResourceSet> sets = new ArrayList<>();
    private short statusCode;
    private String statusDescription;
    private String traceId;

    public ServiceResponse() {
    }

    public String getBrandLogoUri() {
        return brandLogoUri;
    }

    public void setBrandLogoUri(String brandLogoUri) {
        this.brandLogoUri = brandLogoUri;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getAuthenticationResultCode() {
        return authenticationResultCode;
    }

    public void setAuthenticationResultCode(String authenticationResultCode) {
        this.authenticationResultCode = authenticationResultCode;
    }

    public List<ResourceSet> getSets() {
        return sets;
    }

    public void setSets(List<ResourceSet> sets) {
        this.sets = sets;
    }

    public short getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(short statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    @Override
    public String toString() {
        return "Quote{"
                + "type='" + brandLogoUri + '\''
                + ", value=" + copyright
                + '}';
    }
}
