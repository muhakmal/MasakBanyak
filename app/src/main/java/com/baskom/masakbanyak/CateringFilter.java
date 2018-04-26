package com.baskom.masakbanyak;

import com.google.common.base.Predicate;

import java.util.regex.Pattern;

public class CateringFilter implements Predicate<Catering> {

    private Pattern pattern;

    public CateringFilter(String regex){
        pattern = Pattern.compile(regex.toLowerCase());
    }

    @Override
    public boolean apply(Catering input) {
        return pattern.matcher(input.getName().toLowerCase()).find();
    }
}
