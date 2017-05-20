/*
 * Copyright (C) 2017 dd2568 : Project submitted for Udacity Android Developer Nanodegree.
 */
package com.example.uadnd.cou8901.popularmoviesapp2;


/*
 * Created by dd2568 on 4/16/2017.
 *
 {
 "page": 1,
 "results": [
 {

 "poster_path": "/tWqifoYuwLETmmasnGHO7xBjEtt.jpg",
 "adult": false,
 "overview": "A live-action adaptation of Disney's version of the classic 'Beauty and the Beast' tale of a cursed prince and a beautiful young woman who helps him break the spell.",
 "release_date": "2017-03-16",
 "genre_ids": [
 14,
 10402,
 10749
 ],
 "id": 321612,
 "original_title": "Beauty and the Beast",
 "original_language": "en",
 "title": "Beauty and the Beast",
 "backdrop_path": "/6aUWe0GSl69wMTSWWexsorMIvwU.jpg",
 "popularity": 179.312119,
 "vote_count": 1394,
 "video": false,
 "vote_average": 7

 },
 .
 .
 .
 .
 ],
 "total_results": 19537,
 "total_pages": 977
 }
 */


public class MovieDbMovie {
    /*
     * MovieDbMovie POJO with all the attributes as found from API calls .
     * A partial json result is shown above . Not all the attributes are used in this project.
     */
    private String posterPath;  // "/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg"
    private boolean adult;
    private String overview;
    private String releaseDate;
    private long [] genreIds;
    private String id;
    private String originalTitle;
    private String originalLanguage;
    private String title;
    private String backdropPath; // "/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg"
    private float popularity;
    private long voteCount;
    private boolean video;

    private String voteAverage;
    private String json;   // to be passed to detail activity
    public MovieDbMovie() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public long[] getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(long[] genreIds) {
        this.genreIds = genreIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(long voteCount) {
        this.voteCount = voteCount;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }
    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }


}
