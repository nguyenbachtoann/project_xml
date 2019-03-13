/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toannb.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import toannb.wellformer.SyntaxChecker;

/**
 *
 * @author bachtoan
 */
public class TextUtilities {

    public String refineHtml(String src) {
        src = getBody(src);
        src = removeMiscellaneousTags(src);

        SyntaxChecker xmlSyntaxChecker = new SyntaxChecker();
        src = xmlSyntaxChecker.check(src);

        //crop one more time
        src = getBody(src);
        return src;
    }

    private static String getBody(String src) {
        String result = src;
        String expression = "<body.*?</body>";
        Pattern pattern = Pattern.compile(expression);

        Matcher matcher = pattern.matcher(result);

        if (matcher.find()) {
            result = matcher.group(0);
        }

        return result;
    }

    public static String removeMiscellaneousTags(String src) {
        String result = src;

        //Remove all <script> tags
        String expression = "<script.*?</script>";
        result = result.replaceAll(expression, "");

        expression = "<noscript.*?</noscript>";
        result = result.replaceAll(expression, "");
        
        //Remove all comments
        expression = "<!--.*?-->";
        result = result.replaceAll(expression, "");

        //Remove all <script> tags
        expression = "&nbsp;?";
        result = result.replaceAll(expression, "");

        return result;
    }
}
