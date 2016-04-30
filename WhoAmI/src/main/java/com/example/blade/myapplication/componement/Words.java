package com.example.blade.myapplication.componement;

import java.util.ArrayList;

/**
 * Created by blade on 16/4/12.
 */
public class Words {

    ArrayList<WordPairs> words = new ArrayList<WordPairs>();

    public void init(){

        words.add(new WordPairs("苹果","雪梨"));
        words.add(new WordPairs("外套","内衣"));
        words.add(new WordPairs("母鸡","公鸡"));
        words.add(new WordPairs("老虎","猎豹"));

    }


}
