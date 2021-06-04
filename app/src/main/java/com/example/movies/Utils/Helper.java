package com.example.movies.Utils;

import com.example.movies.Entity.Genres;
import com.example.movies.Entity.Video;

import java.util.List;

public class Helper {


    public static String convertToHours(int minutes)
    {
        int hours=minutes/60;
        int min=minutes%60;

        String res=hours+":"+String.format("%02d",min);
        return res+" h";
    }

    public static String getCategories(List<Genres>genres)
    {

        int counter=2;
        String result="";
        for(int i=0;i<genres.size()&&counter!=0;i++,counter--)
        {
            String category=genres.get(i).getName();
            result+=category+"\n";
        }
        return result;
    }
    public static String getTrailerKey(Video video)
    {
        String trailerKey;
        String tmp="";
        List<Video.youTubeKeys>keys=video.getResults();
        for(int i=0;i<keys.size();i++)
        {
            if(keys.get(i).getType().equals("Trailer"))
            {
                trailerKey=keys.get(i).getKey();
                return trailerKey;
            }
            else
            {
                tmp=keys.get(i).getKey();
            }
        }
        if(tmp.length()!=0){
            return tmp;
        }
        return null;
    }
}
