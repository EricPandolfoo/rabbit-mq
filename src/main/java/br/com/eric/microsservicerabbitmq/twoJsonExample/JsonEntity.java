package br.com.eric.microsservicerabbitmq.twoJsonExample;

public class JsonEntity {

    private String fromDate;
    private String toDate;
    private String email;
    private String query;

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return "JsonEntity{" +
                "fromDate='" + fromDate + '\'' +
                ", toDate='" + toDate + '\'' +
                ", email='" + email + '\'' +
                ", query='" + query + '\'' +
                '}';
    }
}
