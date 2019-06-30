package com.example.mymovie.Dao;


import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.ArrayList;
import java.util.List;

import org.greenrobot.greendao.annotation.Generated;

@Entity
public class MovieEntity2Db implements Parcelable {
    @Override
    public String toString() {
        return "MovieEntity2Db{" +
                "Name='" + Name + '\'' +
                ", directors='" + directors1 + '\'' +
                ", years='" + years + '\'' +
                '}';
    }

    @org.greenrobot.greendao.annotation.Id
    private int Id;

    private String Pic = null;

    private String Name = null;

    private double Rating;

    private String type1 = null;
    private String type2 = null;
    private String type3 = null;

    private String description = null;

    private String cast1 = null;
    private String cast2 = null;
    private String cast3 = null;

    private String directors1 = null;
    private String directors2 = null;
    private String directors3 = null;
    private String years = null;


    @Generated(hash = 1631209678)
    public MovieEntity2Db(int Id, String Pic, String Name, double Rating,
                          String type1, String type2, String type3, String description,
                          String cast1, String cast2, String cast3, String directors1,
                          String directors2, String directors3, String years) {
        this.Id = Id;
        this.Pic = Pic;
        this.Name = Name;
        this.Rating = Rating;
        this.type1 = type1;
        this.type2 = type2;
        this.type3 = type3;
        this.description = description;
        this.cast1 = cast1;
        this.cast2 = cast2;
        this.cast3 = cast3;
        this.directors1 = directors1;
        this.directors2 = directors2;
        this.directors3 = directors3;
        this.years = years;
    }

    @Generated(hash = 1726236228)
    public MovieEntity2Db() {
    }


    public List<String> getTypes() {
        List<String> res = new ArrayList<String>();
        if (type1 != null)
            res.add(type1);
        if (type2 != null)
            res.add(type2);
        if (type3 != null)
            res.add(type3);
        return res;
    }

    public void setTypes(String[] types) {
        int len = types.length;
        if (len == 2) {
            type1 = types[0];
            type2 = types[1];
        } else if (len == 3) {
            type1 = types[0];
            type2 = types[1];
            type3 = types[2];
        } else if (len == 1) {
            type1 = types[0];
        }

    }

    public String getCasts() {
        StringBuffer res = new StringBuffer();
        if (cast1 != null)
            res.append(cast1);
        if (cast2 != null)
            res.append(" , ").append(cast2);
        if (cast3 != null)
            res.append(" , ").append(cast3);
        return res.toString();
    }

    public void setCasts(String[] casts) {
        int len = casts.length;
        if (len == 2) {
            cast1 = casts[0];
            cast2 = casts[1];
        } else if (len == 3) {
            cast1 = casts[0];
            cast2 = casts[1];
            cast3 = casts[2];
        } else if (len == 1) {
            cast1 = casts[0];
        }

    }

    public void setDirectors(String[] directors) {
        int len = directors.length;
        if (len == 2) {
            directors1 = directors[0];
            directors2 = directors[1];
        } else if (len == 3) {
            directors1 = directors[0];
            directors2 = directors[1];
            directors3 = directors[2];
        } else if (len == 1) {
            directors1 = directors[0];
        }
    }

    public String getDirectors() {

        StringBuffer res = new StringBuffer();
        if (directors1 != null)
            res.append(directors1);
        if (directors2 != null)
            res.append(" , ").append(directors2);
        if (directors3 != null)
            res.append(" , ").append(directors3);
        return res.toString();
    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getPic() {
        return Pic;
    }

    public void setPic(String pic) {
        Pic = pic;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getRating() {
        return Rating;
    }

    public void setRating(double rating) {
        Rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public String getType1() {
        return this.type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return this.type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public String getType3() {
        return this.type3;
    }

    public void setType3(String type3) {
        this.type3 = type3;
    }

    public String getCast1() {
        return this.cast1;
    }

    public void setCast1(String cast1) {
        this.cast1 = cast1;
    }

    public String getCast2() {
        return this.cast2;
    }

    public void setCast2(String cast2) {
        this.cast2 = cast2;
    }

    public String getCast3() {
        return this.cast3;
    }

    public void setCast3(String cast3) {
        this.cast3 = cast3;
    }

    public String getDirectors1() {
        return this.directors1;
    }

    public void setDirectors1(String directors1) {
        this.directors1 = directors1;
    }

    public String getDirectors2() {
        return this.directors2;
    }

    public void setDirectors2(String directors2) {
        this.directors2 = directors2;
    }

    public String getDirectors3() {
        return this.directors3;
    }

    public void setDirectors3(String directors3) {
        this.directors3 = directors3;
    }


    public MovieEntity2Db(Parcel source) {
        Id = source.readInt();

        Pic = source.readString();

        Name = source.readString();

        Rating = source.readDouble();

        type1 = source.readString();
        type2 = source.readString();
        type3 =source.readString();

        description = source.readString();

        cast1 = source.readString();
        cast2 = source.readString();
        cast3 = source.readString();

        directors1 = source.readString();
        directors2 = source.readString();
        directors3 = source.readString();
        years = source.readString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Id);
        dest.writeString(Pic);
        dest.writeString(Name);
        dest.writeDouble(Rating);
        dest.writeString(type1);
        dest.writeString(type2);
        dest.writeString(type3);
        dest.writeString(description);
        dest.writeString(cast1);
        dest.writeString(cast2);
        dest.writeString(cast3);
        dest.writeString(directors1);
        dest.writeString(directors2);
        dest.writeString(directors3);
        dest.writeString(years);
    }

    public static final Parcelable.Creator<MovieEntity2Db> CREATOR = new Creator<MovieEntity2Db>() {
        @Override
        public MovieEntity2Db createFromParcel(Parcel source) {
            return new MovieEntity2Db(source);
        }

        @Override
        public MovieEntity2Db[] newArray(int size) {
            return new MovieEntity2Db[size];
        }
    };
}
