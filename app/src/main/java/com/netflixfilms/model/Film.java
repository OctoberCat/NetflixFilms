
package com.netflixfilms.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Table(name = "Films")
public class Film extends Model implements Parcelable {

    @SerializedName("unit")
    @Expose
    @Column
    private long unit;
    @SerializedName("show_id")
    @Expose
    @Column(unique = true, onUniqueConflict = Column.ConflictAction.IGNORE)
    private long showId;
    @SerializedName("show_title")
    @Expose
    @Column
    private String showTitle;
    @SerializedName("release_year")
    @Expose
    @Column
    private String releaseYear;
    @SerializedName("rating")
    @Expose
    @Column
    private String rating;
    @SerializedName("category")
    @Expose
    @Column
    private String category;
    @SerializedName("show_cast")
    @Expose
    @Column
    private String showCast;
    @SerializedName("director")
    @Expose
    @Column
    private String director;
    @SerializedName("summary")
    @Expose
    @Column
    private String summary;
    @SerializedName("poster")
    @Expose
    @Column
    private String poster;
    @SerializedName("mediatype")
    @Expose
    @Column
    private long mediatype;
    @SerializedName("runtime")
    @Expose
    @Column
    private String runtime;

    public Film(){
        super();
    }

    protected Film(Parcel in) {
        super();
        unit = in.readLong();
        showId = in.readLong();
        showTitle = in.readString();
        releaseYear = in.readString();
        rating = in.readString();
        category = in.readString();
        showCast = in.readString();
        director = in.readString();
        summary = in.readString();
        poster = in.readString();
        mediatype = in.readLong();
        runtime = in.readString();
    }

    public static final Creator<Film> CREATOR = new Creator<Film>() {
        @Override
        public Film createFromParcel(Parcel in) {
            return new Film(in);
        }

        @Override
        public Film[] newArray(int size) {
            return new Film[size];
        }
    };

    public long getUnit() {
        return unit;
    }

    public void setUnit(long unit) {
        this.unit = unit;
    }

    public long getShowId() {
        return showId;
    }

    public void setShowId(long showId) {
        this.showId = showId;
    }

    public String getShowTitle() {
        return showTitle;
    }

    public void setShowTitle(String showTitle) {
        this.showTitle = showTitle;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getShowCast() {
        return showCast;
    }

    public void setShowCast(String showCast) {
        this.showCast = showCast;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public long getMediatype() {
        return mediatype;
    }

    public void setMediatype(long mediatype) {
        this.mediatype = mediatype;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(unit);
        parcel.writeLong(showId);
        parcel.writeString(showTitle);
        parcel.writeString(releaseYear);
        parcel.writeString(rating);
        parcel.writeString(category);
        parcel.writeString(showCast);
        parcel.writeString(director);
        parcel.writeString(summary);
        parcel.writeString(poster);
        parcel.writeLong(mediatype);
        parcel.writeString(runtime);
    }
}
