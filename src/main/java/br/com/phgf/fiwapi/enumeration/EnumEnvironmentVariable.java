package br.com.phgf.fiwapi.enumeration;

public enum EnumEnvironmentVariable {

    FIWAPI_USER(true, null),
    FIWAPI_PASSWORD(true, null),
    APP_FIWAPI_NAME(false, "fiwapi"),
    APP_FIWAPI_PORT(false, "8891"),
    WIFEED_DISCOVERY_URL(false, "http://localhost:8761/eureka/");

    private boolean required;
    private String defaultValue;

    EnumEnvironmentVariable(boolean required, String defaultValue) {
        this.required = required;
        this.defaultValue = defaultValue;
    }

    public static void checkEnvironmentVariables() {
        for(EnumEnvironmentVariable var : EnumEnvironmentVariable.values()) {
            if(var.required && System.getenv(var.name()) == null || var.required && System.getenv(var.name()).isEmpty()) {
                throw new RuntimeException("A variável de ambiente " + var.name() + " não foi configurada");
            }
        }
    }

    public String getValue() {
        String value = System.getenv(this.name());
        return value != null ? value : defaultValue;
    }

}
