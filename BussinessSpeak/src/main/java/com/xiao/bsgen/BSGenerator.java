package com.xiao.bsgen;

import android.content.Context;
import android.content.res.Resources;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Generates BS strings.
 * Created by Xiao on 6/29/13.
 */
public class BSGenerator {

    private List<String> adverbs;
    private List<String> adjectives;
    private List<String> nouns;
    private List<String> verbs;
    private static Random random = new Random();

    private static BSGenerator instance;

    public static BSGenerator getInstance()
    {
        return instance;
    }

    public List<String> getAdverbs() {
        return adverbs;
    }

    public List<String> getAdjectives() {
        return adjectives;
    }

    public List<String> getNouns() {
        return nouns;
    }

    public List<String> getVerbs() {
        return verbs;
    }

    public static void init(Context context)
    {
        instance = new BSGenerator(context);
    }

    public BSGenerator(Context context)
    {
        Resources resources= context.getResources();
        adverbs = Arrays.asList(resources.getStringArray(R.array.adverbs));
        adjectives = Arrays.asList(resources.getStringArray(R.array.adjectives));
        nouns = Arrays.asList(resources.getStringArray(R.array.nouns));
        verbs = Arrays.asList(resources.getStringArray(R.array.verbs));
    }

    private String getRandomStringInList(List<String> strList)
    {
        return strList.get(random.nextInt(strList.size()));
    }
    public String generate()
    {
        StringBuilder builder = new StringBuilder(100);

        String adjective = getRandomStringInList(adjectives);
        String adverb = getRandomStringInList(adverbs);
        String noun = getRandomStringInList(nouns);
        String verb = getRandomStringInList(verbs);

        builder.append(adverb)
        .append(" ")
        .append(verb)
        .append(" ")
        .append(adjective)
        .append(" ")
        .append(noun);

        return builder.toString();
    }
}
