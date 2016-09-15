
package com.netflixfilms.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Film {

    @SerializedName("unit")
    @Expose
    private long unit;
    @SerializedName("show_id")
    @Expose
    private long showId;
    @SerializedName("show_title")
    @Expose
    private String showTitle;
    @SerializedName("release_year")
    @Expose
    private String releaseYear;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("show_cast")
    @Expose
    private String showCast;
    @SerializedName("director")
    @Expose
    private String director;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("poster")
    @Expose
    private String poster;
    @SerializedName("mediatype")
    @Expose
    private long mediatype;
    @SerializedName("runtime")
    @Expose
    private String runtime;

    /**
     * 
     * @return
     *     The unit
     */
    public long getUnit() {
        return unit;
    }

    /**
     * 
     * @param unit
     *     The unit
     */
    public void setUnit(long unit) {
        this.unit = unit;
    }

    /**
     * 
     * @return
     *     The showId
     */
    public long getShowId() {
        return showId;
    }

    /**
     * 
     * @param showId
     *     The show_id
     */
    public void setShowId(long showId) {
        this.showId = showId;
    }

    /**
     * 
     * @return
     *     The showTitle
     */
    public String getShowTitle() {
        return showTitle;
    }

    /**
     * 
     * @param showTitle
     *     The show_title
     */
    public void setShowTitle(String showTitle) {
        this.showTitle = showTitle;
    }

    /**
     * 
     * @return
     *     The releaseYear
     */
    public String getReleaseYear() {
        return releaseYear;
    }

    /**
     * 
     * @param releaseYear
     *     The release_year
     */
    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * 
     * @return
     *     The rating
     */
    public String getRating() {
        return rating;
    }

    /**
     * 
     * @param rating
     *     The rating
     */
    public void setRating(String rating) {
        this.rating = rating;
    }

    /**
     * 
     * @return
     *     The category
     */
    public String getCategory() {
        return category;
    }

    /**
     * 
     * @param category
     *     The category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 
     * @return
     *     The showCast
     */
    public String getShowCast() {
        return showCast;
    }

    /**
     * 
     * @param showCast
     *     The show_cast
     */
    public void setShowCast(String showCast) {
        this.showCast = showCast;
    }

    /**
     * 
     * @return
     *     The director
     */
    public String getDirector() {
        return director;
    }

    /**
     * 
     * @param director
     *     The director
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * 
     * @return
     *     The summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 
     * @param summary
     *     The summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * 
     * @return
     *     The poster
     */
    public String getPoster() {
        return poster;
    }

    /**
     * 
     * @param poster
     *     The poster
     */
    public void setPoster(String poster) {
        this.poster = poster;
    }

    /**
     * 
     * @return
     *     The mediatype
     */
    public long getMediatype() {
        return mediatype;
    }

    /**
     * 
     * @param mediatype
     *     The mediatype
     */
    public void setMediatype(long mediatype) {
        this.mediatype = mediatype;
    }

    /**
     * 
     * @return
     *     The runtime
     */
    public String getRuntime() {
        return runtime;
    }

    /**
     * 
     * @param runtime
     *     The runtime
     */
    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

}
