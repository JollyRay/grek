package test;

import org.junit.Assert;
import org.junit.Test;

import ru.mironova.grek.Grek;
import static ru.mironova.grek.Console.*;

public class AppTest {
    @Test
    public void grekTest1(){
        String[] args = "-n 6 -A 1 -B 1 test/test2/test.txt test/test2/test2.txt".split(" ");
        Assert.assertEquals(Grek.initAndExcute(args), 
            "test/test2/test.txt-5-5\n" +
            "test/test2/test.txt:6:6\n" +
            "test/test2/test.txt-7-7\n" +
            "--\n" +
            "test/test2/test.txt-13-7\n" +
            "test/test2/test.txt:14:6\n" +
            "test/test2/test.txt-15-5\n" +
            "test/test2/test.txt:16:6\n" +
            "test/test2/test.txt-17-7\n" +
            "--\n" +
            "test/test2/test2.txt-3-v\n" +
            "test/test2/test2.txt:4:6\n" +
            "test/test2/test2.txt-5-3\n" +
            "--\n" +
            "test/test2/test2.txt-7-8\n" +
            "test/test2/test2.txt:8:1234567890");
    }

    @Test
    public void grekTest2(){
        String[] args = "-n 6 -A 1 -B 1 test/test2/test.txt 4324324".split(" ");
        Assert.assertEquals(Grek.initAndExcute(args), 
        "test/test2/test.txt-5-5\n" +
        "test/test2/test.txt:6:6\n" +
        "test/test2/test.txt-7-7\n" +
        "--\n" +
        "test/test2/test.txt-13-7\n" +
        "test/test2/test.txt:14:6\n" +
        "test/test2/test.txt-15-5\n" +
        "test/test2/test.txt:16:6\n" +
        "test/test2/test.txt-17-7\n" +
        "grek: 4324324: No such file or directory");
    }

    @Test
    public void grekTest3(){
        String[] args = "-n 6 -A 1 -B 1 test/test2/test.txt 4324324 test/test2/test2.txt ".split(" ");
        Assert.assertEquals(Grek.initAndExcute(args), 
        "test/test2/test.txt-5-5\n" +
        "test/test2/test.txt:6:6\n" +
        "test/test2/test.txt-7-7\n" +
        "--\n" +
        "test/test2/test.txt-13-7\n" +
        "test/test2/test.txt:14:6\n" +
        "test/test2/test.txt-15-5\n" +
        "test/test2/test.txt:16:6\n" +
        "test/test2/test.txt-17-7\n" +
        "grek: 4324324: No such file or directory\n" +
        "--\n" +
        "test/test2/test2.txt-3-v\n" +
        "test/test2/test2.txt:4:6\n" +
        "test/test2/test2.txt-5-3\n" +
        "--\n" +
        "test/test2/test2.txt-7-8\n" +
        "test/test2/test2.txt:8:1234567890");
    }

    @Test
    public void grekTest4(){
        String[] args = "-n 6 -A 2 -B 2 test/test2/test.txt 4324324 test/test2/test2.txt ".split(" ");
        Assert.assertEquals(Grek.initAndExcute(args), 
        "test/test2/test.txt-4-4\n" +
        "test/test2/test.txt-5-5\n" +
        "test/test2/test.txt:6:6\n" +
        "test/test2/test.txt-7-7\n" +
        "test/test2/test.txt-8-8\n" +
        "--\n" +
        "test/test2/test.txt-12-8\n" +
        "test/test2/test.txt-13-7\n" +
        "test/test2/test.txt:14:6\n" +
        "test/test2/test.txt-15-5\n" +
        "test/test2/test.txt:16:6\n" +
        "test/test2/test.txt-17-7\n" +
        "test/test2/test.txt-18-8\n" +
        "grek: 4324324: No such file or directory\n" +
        "--\n" +
        "test/test2/test2.txt-2-b\n" +
        "test/test2/test2.txt-3-v\n" +
        "test/test2/test2.txt:4:6\n" +
        "test/test2/test2.txt-5-3\n" +
        "test/test2/test2.txt-6-f\n" +
        "test/test2/test2.txt-7-8\n" +
        "test/test2/test2.txt:8:1234567890");
    }

