package se.markusmaga.jayway.weather.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SearchResponse {

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("cod")
    @Expose
    private String cod;

    @SerializedName("count")
    @Expose
    private int count;

    @SerializedName("list")
    @Expose
    private List<SearchResult> list = new ArrayList<>();

    /**
     * @return The message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return The cod
     */
    public String getCod() {
        return cod;
    }

    /**
     * @param cod The cod
     */
    public void setCod(String cod) {
        this.cod = cod;
    }

    /**
     * @return The count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count The count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * @return The list
     */
    public java.util.List<SearchResult> getList() {
        return list;
    }

    /**
     * @param list The list
     */
    public void setList(java.util.List<SearchResult> list) {
        this.list = list;
    }

}
