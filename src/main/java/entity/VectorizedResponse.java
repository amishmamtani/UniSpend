package entity;

public class VectorizedResponse {
    private final String response;
    private final double[] vector;

    public VectorizedResponse(String response, double[] vector) {
        this.response = response;
        this.vector = vector;
    }

    public String getResponse() {
        return response;
    }

    public double[] getVector() {
        return vector;
    }
}