    @Test
    public void grekTest5(){
        String[] args = "-n 6 -A 2 -B 1 test/test2/test.txt 4324324 test/test2/test2.txt".split(" ");
        Assert.assertEquals(Grek.initAndExcute(args), 
        "test/test2/test.txt-5-5\n" +
        "test/test2/test.txt:6:6\n" +
        "test/test2/test.txt-7-7\n" +
        "test/test2/test.txt-8-8\n" +
        "--\n" +
        "test/test2/test.txt-13-7\n" +
        "test/test2/test.txt:14:6\n" +
        "test/test2/test.txt-15-5\n" +
        "test/test2/test.txt:16:6\n" +
        "test/test2/test.txt-17-7\n" +
        "test/test2/test.txt-18-8\n" +
        "grek: 4324324: No such file or directory\n" +
        "--\n" +
        "test/test2/test2.txt-3-v\n" +
        "test/test2/test2.txt:4:6\n" +
        "test/test2/test2.txt-5-3\n" +
        "test/test2/test2.txt-6-f\n" +
        "test/test2/test2.txt-7-8\n" +
        "test/test2/test2.txt:8:1234567890");
    }

    @Test
    public void grekTest6(){
        String[] args = "-n 6 -A 0  test/test2/test.txt 4324324 test/test2/test2.txt".split(" ");
        Assert.assertEquals(Grek.initAndExcute(args), 
        "test/test2/test.txt:6:6\n" +
        "--\n" +
        "test/test2/test.txt:14:6\n" +
        "--\n" +
        "test/test2/test.txt:16:6\n" +
        "grek: 4324324: No such file or directory\n" +
        "--\n" +
        "test/test2/test2.txt:4:6\n" +
        "--\n" +
        "test/test2/test2.txt:8:1234567890");
    }

    @Test
    public void grekTest7(){
        String[] args = "-n 6 -r -A 0  test/test2/test.txt 4324324 test/test2/test2.txt".split(" ");
        Assert.assertEquals(Grek.initAndExcute(args), 
        "test/test2/test.txt:6:6\n" +
        "--\n" +
        "test/test2/test.txt:14:6\n" +
        "--\n" +
        "test/test2/test.txt:16:6\n" +
        "grek: 4324324: No such file or directory\n" +
        "--\n" +
        "test/test2/test2.txt:4:6\n" +
        "--\n" +
        "test/test2/test2.txt:8:1234567890");
    }

    @Test
    public void grekTest8(){
        String[] args = "-n".split(" ");
        Assert.assertEquals(Grek.initAndExcute(args), "");
    }

    @Test
    public void grekTest9(){
        String[] args = "-n 6 test/test2/test.txt 4324324 -r test/test2".split(" ");
        Assert.assertEquals(Grek.initAndExcute(args), 
        "test/test2/test.txt:6:6\n" +
        "test/test2/test.txt:14:6\n" +
        "test/test2/test.txt:16:6\n" +
        "grek: 4324324: No such file or directory\n" +
        "test/test2/test.txt:6:6\n" +
        "test/test2/test.txt:14:6\n" +
        "test/test2/test.txt:16:6\n" +
        "test/test2/test2.txt:4:6\n" +
        "test/test2/test2.txt:8:1234567890");
    }

    @Test
    public void grekTest10(){
        String[] args = "8 -r test/test2".split(" ");
        Assert.assertEquals(Grek.initAndExcute(args), 
        "test/test2/test.txt:8\n"+
        "test/test2/test.txt:8\n"+
        "test/test2/test.txt:8\n"+
        "test/test2/test.txt:8\n"+
        "test/test2/test2.txt:8\n"+
        "test/test2/test2.txt:1234567890");
    }

    @Test
    public void grekTest11(){
        String[] args = "--exclude=test.txt 6 test/test2/ -r".split(" ");
        Assert.assertEquals(Grek.initAndExcute(args), 
        "test/test2/test2.txt:6\n" +
        "test/test2/test2.txt:1234567890");
    }

    @Test
    public void grekTest12(){
        String[] args = "--exclude=test.txt 6 test/test2/test.txt test/test2/test2.txt ".split(" ");
        Assert.assertEquals(Grek.initAndExcute(args), 
        "test/test2/test2.txt:6\n" +
        "test/test2/test2.txt:1234567890");
    }

