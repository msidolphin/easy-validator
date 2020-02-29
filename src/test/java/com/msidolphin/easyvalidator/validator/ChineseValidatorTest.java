package com.msidolphin.easyvalidator.validator;

import com.msidolphin.easyvalidator.exception.ValidateFailedException;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.HashMap;

public class ChineseValidatorTest extends TestCase {

    @Test
    public void test () {
        new ChineseValidator().validate("鑫森淼焱垚犇骉羴猋麤鱻贔掱劦晶磊毳畾飝刕，哈哈哈哈。。。！？：、", null, "");
    }

    @Test
    public void testEmptyString () {
        try {
            new ChineseValidator().validate("", null, "");
        } catch (ValidateFailedException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testNull () {
        try {
            new ChineseValidator().validate(null, null, "");
        } catch (ValidateFailedException e) {
            System.out.println(e.getMessage());
        }
    }


    @Test
    public void testFailed () {
        try {
            new ChineseValidator().validate(1, null, "");
        } catch (ValidateFailedException e) {
            System.out.println(e.getMessage());
        }
        try {
            new ChineseValidator().validate("123123123", null, "");
        } catch (ValidateFailedException e) {
            System.out.println(e.getMessage());
        }
        try {
            new ChineseValidator().validate("啊哈哈哈 烫烫烫", null, "");
        } catch (ValidateFailedException e) {
            System.out.println(e.getMessage());
        }
        try {
            new ChineseValidator().validate(new HashMap<>(), null, "");
        } catch (ValidateFailedException e) {
            System.out.println(e.getMessage());
        }
    }




}