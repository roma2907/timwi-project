package projet.service;

import org.junit.Assert;
import org.junit.Test;
import projet.service.impl.ReadingDigitServiceImpl;

public class ReadingDigitServiceTest {

    private IReadingDigitService readingDigitService = new ReadingDigitServiceImpl();

    /**
     * Cas toujours le même chiffre, 1 fois
     */
    @Test
    public void testLookAndSaySameDigit1(){
        String result = readingDigitService.lookAndSay("1111",1);
        Assert.assertEquals("41",result);
    }

    /**
     * Cas toujours le même chiffre, 3 fois
     */
    @Test
    public void testLookAndSaySameDigit3(){
        String result = readingDigitService.lookAndSay("1111",3);
        Assert.assertEquals("111421",result);
    }

    /**
     * Cas des chiffres différents, 1 fois
     */
    @Test
    public void testLookAndSayDifferentDigit1(){
        String result = readingDigitService.lookAndSay("123",1);
        Assert.assertEquals("111213",result);
    }

    /**
     * Cas des chiffres différents, 3 fois
     */
    @Test
    public void testLookAndSayDifferentDigit3(){
        String result = readingDigitService.lookAndSay("123",3);
        Assert.assertEquals("1321123113",result);
    }
}
