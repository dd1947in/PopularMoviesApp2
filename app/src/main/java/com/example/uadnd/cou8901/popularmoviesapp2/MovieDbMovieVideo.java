package com.example.uadnd.cou8901.popularmoviesapp2;

/**
 * Created by dd2568 on 5/14/2017.
 */
/* {

    "id": 283995,
    "results": [
        {
            "id": "58ba57ac925141608401976f",
            "iso_639_1": "en",
            "iso_3166_1": "US",
            "key": "wUn05hdkhjM",
            "name": "Official Trailer 3",
            "site": "YouTube",
            "size": 1080,
            "type": "Trailer"
        },
        {
            "id": "58f5e92492514127c700b0dc",
            "iso_639_1": "en",
            "iso_3166_1": "US",
            "key": "2WhQcK-Zaok",
            "name": "Official Sneak Peek",
            "site": "YouTube",
            "size": 1080,
            "type": "Teaser"
        },
        ........
        {
            "id": "58f5eb98925141277500b569",
            "iso_639_1": "en",
            "iso_3166_1": "US",
            "key": "0LWEYReXjQs",
            "name": "\"It's Showtime\" TV Spot",
            "site": "YouTube",
            "size": 1080,
            "type": "Teaser"
        }
    ]

}
        */
public class MovieDbMovieVideo {
    private String id;
    private String iso_639_1;
    private String iso_3166_1;
    private String key;   // youtube key
    private String name;
    private String site;
    private String size;
    private String type;

    public String getId() {
        return id;
    }

    public String getIso_639_1() {
        return iso_639_1;
    }

    public String getIso_3166_1() {
        return iso_3166_1;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getSite() {
        return site;
    }

    public String getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIso_639_1(String iso_639_1) {
        this.iso_639_1 = iso_639_1;
    }

    public void setIso_3166_1(String iso_3166_1) {
        this.iso_3166_1 = iso_3166_1;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setType(String type) {
        this.type = type;
    }
}
