package entity;

/**
 * Represents a response with its associated vector representation.
 */
public class VectorizedResponse {
    /** The textual response */
    private final String response;

    /** The vector representation of the response */
    private final double[] vector;

    /**
     * Constructs a VectorizedResponse object with the specified response text and vector.
     *
     * @param response The textual response.
     * @param vector   The vector representation of the response.
     */
    public VectorizedResponse(String response, double[] vector) {
        this.response = response;
        this.vector = vector;
    }

    /**
     * Retrieves the textual response.
     *
     * @return The response text.
     */
    public String getResponse() {
        return response;
    }

    /**
     * Retrieves the vector representation of the response.
     *
     * @return The vector as an array of doubles.
     */
    public double[] getVector() {
        return vector;
    }
}
