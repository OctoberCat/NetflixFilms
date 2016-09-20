
package com.netflixfilms.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiError {

    @SerializedName("errorcode")
    @Expose
    private long errorcode;
    @SerializedName("message")
    @Expose
    private String message;

    public long getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(long errorcode) {
        this.errorcode = errorcode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
