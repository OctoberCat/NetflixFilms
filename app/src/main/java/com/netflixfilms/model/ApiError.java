
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

    /**
     * 
     * @return
     *     The errorcode
     */
    public long getErrorcode() {
        return errorcode;
    }

    /**
     * 
     * @param errorcode
     *     The errorcode
     */
    public void setErrorcode(long errorcode) {
        this.errorcode = errorcode;
    }

    /**
     * 
     * @return
     *     The message
     */
    public String getMessage() {
        return message;
    }

    /**
     * 
     * @param message
     *     The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