    @Test
    public void grekTest13(){
        String[] args = "--exclude=test.txt fghjkl test/test2/test.txt test/test2/test2.txt ".split(" ");
        Assert.assertEquals(Grek.initAndExcute(args), 
        "");
    }

    @Test
    public void grekTest14(){
        String[] args = "-i -r A test/test2".split(" ");
        Assert.assertEquals(Grek.initAndExcute(args), "test/test2/test2.txt:a");
    }

    @Test
    public void grekTest15(){
        String[] args = "-r A test/test2".split(" ");
        Assert.assertEquals(Grek.initAndExcute(args), "");
    }

    @Test
    public void grekTest16(){
        String[] args = "^1 test/test3.txt".split(" ");
        Assert.assertEquals(Grek.initAndExcute(args),
        "12345678\n" +
        "1234");
    }

    @Test
    public void grekTest17(){
        String[] args = "* test/test2/test3.txt".split(" ");
        Assert.assertEquals(Grek.initAndExcute(args), "");
    }

    @Test
    public void grekTest18(){
        String[] args = "^* test/test2/test3.txt".split(" ");
        Assert.assertEquals(Grek.initAndExcute(args), "");
    }

    @Test
    public void grekTest19(){
        String[] args = "[345]* test/test3.txt".split(" ");
        Assert.assertEquals(Grek.initAndExcute(args),
        "12345678\n" +
        "3456789\n" +
        "asdfghjkl;'\n" +
        "qwertyuiop\n" +
        "zxcvbnm,.\n" +
        "dfghjk\n" +
        "ertyui\\\n" +
        "1234\n" +
        "cxzczx");
    }

    @Test
    public void grekTest20(){
        String[] args = "x[cz] test/test3.txt".split(" ");
        Assert.assertEquals(Grek.initAndExcute(args),
        "zxcvbnm,.\n" + 
        "cxzczx");
    }

    @Test
    public void grekTest21(){
        String[] args = "^3 test/test3.txt".split(" ");
        Assert.assertEquals(Grek.initAndExcute(args), "3456789");
    }

    @Test
    public void grekTest22(){
        String[] args = "^* test/test3.txt".split(" ");
        Assert.assertEquals(Grek.initAndExcute(args), "");
    }

    @Test
    public void grekTest23(){
        String[] args = "[^123] test/test3.txt".split(" ");
        Assert.assertEquals(Grek.initAndExcute(args), 
        "12345678\n" +
        "3456789\n" +
        "asdfghjkl;'\n" +
        "qwertyuiop\n" +
        "zxcvbnm,.\n" +
        "dfghjk\n" +
        "ertyui\\\n" +
        "1234\n" +
        "cxzczx");
    }

    @Test
    public void grekTest24(){
        String[] args = "[^1234] test/test3.txt".split(" ");
        Assert.assertEquals(Grek.initAndExcute(args), 
        "12345678\n" +
        "3456789\n" +
        "asdfghjkl;'\n" +
        "qwertyuiop\n" +
        "zxcvbnm,.\n" +
        "dfghjk\n" +
        "ertyui\\\n" +
        "cxzczx");
    }

    @Test public void grekTest25(){
        String[] args = "--help".split(" ");
        Assert.assertEquals(Grek.initAndExcute(args), helpMessage());
    }

    @Test public void grekTest26(){
        String[] args = "--exclude=test.txt fghjkl test/test2/test.txt test/test2/test2.txt --help".split(" ");
        Assert.assertEquals(Grek.initAndExcute(args), helpMessage());
    }

    @Test public void grekTest27(){
        String[] args = "-gfdt.txtfghjkl test/test2/test.txt test/test2/test2.txt".split(" ");
        Assert.assertEquals(Grek.initAndExcute(args), "Uncorrect arg: -gfdt.txtfghjkl");
    }

    @Test public void grekTest28(){
        String[] args = "-r 6 -A -1 test/test2 --exclude=^*".split(" ");
        Assert.assertEquals(Grek.initAndExcute(args), "Uncorrect arg: -1");
    }
}
