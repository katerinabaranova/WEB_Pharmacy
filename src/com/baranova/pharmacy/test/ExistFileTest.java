package com.baranova.pharmacy.test;

import com.baranova.pharmacy.constant.FileConstant;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by Ekaterina on 10/7/16.
 */
public class ExistFileTest {

    @Test
    public void ReadFileTest(){
        String src= FileConstant.DATABASE_INFO;
        File file=new File(src);
        assertTrue(file.exists());
    }

}
