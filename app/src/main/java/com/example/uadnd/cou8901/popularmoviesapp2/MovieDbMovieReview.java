package com.example.uadnd.cou8901.popularmoviesapp2;

/**
 * Created by dd2568 on 5/14/2017.
 */
/*
{

    "id": 283995,
    "page": 1,
    "results": [
        {
            "id": "59007a4292514161aa00a8bd",
            "author": "steveinadelaide l",
            "content": "I didn’t enjoy this as much as the first one. Too long — could have been about 30 minutes shorter. I don’t mind long movies, but they shouldn’t feel long. There’s some good humour throughout the movie and the characters are enjoyable, particularly Rocket who is my favourite! Story is pretty thin. Some good action sequences, of course. Overall, an average movie. Has lost some of the freshness of the original.",
            "url": "https://www.themoviedb.org/review/59007a4292514161aa00a8bd"
        }
    ],
    "total_pages": 1,
    "total_results": 1

}
 */
public class MovieDbMovieReview {
    private String id;
    private String author;
    private String content;
    private String url;

    public void setId(String id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }
}
